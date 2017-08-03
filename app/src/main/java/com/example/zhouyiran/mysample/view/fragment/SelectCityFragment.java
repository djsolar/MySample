package com.example.zhouyiran.mysample.view.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Toast;

import com.example.library.fragment.BaseFragment;
import com.example.zhouyiran.mysample.R;
import com.example.zhouyiran.mysample.contract.SelectCityContract;
import com.example.zhouyiran.mysample.model.db.entities.City;
import com.example.zhouyiran.mysample.model.preference.PreferenceHelper;
import com.example.zhouyiran.mysample.model.preference.WeatherSetting;
import com.example.zhouyiran.mysample.view.adapter.CityListAdapter;
import com.example.zhouyiran.mysample.view.widget.DividerItemDecoration;

import java.io.InvalidClassException;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link SelectCityFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link SelectCityFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SelectCityFragment extends BaseFragment implements SelectCityContract.View{

    private Unbinder unbinder;

    public List<City> cities;

    public CityListAdapter cityListAdapter;

    @BindView(R.id.rv_city_list)
    RecyclerView recyclerView;

    private SelectCityContract.Presenter mPresenter;

    public SelectCityFragment() {
    }

    public static SelectCityFragment newInstance() {
        SelectCityFragment fragment = new SelectCityFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_select_city, container, false);
        unbinder = ButterKnife.bind(this, rootView);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL_LIST));

        cities = new ArrayList<>();
        cityListAdapter = new CityListAdapter(cities);
        cityListAdapter.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                City selectCity = cityListAdapter.mFilterData.get(i);
                try {
                    PreferenceHelper.savePreference(WeatherSetting.SETTING_CURRENT_CITY_ID, selectCity.getCityId() + "");
                    Toast.makeText(getActivity(), selectCity.getCityName(), Toast.LENGTH_SHORT).show();
                    getActivity().finish();
                } catch (InvalidClassException e) {
                    e.printStackTrace();
                }
            }
        });
        recyclerView.setAdapter(cityListAdapter);
        mPresenter.subscribe();
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
        mPresenter.unSubscribe();
    }

    @Override
    public void setPresenter(SelectCityContract.Presenter presenter) {
        this.mPresenter = presenter;
    }

    @Override
    public void displayCities(List<City> cities) {
        this.cities.addAll(cities);
        cityListAdapter.notifyDataSetChanged();
    }
}
