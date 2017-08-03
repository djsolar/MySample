package com.example.daggertest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import javax.inject.Inject;


public class MainActivity extends AppCompatActivity {

    @Inject
    Pot pot;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DaggerMainActivityComponent.builder().potComponent(((AppApplication)getApplication()).getPotComponent()).build().inject(this);

        System.out.println("还未初始化");

        System.out.println("pot = " + pot);
    }
}
