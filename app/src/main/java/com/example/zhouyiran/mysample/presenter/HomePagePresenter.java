package com.example.zhouyiran.mysample.presenter;

import android.content.Context;
import android.widget.Toast;

import com.example.library.utils.RxSchedulerUtils;
import com.example.zhouyiran.mysample.ApplicationModule;
import com.example.zhouyiran.mysample.contract.HomePageContract;
import com.example.zhouyiran.mysample.model.db.dao.WeatherDao;
import com.example.zhouyiran.mysample.model.preference.PreferenceHelper;
import com.example.zhouyiran.mysample.model.preference.WeatherSetting;
import com.example.zhouyiran.mysample.model.repository.WeatherDataRepository;
import com.example.zhouyiran.mysample.presenter.component.DaggerPresenterComponent;

import javax.inject.Inject;

import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by zhouyiran on 2017/7/20.
 */

public class HomePagePresenter implements HomePageContract.Presenter {

    private final Context context;

    private HomePageContract.View view;

    private CompositeSubscription compositeSubscription;

    @Inject
    WeatherDao weatherDao;

    @Inject
    public HomePagePresenter(Context context, HomePageContract.View view) {
        this.context = context;
        this.view = view;
        this.compositeSubscription = new CompositeSubscription();
        view.setPresenter(this);

        DaggerPresenterComponent.builder().applicationModule(new ApplicationModule(context))
                .build().inject(this);
    }

    @Override
    public void subscribe() {
        String cityId = PreferenceHelper.getSharedPreferences().getString(WeatherSetting.SETTING_CURRENT_CITY_ID.getmId(), "");
        loadWeather(cityId);
    }

    @Override
    public void unSubscribe() {
        compositeSubscription.clear();
    }

    @Override
    public void loadWeather(String cityId) {
        Subscription subscription = WeatherDataRepository.getWeather(context, cityId, weatherDao).compose(RxSchedulerUtils.normalSchedulersTransformer())
                .subscribe(view::showWeather, throwable -> {
                    Toast.makeText(context, throwable.getMessage(), Toast.LENGTH_LONG).show();
                });
        compositeSubscription.add(subscription);
    }
}
