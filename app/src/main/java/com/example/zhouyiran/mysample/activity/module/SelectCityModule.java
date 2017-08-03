package com.example.zhouyiran.mysample.activity.module;

import com.example.zhouyiran.mysample.contract.SelectCityContract;

import dagger.Module;
import dagger.Provides;

/**
 * Created by zhouyiran on 2017/7/20.
 */

@Module
public class SelectCityModule {

    private SelectCityContract.View view;

    public SelectCityModule(SelectCityContract.View view) {
        this.view = view;
    }

    @Provides
    SelectCityContract.View provideSelectCityContractView() {
        return this.view;
    }
}
