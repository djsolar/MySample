package com.example.zhouyiran.mysample.contract;

import com.example.library.presenter.BasePresenter;
import com.example.library.view.BaseView;
import com.example.zhouyiran.mysample.model.db.entities.City;

import java.util.List;

/**
 * Created by zhouyiran on 2017/7/20.
 */

public interface SelectCityContract {

    interface View extends BaseView<Presenter> {

        void displayCities(List<City> cities);
    }


    interface Presenter extends BasePresenter {

        void loadCities();

    }
}
