package com.example.zhouyiran.mysample;

import android.app.Application;

import com.example.zhouyiran.mysample.model.http.ApiClient;
import com.example.zhouyiran.mysample.model.http.ApiConstant;
import com.example.zhouyiran.mysample.model.http.configuration.ApiConfiguration;
import com.facebook.stetho.Stetho;

/**
 * Created by zhouyiran on 2017/7/18.
 */

public class WeatherApplication extends Application {

    private static WeatherApplication weatherApplication;

    private ApplicationComponent applicationComponent;

    public static WeatherApplication getInstance() {
        return weatherApplication;
    }

    @Override
    public void onCreate() {
        super.onCreate();

       /* if (BuildConfig.DEBUG) {
            StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder().detectAll().penaltyLog().build());
            StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder().detectAll().penaltyLog().build());
        }
*/
        applicationComponent = DaggerApplicationComponent.builder().applicationModule(new ApplicationModule(this)).build();
        Stetho.initializeWithDefaults(this);

        weatherApplication = this;

        ApiConfiguration apiConfiguration = ApiConfiguration.builder()
                .datasourceType(ApiConstant.WEATHER_DATA_SOURCE_TYPE_MI)
                .build();
        ApiClient.init(apiConfiguration);
    }

    public ApplicationComponent getApplicationComponent() {
        return applicationComponent;
    }
}
