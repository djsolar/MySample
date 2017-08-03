package com.example.zhouyiran.mysample.view.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.library.adapter.BaseRecyclerViewAdapter;
import com.example.zhouyiran.mysample.R;
import com.example.zhouyiran.mysample.view.entity.WeatherDetail;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by zhouyiran on 2017/7/20.
 */

public class DetailAdapter extends BaseRecyclerViewAdapter<DetailAdapter.ViewHolder> {

    private List<WeatherDetail> detailList;

    public DetailAdapter(List<WeatherDetail> detailList) {
        this.detailList = detailList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_detail, parent, false);
        return new ViewHolder(itemView, this);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        WeatherDetail weatherDetail = detailList.get(position);
        holder.detailIconImageView.setImageResource(weatherDetail.getIconResourceId());
        holder.detailKeyTextView.setText(weatherDetail.getKey());
        holder.detailValueTextView.setText(weatherDetail.getValue());
    }

    @Override
    public int getItemCount() {
        return detailList == null ? 0 : detailList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.detail_icon_image_view)
        ImageView detailIconImageView;

        @BindView(R.id.detail_key_text_view)
        TextView detailKeyTextView;

        @BindView(R.id.detail_value_text_view)
        TextView detailValueTextView;

        public ViewHolder(View itemView, DetailAdapter detailAdapter) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(view -> detailAdapter.onItemHolderClick(DetailAdapter.ViewHolder.this));
        }
    }
}
