package com.example.zhouyiran.mysample.model.preference;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.example.zhouyiran.mysample.WeatherApplication;

import java.io.InvalidClassException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.BooleanSupplier;

/**
 * Created by zhouyiran on 2017/7/18.
 */

public final class PreferenceHelper {

    private static final String TAG = "PreferenceHelper";

    private static final String SETTING_NAME = WeatherApplication.class.getPackage().getName();

    private static final List<ConfigurationListener> CONFIGURATION_LISTENERS = Collections.synchronizedList(new ArrayList<>());

    private PreferenceHelper() {
    }

    public static void loadDefaults() {

        try {
            Map<WeatherSetting, Object> defaultPrefs = new HashMap<>();
            WeatherSetting[] values = WeatherSetting.values();
            for(WeatherSetting value : values) {
                defaultPrefs.put(value, value.getDefaultValue());
            }
            savePreference(defaultPrefs, true);
        } catch (InvalidClassException e) {
            e.printStackTrace();
            Log.i(TAG, "Save default setting fails.");
        }
    }

    public void addConfigurationListener(ConfigurationListener listener) {
        CONFIGURATION_LISTENERS.add(listener);
    }

    public void removeConfigurationListener(ConfigurationListener listener) {
        CONFIGURATION_LISTENERS.remove(listener);
    }

    public static SharedPreferences getSharedPreferences() {
        return WeatherApplication.getInstance().getSharedPreferences(SETTING_NAME, Context.MODE_PRIVATE);
    }

    public static void savePreference(WeatherSetting weatherSetting, Object value) throws InvalidClassException {
        Map<WeatherSetting, Object> prefs = new HashMap<>();
        prefs.put(weatherSetting, value);
        savePreference(prefs, false);
    }

    public static void savePreference(Map<WeatherSetting, Object> prefs) throws InvalidClassException {
        savePreference(prefs, false);
    }

    private static void savePreference(Map<WeatherSetting, Object> prefs, boolean noSaveIfExist) throws InvalidClassException {
        SharedPreferences sp = getSharedPreferences();
        SharedPreferences.Editor editor = sp.edit();

        for(WeatherSetting weatherSetting : prefs.keySet()) {

            Object value = prefs.get(weatherSetting);

            if (noSaveIfExist && sp.contains(weatherSetting.getmId())) {
                continue;
            }

            if (value instanceof Boolean && weatherSetting.getDefaultValue() instanceof Boolean) {
                editor.putBoolean(weatherSetting.getmId(), (Boolean) value);
            } else if (value instanceof String && weatherSetting.getDefaultValue() instanceof String) {
                editor.putString(weatherSetting.getmId(), (String) value);
            } else if (value instanceof Integer && weatherSetting.getDefaultValue() instanceof Integer) {
                editor.putInt(weatherSetting.getmId(), (Integer) value);
            } else if (value instanceof Float && weatherSetting.getDefaultValue() instanceof Float) {
                editor.putFloat(weatherSetting.getmId(), (Float) value);
            } else if (value instanceof Long && weatherSetting.getDefaultValue() instanceof Long) {
                editor.putLong(weatherSetting.getmId(), (Long) value);
            } else if (value instanceof Set && weatherSetting.getDefaultValue() instanceof Set) {
                editor.putStringSet(weatherSetting.getmId(), (Set<String>) value);
            } else {
                String msg = String.format("%s: %s", weatherSetting.getmId(), value.getClass().getName());
                throw new InvalidClassException(msg);
            }
        }
        editor.apply();

        if (CONFIGURATION_LISTENERS != null && CONFIGURATION_LISTENERS.size() > 0) {
            for(WeatherSetting pref : prefs.keySet()) {
                for(ConfigurationListener listener : CONFIGURATION_LISTENERS) {
                    listener.onConfigurationChanged(pref, prefs.get(pref));
                }
            }
        }

    }


}
