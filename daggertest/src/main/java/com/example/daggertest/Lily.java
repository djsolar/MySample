package com.example.daggertest;

import javax.inject.Inject;

/**
 * Created by zhouyiran on 2017/7/17.
 */

public class Lily extends Flower {

    public Lily() {
    }

    @Override
    public String whisper() {
        return "纯洁";
    }
}
