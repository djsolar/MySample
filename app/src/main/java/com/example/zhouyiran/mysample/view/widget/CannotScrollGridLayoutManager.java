package com.example.zhouyiran.mysample.view.widget;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;

/**
 * Created by zhouyiran on 2017/7/20.
 */

public class CannotScrollGridLayoutManager extends GridLayoutManager {

    public CannotScrollGridLayoutManager(Context context, int spanCount) {
        super(context, spanCount);
    }

    @Override
    public boolean canScrollVertically() {
        return false;
    }
}
