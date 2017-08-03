package com.example.daggertest;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Qualifier;

/**
 * Created by zhouyiran on 2017/7/17.
 */
@Qualifier
@Retention(RetentionPolicy.RUNTIME)
public @interface LilyFlower {
}
