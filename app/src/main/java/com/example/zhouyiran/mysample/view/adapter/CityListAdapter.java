package com.example.zhouyiran.mysample.view.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import com.annimon.stream.Stream;
import com.example.library.adapter.BaseRecyclerViewAdapter;
import com.example.zhouyiran.mysample.R;
import com.example.zhouyiran.mysample.model.db.entities.City;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by zhouyiran on 2017/7/20.
 */

public class CityListAdapter extends BaseRecyclerViewAdapter<CityListAdapter.ViewHolder> implements Filterable{

    private List<City> cities;

    public List<City> mFilterData;

    private RecyclerFilter mFilter;

    public CityListAdapter(List<City> cities) {
        this.cities = cities;
        this.mFilterData = cities;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_city, parent, false);
        return new ViewHolder(view, this);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        City city = mFilterData.get(position);
        String cityName = city.getCityName();
        String parentName = city.getParent();
        if (!cityName.equals(parentName)) {
            cityName = parentName + "." + cityName;
        }
        holder.cityNameTextView.setText(cityName);
    }

    @Override
    public int getItemCount() {
        return mFilterData == null ? 0 : mFilterData.size();
    }

    @Override
    public Filter getFilter() {
        if (mFilter == null) {
            mFilter = new RecyclerFilter();
        }
        return mFilter;
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.city_name_text_view)
        TextView cityNameTextView;

        public ViewHolder(View itemView, CityListAdapter cityListAdapter) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(view -> cityListAdapter.onItemHolderClick(this));
        }
    }

    private class RecyclerFilter extends Filter {

        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {

            FilterResults results = new FilterResults();

            if (charSequence == null || charSequence.length() == 0) {
                results.values = null;
                results.count = 0;
            } else {
                String prefixString = charSequence.toString().toLowerCase();
                ArrayList<City> newValues = new ArrayList<>();
                Stream.of(cities).filter(city -> city.getCityName().contains(prefixString) || city.getParent().contains(prefixString)
                    || city.getCityNameEn().contains(prefixString) || city.getRoot().contains(prefixString))
                        .forEach(newValues::add);
                results.values = newValues;
                results.count = newValues.size();
            }
            return results;
        }

        @Override
        protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
            mFilterData = (List<City>) filterResults.values;
            if (filterResults.count > 0) {
                notifyDataSetChanged();
            } else {
                mFilterData = cities;
                notifyDataSetChanged();
            }
        }
    }
}
