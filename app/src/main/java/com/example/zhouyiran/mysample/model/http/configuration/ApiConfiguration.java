package com.example.zhouyiran.mysample.model.http.configuration;

import com.example.zhouyiran.mysample.model.http.ApiConstant;

/**
 * Created by zhouyiran on 2017/7/19.
 */

public class ApiConfiguration {

    private int dataSourceType;

    public ApiConfiguration(Builder builder) {
        initialize(builder);
    }

    private void initialize(Builder builder) {
        this.dataSourceType = builder.dataSourceType;
    }

    public static Builder builder() {
        return new Builder();
    }

    public int getDataSourceType() {
        return dataSourceType;
    }

    public static final class Builder {

        private int dataSourceType;

        private Builder() {
        }

        public ApiConfiguration build() {

            if (dataSourceType != ApiConstant.WEATHER_DATA_SOURCE_TYPE_MI && dataSourceType != ApiConstant.WEATHER_DATA_SOURCE_TYPE_ENVIRONMENT_CLOUD
                    && dataSourceType != ApiConstant.WEATHER_DATA_SOURCE_TYPE_KNOW) {
                throw new IllegalStateException("The dataSourceType does not support!");
            }
            return new ApiConfiguration(this);
        }

        public Builder datasourceType(int dataSourceType) {
            this.dataSourceType = dataSourceType;
            return this;
        }
    }
}
