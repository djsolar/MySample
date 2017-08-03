package com.example.daggertest;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by zhouyiran on 2017/7/17.
 */

@Module
public class PotModule {

    @Provides
    @Singleton
    public Pot providePot(@RoseFlower Flower flower) {
        return new Pot(flower);
    }
}
