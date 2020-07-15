package com.example.day9_lianxice;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.day9_lianxice.adapter.MainAdapter;
import com.example.day9_lianxice.fragment.CangFragment;
import com.example.day9_lianxice.fragment.ShouFragment;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Toolbar mToolbar;
    private ViewPager mViewPager;
    private TabLayout mTablayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mViewPager = (ViewPager) findViewById(R.id.ViewPager);
        mTablayout = (TabLayout) findViewById(R.id.tablayout);
        mToolbar.setTitle("首页");
        setSupportActionBar(mToolbar);
        TextView childAt = (TextView) mToolbar.getChildAt(0);
        childAt.getLayoutParams().width= LinearLayout.LayoutParams.MATCH_PARENT;
        childAt.setGravity(Gravity.CENTER);


        List<Fragment> list=new ArrayList<>();
        list.add(new ShouFragment());
        list.add(new CangFragment());
        MainAdapter mainAdapter = new MainAdapter(getSupportFragmentManager(), list);
        mViewPager.setAdapter(mainAdapter);
        mTablayout.setupWithViewPager(mViewPager);
        mTablayout.getTabAt(0).setText("首页");
        mTablayout.getTabAt(1).setText("收藏");
    }
}
