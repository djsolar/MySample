package com.example.zhouyiran.mysample.presenter.component;

import com.example.zhouyiran.mysample.ApplicationModule;
import com.example.zhouyiran.mysample.presenter.CityManagerPresenter;
import com.example.zhouyiran.mysample.presenter.HomePagePresenter;
import com.example.zhouyiran.mysample.presenter.SelectCityPresenter;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by zhouyiran on 2017/7/20.
 */

@Singleton
@Component(modules = {ApplicationModule.class})
public interface PresenterComponent {

    void inject(HomePagePresenter homePagePresenter);

    void inject(SelectCityPresenter selectCityPresenter);

    void inject(CityManagerPresenter cityManagerPresenter);

}
