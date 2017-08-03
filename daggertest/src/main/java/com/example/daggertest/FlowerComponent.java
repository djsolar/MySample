package com.example.daggertest;

import dagger.Component;

/**
 * Created by zhouyiran on 2017/7/17.
 */

@Component(modules = FlowerModule.class)
public interface FlowerComponent {

    @RoseFlower
    Flower getRoseFlower();

    @LilyFlower
    Flower getLilyFlower();
}
