package com.example.daggertest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import javax.inject.Inject;

import dagger.Lazy;

public class SecondActivity extends AppCompatActivity {

    @Inject
    Lazy<Pot> pot3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        DaggerSecondComponent.builder().potComponent(((AppApplication)getApplication()).getPotComponent()).build().inject(this);

        System.out.println("pot3 = " + pot3.get());
    }
}
