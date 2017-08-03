package com.example.zhouyiran.mysample.activity.component;

import com.example.zhouyiran.mysample.ApplicationComponent;
import com.example.zhouyiran.mysample.activity.SelectCityActivity;
import com.example.zhouyiran.mysample.activity.module.SelectCityModule;
import com.example.zhouyiran.mysample.util.ActivityScoped;

import dagger.Component;

/**
 * Created by zhouyiran on 2017/7/20.
 */

@ActivityScoped
@Component(modules = SelectCityModule.class, dependencies = ApplicationComponent.class)
public interface SelectCityComponent {

    void inject(SelectCityActivity selectCityActivity);
}
