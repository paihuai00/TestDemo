package com.mytestdemo.table_viewpager;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

import com.mytestdemo.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * TabLayout  +ViewPager
 */

public class MyTabViewPagerActivity extends AppCompatActivity implements View.OnClickListener {
    @BindView(R.id.tablayout)
    TabLayout tablayout;
    @BindView(R.id.viewpager)
    ViewPager viewpager;
    private List<Fragment> fragmentList;

    private TabFragment1 tabFragment1;
    private TabFragment2 tabFragment2;
    private TabFragment3 tabFragment3;


    private MyPagerAdapter adapter;

    private  int[] imgs = new int[]{R.drawable.ic_home_home_normal,
            R.drawable.ic_home_mine_focus,
            R.drawable.ic_home_article_normal};

    private String[] tabs = new String[]{"tab1", "tab2", "tab3"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_tab_view_pager);
        ButterKnife.bind(this);

        tabFragment1 = new TabFragment1();
        tabFragment2 = new TabFragment2();
        tabFragment3 = new TabFragment3();
        fragmentList = new ArrayList<>();
        fragmentList.add(tabFragment1);
        fragmentList.add(tabFragment2);
        fragmentList.add(tabFragment3);

        adapter = new MyPagerAdapter(getSupportFragmentManager());

        viewpager.setAdapter(adapter);

        tablayout.setupWithViewPager(viewpager);

//        tablayout.addTab(tablayout.newTab().setText("tab1"),true);
//        tablayout.addTab(tablayout.newTab().setIcon(imgs[1]).setText("tab2"),false);
//        tablayout.addTab(tablayout.newTab().setIcon(imgs[2]).setText("tab3"),false);

//
        for (int i = 0; i < tablayout.getTabCount(); i++) {
            TabLayout.Tab tab = tablayout.getTabAt(i);

            if (tab != null) {
                tab.setCustomView(adapter.getTabView(i));
            }
        }

//        tablayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
//            @Override
//            public void onTabSelected(TabLayout.Tab tab) {
//
//            }
//
//            @Override
//            public void onTabUnselected(TabLayout.Tab tab) {
//
//            }
//
//            @Override
//            public void onTabReselected(TabLayout.Tab tab) {
//
//            }
//        });


   }

    @Override
    public void onClick(View view) {

    }


    class MyPagerAdapter extends FragmentPagerAdapter {
        //        int[] imgs = new int[]{R.drawable.ic_home_home_normal,
//                R.drawable.ic_home_mine_focus,
//                R.drawable.ic_home_article_normal};
        int[] imgs = new int[]{R.drawable.ic_home_article_normal,
                R.drawable.select_tab_img,
                R.drawable.select_tab_img};

        public MyPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return fragmentList.get(position);
        }

        @Override
        public int getCount() {
            return fragmentList.size();
        }


        public View getTabView(int positon) {
            View view = LayoutInflater.from(getBaseContext()).inflate(R.layout.item_tab_view, null);

            ImageView imageView = (ImageView) view.findViewById(R.id.tab_img);

            imageView.setImageResource(imgs[positon]);

            return view;
        }


    }


}
