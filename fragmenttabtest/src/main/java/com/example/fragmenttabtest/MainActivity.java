package com.example.fragmenttabtest;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTabHost;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TabWidget;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener, TabHost.OnTabChangeListener{

    private ViewPager viewPager;

    private Class fragmnets[] = {FragmentOne.class, FragmentTwo.class};

    private FragmentTabHost fragmentTabHost;

    private int imageViewArray[] = { android.R.drawable.bottom_bar, android.R.drawable.btn_default };
    private String textViewArray[] = { "首页", "分类"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        initPage();
    }

    private void initPage() {
        Fragment fragment1 = new FragmentOne();
        Fragment fragment2 = new FragmentTwo();
        List<Fragment> fragments = new ArrayList<>();
        fragments.add(fragment1);
        fragments.add(fragment2);
        viewPager.setAdapter(new MyViewAdapter(getSupportFragmentManager(), fragments));
    }

    private void initView() {
        viewPager = (ViewPager) findViewById(R.id.view_pager);
        viewPager.addOnPageChangeListener(this);
        fragmentTabHost = (FragmentTabHost) findViewById(android.R.id.tabhost);
        fragmentTabHost.setup(this, getSupportFragmentManager(), R.id.view_pager);
        fragmentTabHost.setOnTabChangedListener(this);

        int count = imageViewArray.length;
        for(int i = 0; i < count; i++) {
            TabHost.TabSpec tabSpec = fragmentTabHost.newTabSpec(textViewArray[i]).setIndicator(getTabItemView(i));
            fragmentTabHost.addTab(tabSpec, fragmnets[i], null);
            fragmentTabHost.setTag(i);
            fragmentTabHost.getTabWidget().getChildAt(i).setBackgroundResource(R.drawable.background_select);
        }
        fragmentTabHost.getTabWidget().setDividerDrawable(null);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        TabWidget tabWidget = fragmentTabHost.getTabWidget();
        int oldFocusablity = tabWidget.getDescendantFocusability();
        tabWidget.setDescendantFocusability(TabWidget.FOCUS_BLOCK_DESCENDANTS);
        fragmentTabHost.setCurrentTab(position);
        tabWidget.setDescendantFocusability(oldFocusablity);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public void onTabChanged(String s) {
        int position = fragmentTabHost.getCurrentTab();
        viewPager.setCurrentItem(position);
    }

    private View getTabItemView(int i) {
        //将xml布局转换为view对象
        LayoutInflater layoutInflater = getLayoutInflater();
        View view = layoutInflater.inflate(R.layout.tab_content, null);
        //利用view对象，找到布局中的组件,并设置内容，然后返回视图
        ImageView mImageView = (ImageView) view
                .findViewById(R.id.tab_imageview);
        TextView mTextView = (TextView) view.findViewById(R.id.tab_textview);
        mImageView.setBackgroundResource(imageViewArray[i]);
        mTextView.setText(textViewArray[i]);
        return view;
    }

    private class MyViewAdapter extends FragmentPagerAdapter {

        private List<Fragment> fragments;

        public MyViewAdapter(FragmentManager fm, List<Fragment> fragments) {
            super(fm);
            this.fragments = fragments;
        }

        @Override
        public Fragment getItem(int position) {
            return this.fragments.get(position);
        }

        @Override
        public int getCount() {
            return this.fragments.size();
        }
    }
}
