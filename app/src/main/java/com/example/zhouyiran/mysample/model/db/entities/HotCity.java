package com.example.zhouyiran.mysample.model.db.entities;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by zhouyiran on 2017/7/19.
 */

@DatabaseTable(tableName = "HotCity")
public class HotCity {

    public static final String ID_FIELD_NAME = "_id";
    public static final String CITY_NAME_FIELD_NAME = "name";
    public static final String CITY_ID_FIELD_NAME = "posID";

    @DatabaseField(columnName = ID_FIELD_NAME)
    private int id;
    @DatabaseField(columnName = CITY_ID_FIELD_NAME)
    private int cityId;
    @DatabaseField(columnName = CITY_NAME_FIELD_NAME)
    private String cityName;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCityId() {
        return cityId;
    }

    public void setCityId(int cityId) {
        this.cityId = cityId;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    @Override
    public String toString() {
        return "HotCity{" +
                "id=" + id +
                ", cityId=" + cityId +
                ", cityName='" + cityName + '\'' +
                '}';
    }
}
