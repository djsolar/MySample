package com.example.zhouyiran.mysample.util;

import com.example.zhouyiran.mysample.AppConstants;
import com.example.zhouyiran.mysample.R;
import com.example.zhouyiran.mysample.WeatherApplication;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;

/**
 * Created by zhouyiran on 2017/7/18.
 */

public class CityDbUtil {

    public static void importCityData() {
        File file = new File(WeatherApplication.getInstance().getDatabasePath(AppConstants.DB_NAME_CITY).getAbsolutePath());
        if (!file.exists()) {
            File dbFile = WeatherApplication.getInstance().getDatabasePath(AppConstants.DB_NAME_CITY);
            try {
                if (!dbFile.getParentFile().exists()) {
                    dbFile.getParentFile().mkdir();
                }
                if (!dbFile.exists()) {
                    dbFile.createNewFile();
                }
                InputStream is = WeatherApplication.getInstance().getResources().openRawResource(R.raw.city);
                FileOutputStream fos = new FileOutputStream(dbFile);
                byte[] buffer = new byte[is.available()];
                is.read(buffer);
                fos.write(buffer);
                is.close();
                fos.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
