package com.example.zhouyiran.mysample;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by zhouyiran on 2017/7/19.
 */

@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {

    WeatherApplication getApplication();

    Context getContext();
}
