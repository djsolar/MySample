package com.example.zhouyiran.mysample.contract;

import com.example.library.presenter.BasePresenter;
import com.example.library.view.BaseView;
import com.example.zhouyiran.mysample.model.db.entities.minimalist.Weather;

/**
 * Created by zhouyiran on 2017/7/19.
 */

public interface HomePageContract {

    interface View extends BaseView<Presenter> {
        void showWeather(Weather weather);
    }

    interface Presenter extends BasePresenter {
        void loadWeather(String cityId);
    }
}
