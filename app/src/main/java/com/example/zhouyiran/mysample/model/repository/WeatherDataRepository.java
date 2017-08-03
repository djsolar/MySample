package com.example.zhouyiran.mysample.model.repository;

import android.content.Context;
import android.text.TextUtils;

import com.example.library.utils.NetWorkUtils;
import com.example.zhouyiran.mysample.model.db.dao.WeatherDao;
import com.example.zhouyiran.mysample.model.db.entities.adapter.CloudWeatherAdapter;
import com.example.zhouyiran.mysample.model.db.entities.adapter.KnowWeatherAdapter;
import com.example.zhouyiran.mysample.model.db.entities.adapter.MiWeatherAdapter;
import com.example.zhouyiran.mysample.model.db.entities.minimalist.Weather;
import com.example.zhouyiran.mysample.model.http.ApiClient;
import com.example.zhouyiran.mysample.model.http.ApiConstant;
import com.example.zhouyiran.mysample.model.http.entity.envicloud.EnvironmentCloudCityAirLive;
import com.example.zhouyiran.mysample.model.http.entity.envicloud.EnvironmentCloudForecast;
import com.example.zhouyiran.mysample.model.http.entity.envicloud.EnvironmentCloudWeatherLive;

import java.sql.SQLException;

import rx.Observable;
import rx.schedulers.Schedulers;

/**
 * Created by zhouyiran on 2017/7/19.
 */

public class WeatherDataRepository {

    public static Observable<Weather> getWeather(Context context, String cityId, WeatherDao weatherDao) {
        // 从数据库中取数据
        Observable<Weather> observableGetDataFromDB = Observable.create(subscriber -> {
            try {
                Weather weather = weatherDao.queryWeather(cityId);
                subscriber.onNext(weather);
                subscriber.onCompleted();
            } catch (SQLException e) {
                e.printStackTrace();
            }

        });

        if (!NetWorkUtils.isNetConnected(context)) {
            return observableGetDataFromDB;
        }

        // 从服务器获取天气
        Observable<Weather> observableGetDataFromServer = null;
        switch (ApiClient.configuration.getDataSourceType()) {
            case ApiConstant.WEATHER_DATA_SOURCE_TYPE_KNOW:
                observableGetDataFromServer = ApiClient.weatherService.getKnowWeather(cityId)
                        .map(knowWeather ->
                                new KnowWeatherAdapter(knowWeather).getWeather()
                        );
                break;

            case ApiConstant.WEATHER_DATA_SOURCE_TYPE_MI:
                observableGetDataFromServer = ApiClient.weatherService.getMiWeather(cityId)
                        .map(miWeather -> new MiWeatherAdapter(miWeather).getWeather());
                break;

            case ApiConstant.WEATHER_DATA_SOURCE_TYPE_ENVIRONMENT_CLOUD:
                Observable<EnvironmentCloudCityAirLive> cloudCityAirLiveObservable = ApiClient.environmentCloudWeatherService.getWeatherAirLive(cityId);
                Observable<EnvironmentCloudForecast> cloudForecastObservable = ApiClient.environmentCloudWeatherService.getWeatherForecase(cityId);
                Observable<EnvironmentCloudWeatherLive> cloudWeatherLiveObservable = ApiClient.environmentCloudWeatherService.getWeatherLive(cityId);

                observableGetDataFromServer = Observable.combineLatest(cloudCityAirLiveObservable, cloudForecastObservable, cloudWeatherLiveObservable,
                        ((environmentCloudCityAirLive, environmentCloudForecast, environmentCloudWeatherLive) ->
                                new CloudWeatherAdapter(environmentCloudWeatherLive, environmentCloudForecast, environmentCloudCityAirLive).getWeather()
                        ));

                break;
        }
        observableGetDataFromServer = observableGetDataFromServer.doOnNext(weather -> Schedulers.io().createWorker().schedule(() -> {
            try {
                weatherDao.insertOrUpdateWeather(weather);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }));
        return Observable.concat(observableGetDataFromDB, observableGetDataFromServer).filter(weather -> weather != null && TextUtils.isEmpty(weather.getCityId()))
                .distinct(weather -> weather.getWeatherLive().getTime())
                .takeUntil(weather -> System.currentTimeMillis() - weather.getWeatherLive().getTime() <= 15 * 60 * 1000);
    }

}
