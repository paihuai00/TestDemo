package com.mytestdemo.my_gridview;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.mytestdemo.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/4/7.
 */

public class MyGridViewActivity extends AppCompatActivity {
    ViewPager grid_viewpager;
    private MyGridViewPagerAdapter myGridViewPagerAdapter;

    private List<String> totalList;

//    FragmentGrid1 fragmentGrid1;
    FragmentGrid2 fragmentGrid2;
    FragmentGrid3 fragmentGrid3;

    List<Fragment> fragmentList;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gridview_layout);

        totalList = new ArrayList<>();
        for (int i = 0; i < 9; i++) {
            totalList.add("Fragment:" + i);
        }
        totalList.add("Plus");//最后一个元素

        grid_viewpager = (ViewPager) findViewById(R.id.grid_viewpager);
        myGridViewPagerAdapter = new MyGridViewPagerAdapter(getSupportFragmentManager());

//        fragmentGrid1 = new FragmentGrid1();
        fragmentGrid2 = new FragmentGrid2();
        fragmentGrid3 = new FragmentGrid3();

        fragmentList = new ArrayList<>();
//        fragmentList.add(fragmentGrid1);
        fragmentList.add(fragmentGrid2);
        fragmentList.add(fragmentGrid3);

        grid_viewpager.setAdapter(myGridViewPagerAdapter);

    }

    class MyGridViewPagerAdapter extends FragmentPagerAdapter {
        public MyGridViewPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return fragmentList.get(position);
        }

        @Override
        public int getCount() {
            return 3;
        }
    }
}
