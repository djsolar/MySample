package com.example.zhouyiran.mysample.activity.module;

import com.example.zhouyiran.mysample.contract.HomePageContract;

import dagger.Module;
import dagger.Provides;

/**
 * Created by zhouyiran on 2017/7/20.
 */

@Module
public class HomePageModule {

    private HomePageContract.View view;

    public HomePageModule(HomePageContract.View view) {
        this.view = view;
    }

    @Provides
    HomePageContract.View provideHomePageContractView() {
        return this.view;
    }
}
