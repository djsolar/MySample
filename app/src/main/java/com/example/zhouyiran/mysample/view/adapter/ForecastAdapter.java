package com.example.zhouyiran.mysample.view.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.library.adapter.BaseRecyclerViewAdapter;
import com.example.zhouyiran.mysample.R;
import com.example.zhouyiran.mysample.model.db.entities.minimalist.WeatherForecast;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by zhouyiran on 2017/7/20.
 */

public class ForecastAdapter extends BaseRecyclerViewAdapter<ForecastAdapter.ViewHolder> {

    private List<WeatherForecast> weatherForecasts;

    public ForecastAdapter(List<WeatherForecast> weatherForecast) {
        this.weatherForecasts = weatherForecast;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_forecast, parent, false);
        return new ViewHolder(view, this);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        WeatherForecast weatherForecast = weatherForecasts.get(position);
        holder.weekTextView.setText(weatherForecast.getWeek());
        holder.weatherIconImageView.setImageResource(R.mipmap.ic_launcher);
        holder.tempMaxTextView.setText(weatherForecast.getTempMax() + "°");
        holder.tempMinTextView.setText(weatherForecast.getTempMin()+"°");
    }

    @Override
    public int getItemCount() {
        return this.weatherForecasts == null ? 0 : this.weatherForecasts.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.week_text_view)
        TextView weekTextView;
        @BindView(R.id.weather_icon_image_view)
        ImageView weatherIconImageView;
        @BindView(R.id.temp_max_text_view)
        TextView tempMaxTextView;
        @BindView(R.id.temp_min_text_view)
        TextView tempMinTextView;

        public ViewHolder(View itemView, ForecastAdapter adapter) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(view -> adapter.onItemHolderClick(ForecastAdapter.ViewHolder.this));
        }
    }
 }
