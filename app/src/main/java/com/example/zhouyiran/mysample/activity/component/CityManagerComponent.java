package com.example.zhouyiran.mysample.activity.component;

import com.example.zhouyiran.mysample.ApplicationComponent;
import com.example.zhouyiran.mysample.activity.CityManagerActivity;
import com.example.zhouyiran.mysample.activity.module.CityManagerModule;
import com.example.zhouyiran.mysample.util.ActivityScoped;

import dagger.Component;

/**
 * Created by zhouyiran on 2017/7/21.
 */

@ActivityScoped
@Component(modules = CityManagerModule.class, dependencies = ApplicationComponent.class)
public interface CityManagerComponent {

    void inject(CityManagerActivity activity);
}
