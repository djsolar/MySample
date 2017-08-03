package com.example.zhouyiran.mysample.model.http.service;

import com.example.zhouyiran.mysample.model.http.entity.know.KnowWeather;
import com.example.zhouyiran.mysample.model.http.entity.mi.MiWeather;

import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by zhouyiran on 2017/7/19.
 */

public interface WeatherService {

    @GET("weather")
    Observable<MiWeather> getMiWeather(@Query("cityId") String cityId);

    @GET("/v1.0/weather/{cityId}")
    Observable<KnowWeather> getKnowWeather(@Path("cityId") String cityId);
}
