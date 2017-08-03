package com.example.zhouyiran.mysample.view.widget;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;

/**
 * Created by zhouyiran on 2017/7/20.
 */

public class CannotScrollLinearLayoutManager extends LinearLayoutManager {

    public CannotScrollLinearLayoutManager(Context context) {
        super(context);
    }

    @Override
    public boolean canScrollVertically() {
        return false;
    }
}
