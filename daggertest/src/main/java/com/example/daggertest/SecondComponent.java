package com.example.daggertest;

import dagger.Component;

/**
 * Created by zhouyiran on 2017/7/18.
 */

@ActivityScope
@Component(dependencies = PotComponent.class)
public interface SecondComponent {

    void inject(SecondActivity secondActivity);
}
