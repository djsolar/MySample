package com.example.customviewtest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /*SpringProgressView springProgressView = new SpringProgressView(this);
        springProgressView.setMaxCount(100);
        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, 40);
        params.setMargins(30, 30, 30, 30);
        springProgressView.setLayoutParams(params);
        setContentView(springProgressView);*/
        /*ShrinkTextView tv = new ShrinkTextView(this);
        tv.setText("一杯敬自由，一杯敬死亡");
        tv.setTextSize(30);
        setContentView(tv);*/
        setContentView(R.layout.activity_main);
    }
}
