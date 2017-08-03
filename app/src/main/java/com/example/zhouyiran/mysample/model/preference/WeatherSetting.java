package com.example.zhouyiran.mysample.model.preference;

/**
 * Created by zhouyiran on 2017/7/18.
 */

public enum WeatherSetting {

    SETTINGS_FIRST_USE("first_use", Boolean.TRUE),
    SETTING_CURRENT_CITY_ID("current_city_id", "");

    private String mId;

    private Object defaultValue;

    WeatherSetting(String mId, Object defaultValue) {
        this.mId = mId;
        this.defaultValue = defaultValue;
    }

    public String getmId() {
        return mId;
    }

    public Object getDefaultValue() {
        return defaultValue;
    }

    public static WeatherSetting fromId(String id) {
        WeatherSetting[] values = values();
        for(WeatherSetting value : values) {
            if (value.getmId().equals(id)) {
                return value;
            }
        }
        return null;
    }
}
