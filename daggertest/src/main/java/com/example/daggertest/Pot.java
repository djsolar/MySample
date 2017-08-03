package com.example.daggertest;


/**
 * Created by zhouyiran on 2017/7/17.
 */

public class Pot {

    private Flower flower;

    public Pot(Flower flower) {
        this.flower = flower;
        System.out.println("初始化");
    }

    public String show() {
        return flower.whisper();
    }
}
