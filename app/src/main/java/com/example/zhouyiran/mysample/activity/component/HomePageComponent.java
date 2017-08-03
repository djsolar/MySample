package com.example.zhouyiran.mysample.activity.component;

import com.example.zhouyiran.mysample.ApplicationComponent;
import com.example.zhouyiran.mysample.activity.MainActivity;
import com.example.zhouyiran.mysample.activity.module.HomePageModule;
import com.example.zhouyiran.mysample.util.ActivityScoped;

import dagger.Component;

/**
 * Created by zhouyiran on 2017/7/20.
 */

@ActivityScoped
@Component(modules = HomePageModule.class, dependencies = ApplicationComponent.class)
public interface HomePageComponent {

    void inject(MainActivity mainActivity);
}
