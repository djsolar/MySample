package com.example.zhouyiran.mysample.model.db.dao;

import android.content.Context;

import com.example.zhouyiran.mysample.model.db.WeatherDatabaseHelper;
import com.example.zhouyiran.mysample.model.db.entities.minimalist.AirQualityLive;
import com.example.zhouyiran.mysample.model.db.entities.minimalist.LifeIndex;
import com.example.zhouyiran.mysample.model.db.entities.minimalist.Weather;
import com.example.zhouyiran.mysample.model.db.entities.minimalist.WeatherForecast;
import com.example.zhouyiran.mysample.model.db.entities.minimalist.WeatherLive;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.misc.TransactionManager;
import com.j256.ormlite.stmt.DeleteBuilder;
import com.j256.ormlite.stmt.PreparedDelete;

import java.sql.SQLException;
import java.util.List;

import javax.inject.Inject;

/**
 * Created by zhouyiran on 2017/7/19.
 */

public class WeatherDao {

    private Context context;

    private Dao<AirQualityLive, String> apiDaoOperation;
    private Dao<WeatherForecast, Long> forecastDaoOperation;
    private Dao<LifeIndex, Long> lifeIndexesDaoOperation;
    private Dao<WeatherLive, String> realTimeDaoOperation;
    private Dao<Weather, String> weatherDaoOperation;

    @Inject
    public WeatherDao(Context context) {
        this.context = context;
        this.apiDaoOperation = WeatherDatabaseHelper.getInstance(context).getWeatherDao(AirQualityLive.class);
        this.forecastDaoOperation = WeatherDatabaseHelper.getInstance(context).getWeatherDao(WeatherForecast.class);
        this.lifeIndexesDaoOperation = WeatherDatabaseHelper.getInstance(context).getWeatherDao(LifeIndex.class);
        this.realTimeDaoOperation = WeatherDatabaseHelper.getInstance(context).getWeatherDao(WeatherLive.class);
        this.weatherDaoOperation = WeatherDatabaseHelper.getInstance(context).getWeatherDao(Weather.class);
    }

    public Weather queryWeather(String cityId) throws SQLException {
        return TransactionManager.callInTransaction(WeatherDatabaseHelper.getInstance(context).getConnectionSource(), () -> {
            Weather weather = this.weatherDaoOperation.queryForId(cityId);
            if(weather != null) {
                weather.setAirQualityLive(this.apiDaoOperation.queryForId(cityId));
                weather.setWeatherForecasts(this.forecastDaoOperation.queryForEq(WeatherForecast.CITY_ID_FIELD_NAME, cityId));
                weather.setLifeIndexes(this.lifeIndexesDaoOperation.queryForEq(LifeIndex.CITY_ID_FIELD_NAME, cityId));
                weather.setWeatherLive(this.realTimeDaoOperation.queryForId(cityId));
            }
            return weather;
        });
    }

    public void insertOrUpdateWeather(Weather weather) throws SQLException {

        TransactionManager.callInTransaction(WeatherDatabaseHelper.getInstance(context).getConnectionSource(), () -> {
            if (weatherDaoOperation.idExists(weather.getCityId())) {
                updateWeather(weather);
            } else {
                insertWeather(weather);
            }
            return null;
        });
    }

    public void deleteById(String cityId) throws SQLException {
        weatherDaoOperation.deleteById(cityId);
    }

    private void delete(Weather data) throws SQLException {
        weatherDaoOperation.delete(data);
    }

    public List<Weather> queryAllSaveCity() throws SQLException {
        return TransactionManager.callInTransaction(WeatherDatabaseHelper.getInstance(context).getConnectionSource(), () -> {
           List<Weather> weatherList = weatherDaoOperation.queryForAll();
            for(Weather weather : weatherList) {
                String cityId = weather.getCityId();
                weather.setWeatherLive(realTimeDaoOperation.queryForId(cityId));
                weather.setWeatherForecasts(forecastDaoOperation.queryForEq(WeatherForecast.CITY_ID_FIELD_NAME, cityId));
                weather.setLifeIndexes(lifeIndexesDaoOperation.queryForEq(LifeIndex.CITY_ID_FIELD_NAME, cityId));
                weather.setAirQualityLive(apiDaoOperation.queryForId(cityId));
            }
            return weatherList;
        });
    }

    private void insertWeather(Weather weather) throws SQLException {
        weatherDaoOperation.create(weather);
        apiDaoOperation.create(weather.getAirQualityLive());
        for(WeatherForecast forecast : weather.getWeatherForecasts()){
            forecastDaoOperation.create(forecast);
        }
        for(LifeIndex lifeIndex : weather.getLifeIndexes()) {
            lifeIndexesDaoOperation.create(lifeIndex);
        }
        realTimeDaoOperation.create(weather.getWeatherLive());
    }

    private void updateWeather(Weather weather) throws SQLException {
        weatherDaoOperation.update(weather);
        apiDaoOperation.update(weather.getAirQualityLive());

        // 删除旧数据
        DeleteBuilder<WeatherForecast, Long> forecastDeleteBuilder = forecastDaoOperation.deleteBuilder();
        forecastDeleteBuilder.where().eq(WeatherForecast.CITY_ID_FIELD_NAME, weather.getCityId());
        PreparedDelete<WeatherForecast> prepareDeleteBuilder = forecastDeleteBuilder.prepare();
        forecastDaoOperation.delete(prepareDeleteBuilder);

        // 插入新数据
        for(WeatherForecast forecast : weather.getWeatherForecasts()){
            forecastDaoOperation.create(forecast);
        }

        // 删除旧数据
        DeleteBuilder<LifeIndex, Long> lifeIndexDeleteBuilder = lifeIndexesDaoOperation.deleteBuilder();
        forecastDeleteBuilder.where().eq(WeatherForecast.CITY_ID_FIELD_NAME, weather.getCityId());
        PreparedDelete<LifeIndex> prepareLifeDeleteBuilder = lifeIndexDeleteBuilder.prepare();
        lifeIndexesDaoOperation.delete(prepareLifeDeleteBuilder);

        // 插入新数据
        for(LifeIndex lifeIndex : weather.getLifeIndexes()) {
            lifeIndexesDaoOperation.create(lifeIndex);
        }
        realTimeDaoOperation.update(weather.getWeatherLive());
    }
}
