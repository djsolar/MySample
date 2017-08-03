package com.example.zhouyiran.mysample.model.http.service;

import com.example.zhouyiran.mysample.model.http.entity.envicloud.EnvironmentCloudCityAirLive;
import com.example.zhouyiran.mysample.model.http.entity.envicloud.EnvironmentCloudForecast;
import com.example.zhouyiran.mysample.model.http.entity.envicloud.EnvironmentCloudWeatherLive;

import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by zhouyiran on 2017/7/19.
 */

public interface EnvironmentCloudWeatherService {

    @GET("/v2/weatherlive/YMFYB256AGFUZZE0ODQ3MZM1MZE2NTU=/{cityId}")
    Observable<EnvironmentCloudWeatherLive> getWeatherLive(@Path("cityId") String cityId);

    @GET("/v2/weatherforecast/YMFYB256AGFUZZE0ODQ3MZM1MZE2NTU=/{cityId}")
    Observable<EnvironmentCloudForecast> getWeatherForecase(@Path("cityId") String cityId);

    @GET("/v2/cityairlive/YMFYB256AGFUZZE0ODQ3MZM1MZE2NTU=/{cityId}")
    Observable<EnvironmentCloudCityAirLive> getWeatherAirLive(@Path("cityId") String cityId);
}
