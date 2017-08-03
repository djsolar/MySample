package com.example.zhouyiran.mysample.view.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.library.adapter.BaseRecyclerViewAdapter;
import com.example.zhouyiran.mysample.R;
import com.example.zhouyiran.mysample.model.db.entities.minimalist.LifeIndex;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.example.zhouyiran.mysample.R.drawable.ic_index_sunscreen;

/**
 * Created by zhouyiran on 2017/7/20.
 */

public class LifeIndexAdapter extends BaseRecyclerViewAdapter<LifeIndexAdapter.ViewHolder> {

    private List<LifeIndex> lifeIndexs;

    private Context context;

    public LifeIndexAdapter(Context context, List<LifeIndex> lifeIndexs) {
        this.context = context;
        this.lifeIndexs = lifeIndexs;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View rootView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_life_index, parent, false);
        return new ViewHolder(rootView, this);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        LifeIndex lifeIndex = lifeIndexs.get(position);
        holder.indexIconImageView.setImageDrawable(getIndexDrawable(context, lifeIndex.getName()));
        holder.indexLevelTextView.setText(lifeIndex.getIndex());
        holder.indexNameTextView.setText(lifeIndex.getName());
    }

    @Override
    public int getItemCount() {
        return this.lifeIndexs == null ? 0 : this.lifeIndexs.size();
    }

    private Drawable getIndexDrawable(Context context, String indexName) {


        int colorResourceId = ic_index_sunscreen;
        if (indexName.contains("防晒")) {
            colorResourceId = ic_index_sunscreen;
        } else if (indexName.contains("穿衣")) {
            colorResourceId = R.drawable.ic_index_dress;
        } else if (indexName.contains("运动")) {
            colorResourceId = R.drawable.ic_index_sport;
        } else if (indexName.contains("逛街")) {
            colorResourceId = R.drawable.ic_index_shopping;
        } else if (indexName.contains("晾晒")) {
            colorResourceId = R.drawable.ic_index_sun_cure;
        } else if (indexName.contains("洗车")) {
            colorResourceId = R.drawable.ic_index_car_wash;
        } else if (indexName.contains("感冒")) {
            colorResourceId = R.drawable.ic_index_clod;
        } else if (indexName.contains("广场舞")) {
            colorResourceId = R.drawable.ic_index_dance;
        }
        return context.getResources().getDrawable(colorResourceId);
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.index_icon_image_view)
        ImageView indexIconImageView;
        @BindView(R.id.index_level_text_view)
        TextView indexLevelTextView;
        @BindView(R.id.index_name_text_view)
        TextView indexNameTextView;

        public ViewHolder(View itemView, LifeIndexAdapter adapter) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(view -> {
                adapter.onItemHolderClick(LifeIndexAdapter.ViewHolder.this);
            });
        }
    }
}
