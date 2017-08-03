package com.example.daggertest;

import dagger.Module;
import dagger.Provides;

/**
 * Created by zhouyiran on 2017/7/17.
 */

@Module
public class FlowerModule {

    @Provides
    @RoseFlower
    Flower providerRose() {
        return new Rose();
    }

    @Provides
    @LilyFlower
    Flower provideLily() {
        return new Lily();
    }
}
