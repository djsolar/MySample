package com.example.zhouyiran.mysample.activity;

import android.content.Intent;
import android.os.Bundle;

import com.example.library.activity.BaseActivity;
import com.example.library.utils.system.StatusBarHelper;
import com.example.zhouyiran.mysample.R;
import com.example.zhouyiran.mysample.model.preference.PreferenceHelper;
import com.example.zhouyiran.mysample.model.preference.WeatherSetting;
import com.example.zhouyiran.mysample.util.CityDbUtil;

import java.io.InvalidClassException;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class WelcomeActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        // StatusBarHelper.statusBarLightMode(this);
        Observable.just(initAppData()).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(result -> goToMainPage());
    }

    private void goToMainPage() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    private String initAppData() {

        PreferenceHelper.loadDefaults();
        if (PreferenceHelper.getSharedPreferences().getBoolean(WeatherSetting.SETTINGS_FIRST_USE.getmId(), false)) {
            try {
                PreferenceHelper.savePreference(WeatherSetting.SETTINGS_FIRST_USE, false);
                PreferenceHelper.savePreference(WeatherSetting.SETTING_CURRENT_CITY_ID, "101020100");
            } catch (InvalidClassException e) {
                e.printStackTrace();
            }
        }
        CityDbUtil.importCityData();
        return null;
    }
}
