package com.example.zhouyiran.mysample.contract;

import com.example.library.presenter.BasePresenter;
import com.example.library.view.BaseView;
import com.example.zhouyiran.mysample.model.db.entities.minimalist.Weather;

import java.io.InvalidClassException;
import java.util.List;

/**
 * Created by zhouyiran on 2017/7/20.
 */

public interface CityManagerContract {

    interface View extends BaseView<Presenter> {
        void displaySavedCities(List<Weather> weathers);
    }


    interface Presenter extends BasePresenter {

        void loadSavedCities();

        void deleteCityId(String cityId);

        void saveCurrencyCityToPreference(String cityId) throws InvalidClassException;
    }
}
