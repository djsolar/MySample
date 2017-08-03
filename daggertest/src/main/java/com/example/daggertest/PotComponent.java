package com.example.daggertest;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by zhouyiran on 2017/7/17.
 */
@Singleton
@Component(modules = PotModule.class, dependencies = FlowerComponent.class)
public interface PotComponent {
    Pot getPot();
}
