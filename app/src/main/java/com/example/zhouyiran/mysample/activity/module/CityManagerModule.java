package com.example.zhouyiran.mysample.activity.module;

import com.example.zhouyiran.mysample.contract.CityManagerContract;

import javax.inject.Inject;

import dagger.Module;
import dagger.Provides;

/**
 * Created by zhouyiran on 2017/7/21.
 */

@Module
public class CityManagerModule {

    private CityManagerContract.View view;

    @Inject
    public CityManagerModule(CityManagerContract.View view) {
        this.view = view;
    }

    @Provides
    CityManagerContract.View privodeCityManagerView() {
        return this.view;
    }
}
