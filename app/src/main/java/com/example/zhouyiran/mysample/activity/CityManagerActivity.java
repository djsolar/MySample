package com.example.zhouyiran.mysample.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.example.library.utils.ActivityUtils;
import com.example.zhouyiran.mysample.R;
import com.example.zhouyiran.mysample.WeatherApplication;
import com.example.zhouyiran.mysample.activity.component.DaggerCityManagerComponent;
import com.example.zhouyiran.mysample.activity.module.CityManagerModule;
import com.example.zhouyiran.mysample.contract.CityManagerContract;
import com.example.zhouyiran.mysample.presenter.CityManagerPresenter;
import com.example.zhouyiran.mysample.view.fragment.CityManagerFragment;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CityManagerActivity extends AppCompatActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @Inject
    CityManagerPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_city_manager);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        CityManagerFragment cityManagerFragment = CityManagerFragment.newInstance(3);
        ActivityUtils.addFragmentToActivity(getSupportFragmentManager(), cityManagerFragment, R.id.fragment_container);

        DaggerCityManagerComponent.builder().applicationComponent(WeatherApplication.getInstance().getApplicationComponent())
                .cityManagerModule(new CityManagerModule(cityManagerFragment))
                .build()
                .inject(this);
    }
}
