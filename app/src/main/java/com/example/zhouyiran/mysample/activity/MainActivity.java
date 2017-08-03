package com.example.zhouyiran.mysample.activity;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.example.library.activity.BaseActivity;
import com.example.library.utils.ActivityUtils;
import com.example.zhouyiran.mysample.R;
import com.example.zhouyiran.mysample.WeatherApplication;
import com.example.zhouyiran.mysample.activity.component.DaggerHomePageComponent;
import com.example.zhouyiran.mysample.activity.module.HomePageModule;
import com.example.zhouyiran.mysample.presenter.HomePagePresenter;
import com.example.zhouyiran.mysample.view.fragment.HomePageFragment;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity implements NavigationView.OnNavigationItemSelectedListener, HomePageFragment.OnFragmentInteractionListener {

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.main_content)
    DrawerLayout drawerLayout;

    @Inject
    HomePagePresenter homePagePresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);
        setSupportActionBar(toolbar);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        HomePageFragment homePageFragment = HomePageFragment.newInstance();
        ActivityUtils.addFragmentToActivity(getSupportFragmentManager(), homePageFragment, R.id.fragment_container);

        DaggerHomePageComponent.builder().applicationComponent(WeatherApplication.getInstance().getApplicationComponent())
                .homePageModule(new HomePageModule(homePageFragment)).build().inject(this);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.nav_camera:
                break;

            case R.id.nav_gallery:
                break;

            case R.id.nav_tool:
                break;

            case R.id.nav_slide:
                break;

            case R.id.nav_share:
                break;

            case R.id.nav_feed:
                break;
        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onBackPressed() {

        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

     @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.setting:
                break;

            case R.id.city_select:
                Intent selectIntent = new Intent(MainActivity.this, SelectCityActivity.class);
                startActivity(selectIntent);
                break;

            case R.id.city_manage:
                Intent manageIntent = new Intent(MainActivity.this, CityManagerActivity.class);
                startActivity(manageIntent);
                break;
        }
        return true;
    }

    @Override
    public void updatePageTitle(String title) {
        toolbar.setTitle(title);
    }
}
