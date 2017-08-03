package com.example.zhouyiran.mysample.presenter;

import android.content.Context;

import com.example.zhouyiran.mysample.ApplicationModule;
import com.example.zhouyiran.mysample.contract.SelectCityContract;
import com.example.zhouyiran.mysample.model.db.dao.CityDao;
import com.example.zhouyiran.mysample.presenter.component.DaggerPresenterComponent;

import javax.inject.Inject;

import rx.Observable;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by zhouyiran on 2017/7/20.
 */

public class SelectCityPresenter implements SelectCityContract.Presenter {

    private final SelectCityContract.View view;

    private CompositeSubscription compositeSubscription;

    @Inject
    CityDao cityDao;

    @Inject
    public SelectCityPresenter(Context context, SelectCityContract.View view) {
        this.view = view;
        compositeSubscription = new CompositeSubscription();
        view.setPresenter(this);

        DaggerPresenterComponent.builder().applicationModule(new ApplicationModule(context)).build().inject(this);
    }

    @Override
    public void subscribe() {
        loadCities();
    }

    @Override
    public void unSubscribe() {
        compositeSubscription.unsubscribe();
    }

    @Override
    public void loadCities() {
        Subscription subscription = Observable.just(cityDao.queryCityList())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(view::displayCities);
        compositeSubscription.add(subscription);
    }
}
