package com.example.zhouyiran.mysample.model.preference;

/**
 * Created by zhouyiran on 2017/7/18.
 */

public interface ConfigurationListener {

    void onConfigurationChanged(WeatherSetting pref, Object newValue);
}
