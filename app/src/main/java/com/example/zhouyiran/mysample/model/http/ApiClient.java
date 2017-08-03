package com.example.zhouyiran.mysample.model.http;

import com.baronzhang.retrofit2.converter.FastJsonConverterFactory;
import com.example.zhouyiran.mysample.BuildConfig;
import com.example.zhouyiran.mysample.model.http.configuration.ApiConfiguration;
import com.example.zhouyiran.mysample.model.http.service.EnvironmentCloudWeatherService;
import com.example.zhouyiran.mysample.model.http.service.WeatherService;
import com.facebook.stetho.okhttp3.StethoInterceptor;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;

/**
 * Created by zhouyiran on 2017/7/19.
 */

public class ApiClient {

    public static WeatherService weatherService;

    public static EnvironmentCloudWeatherService environmentCloudWeatherService;

    public static ApiConfiguration configuration;

    public static void init(ApiConfiguration apiConfiguration) {
        configuration = apiConfiguration;
        String weatherApiHost;
        switch (configuration.getDataSourceType()) {
            case ApiConstant.WEATHER_DATA_SOURCE_TYPE_MI:
                weatherApiHost = ApiConstant.MI_WEATHER_API_HOST;
                weatherService = initWeatherService(weatherApiHost, WeatherService.class);
                break;

            case ApiConstant.WEATHER_DATA_SOURCE_TYPE_KNOW:
                weatherApiHost = ApiConstant.KNOW_WEATHER_API_HOST;
                weatherService = initWeatherService(weatherApiHost, WeatherService.class);
                break;

            case ApiConstant.WEATHER_DATA_SOURCE_TYPE_ENVIRONMENT_CLOUD:
                weatherApiHost = ApiConstant.ENVIRONMENT_CLOUD_WEATHER_API_HOST;
                environmentCloudWeatherService = initWeatherService(weatherApiHost, EnvironmentCloudWeatherService.class);
                break;
        }
    }

    private static <T> T initWeatherService(String weatherApiHost, Class<T> clazz) {

        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
            httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            builder.addInterceptor(httpLoggingInterceptor).addNetworkInterceptor(new StethoInterceptor());
        }
        OkHttpClient client = builder.build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(weatherApiHost)
                .addConverterFactory(FastJsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .client(client)
                .build();

        return retrofit.create(clazz);
    }
}
