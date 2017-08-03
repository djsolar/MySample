package com.example.daggertest;

import dagger.Component;

/**
 * Created by zhouyiran on 2017/7/17.
 */
@ActivityScope
@Component(dependencies = PotComponent.class)
public interface MainActivityComponent {

    void inject(MainActivity activity);
}
