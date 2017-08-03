package com.example.zhouyiran.mysample.presenter;

import android.content.Context;

import com.example.zhouyiran.mysample.ApplicationModule;
import com.example.zhouyiran.mysample.contract.CityManagerContract;
import com.example.zhouyiran.mysample.model.db.dao.WeatherDao;
import com.example.zhouyiran.mysample.model.db.entities.minimalist.Weather;
import com.example.zhouyiran.mysample.model.preference.PreferenceHelper;
import com.example.zhouyiran.mysample.model.preference.WeatherSetting;
import com.example.zhouyiran.mysample.presenter.component.DaggerPresenterComponent;

import java.io.InvalidClassException;
import java.sql.SQLException;
import java.util.List;

import javax.inject.Inject;

import rx.Observable;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by zhouyiran on 2017/7/21.
 */

public class CityManagerPresenter implements CityManagerContract.Presenter {

    private CityManagerContract.View view;

    private CompositeSubscription compositeSubscription;

    @Inject
    WeatherDao weatherDao;

    @Inject
    public CityManagerPresenter(Context context, CityManagerContract.View view) {
        this.view = view;
        compositeSubscription = new CompositeSubscription();
        view.setPresenter(this);
        DaggerPresenterComponent.builder().applicationModule(new ApplicationModule(context))
                .build().inject(this);
    }

    @Override
    public void subscribe() {
        loadSavedCities();
    }

    @Override
    public void unSubscribe() {
        compositeSubscription.clear();
    }

    @Override
    public void loadSavedCities() {
        try {
            Subscription subscription = Observable.just(weatherDao.queryAllSaveCity())
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(weathers -> view.displaySavedCities(weathers));
            compositeSubscription.add(subscription);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteCityId(String cityId) {
        Observable.just(deleteCityFromDBAndReturnCurrentCityId(cityId)).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(currentId-> {
                   if (currentId == null) {
                       return;
                   }
                    try {
                        PreferenceHelper.savePreference(WeatherSetting.SETTING_CURRENT_CITY_ID, cityId);
                    } catch (InvalidClassException e) {
                        e.printStackTrace();
                    }
                });
    }

    private String deleteCityFromDBAndReturnCurrentCityId(String cityId) {
        String currentId = PreferenceHelper.getSharedPreferences().getString(WeatherSetting.SETTING_CURRENT_CITY_ID.getmId(), "");
        try {
            weatherDao.deleteById(currentId);
            if (cityId.equals(currentId)) {
                List<Weather> weathers = weatherDao.queryAllSaveCity();
                if (weathers != null && weathers.size() > 0) {
                    currentId = weathers.get(0).getCityId();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return currentId;
    }

    @Override
    public void saveCurrencyCityToPreference(String cityId) throws InvalidClassException {
        PreferenceHelper.savePreference(WeatherSetting.SETTING_CURRENT_CITY_ID, cityId);
    }
}
