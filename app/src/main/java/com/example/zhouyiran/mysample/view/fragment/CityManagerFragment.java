package com.example.zhouyiran.mysample.view.fragment;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import com.example.library.fragment.BaseFragment;
import com.example.zhouyiran.mysample.R;
import com.example.zhouyiran.mysample.activity.CityManagerActivity;
import com.example.zhouyiran.mysample.activity.SelectCityActivity;
import com.example.zhouyiran.mysample.contract.CityManagerContract;
import com.example.zhouyiran.mysample.model.db.entities.minimalist.Weather;
import com.example.zhouyiran.mysample.view.adapter.CityManagerAdapter;

import java.io.InvalidClassException;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class CityManagerFragment extends BaseFragment implements CityManagerContract.View {

    public static final String ARG_COLUMN_COUNT = "column-count";

    @BindView(R.id.rv_city_manager)
    RecyclerView recyclerView;

    @BindView(R.id.fab)
    FloatingActionButton fab;

    private Unbinder unbinder;

    private int columnCount = 3;

    private List<Weather> weathers;

    private CityManagerAdapter cityManagerAdapter;

    CityManagerContract.Presenter presenter;

    public CityManagerFragment() {
    }

    public static CityManagerFragment newInstance(int columnCount) {
        CityManagerFragment fragment = new CityManagerFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(ARG_COLUMN_COUNT, columnCount);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            columnCount = getArguments().getInt(ARG_COLUMN_COUNT);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_city_manager, container, false);
        unbinder = ButterKnife.bind(this, root);

        Context contex = getContext();
        if (columnCount <= 1) {
            recyclerView.setLayoutManager(new LinearLayoutManager(contex));
        } else {
            recyclerView.setLayoutManager(new GridLayoutManager(contex, columnCount));
        }
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        weathers = new ArrayList<>();
        cityManagerAdapter = new CityManagerAdapter(weathers);
        cityManagerAdapter.setOnItemClickListener(new CityManagerAdapter.OnCityManagerItemClickListener() {
            @Override
            public void onDeleteClick(String cityId) {
                presenter.deleteCityId(cityId);
            }

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                try {
                    presenter.saveCurrencyCityToPreference(weathers.get(i).getCityId() + "");
                    CityManagerFragment.this.getActivity().finish();
                } catch (InvalidClassException e) {
                    e.printStackTrace();
                }
            }
        });
        recyclerView.setAdapter(cityManagerAdapter);
        presenter.subscribe();
        return root;
    }

    @Override
    public void setPresenter(CityManagerContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void displaySavedCities(List<Weather> weathers) {
        this.weathers.addAll(weathers);
        cityManagerAdapter.notifyDataSetChanged();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
        presenter.unSubscribe();
    }

    @OnClick(R.id.fab)
    public void onFabCallback() {
        Intent intent = new Intent(getActivity(), SelectCityActivity.class);
        startActivity(intent);
        getActivity().finish();
    }
}
