package com.example.zhouyiran.mysample.model.db.dao;

import android.content.Context;

import com.example.zhouyiran.mysample.model.db.CityDatabaseHelper;
import com.example.zhouyiran.mysample.model.db.entities.City;
import com.example.zhouyiran.mysample.model.db.entities.HotCity;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.QueryBuilder;

import java.sql.SQLException;
import java.util.List;

import javax.inject.Inject;

/**
 * Created by zhouyiran on 2017/7/19.
 */

public class CityDao {

    private Dao<City, Integer> cityDaoOperation;

    private Dao<HotCity, Integer> hotCityDaoOperation;

    @Inject
    CityDao(Context context) {
        cityDaoOperation = CityDatabaseHelper.getInstance(context).getCityDao(City.class);
        hotCityDaoOperation = CityDatabaseHelper.getInstance(context).getCityDao(HotCity.class);
    }

    /**
     * 查询城市列表
     *
     * @return 城市列表
     */
    public List<City> queryCityList() {
        try {
            return cityDaoOperation.queryForAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     *
     * @param currentId 当前的城市id
     * @return 返回当前城市
     * @throws SQLException
     */
    public City queryCityById(String currentId) throws SQLException {
        QueryBuilder<City, Integer> queryBuilder = cityDaoOperation.queryBuilder();
        queryBuilder.where().eq(City.CITY_ID_FIELD_NAME, currentId);
        return queryBuilder.queryForFirst();
    }

    /**
     * 获取所有的热门城市
     *
     * @return 返回热门城市列表
     */
    public List<HotCity> queryAllHostCity() {
        try {
            return hotCityDaoOperation.queryForAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
