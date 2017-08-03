package com.example.daggertest;

import android.app.Application;

/**
 * Created by zhouyiran on 2017/7/18.
 */

public class AppApplication extends Application {

    private PotComponent potComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        potComponent = DaggerPotComponent.builder().flowerComponent(DaggerFlowerComponent.create()).build();
    }

    public PotComponent getPotComponent() {
        return potComponent;
    }
}
