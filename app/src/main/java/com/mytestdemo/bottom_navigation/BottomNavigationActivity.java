package com.mytestdemo.bottom_navigation;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.mytestdemo.BaseActivity;
import com.mytestdemo.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by cuishuxiang on 2017/10/9.
 * <p>
 * https://github.com/Ashok-Varma/BottomNavigation
 * <p>
 * 底部导航栏
 */

public class BottomNavigationActivity extends BaseActivity {
    private static final String TAG = "BottomNavigationActivit";
    @BindView(R.id.bottom_navigation_bar)
    BottomNavigationBar bottomNavigationBar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bottom_navigation);
        ButterKnife.bind(this);

        bottomNavigationBar
                .addItem(new BottomNavigationItem(R.drawable.icon_login_logo, "Home")
                        .setInActiveColor("#676bff"))
                .addItem(new BottomNavigationItem(R.drawable.icon_login_logo, "Books")
                        .setInactiveIcon(getDrawable(R.drawable.icon_mine_service))
                        .setActiveColor("#FFFF00")
                        .setInActiveColor("#488495"))
                .addItem(new BottomNavigationItem(R.drawable.icon_login_logo, "Music")
                        .setActiveColor("#FFFF00")
                        .setInActiveColor("#4bc465"))
                .addItem(new BottomNavigationItem(R.drawable.icon_login_logo, "Movies & TV")
                        .setActiveColor("#FFFF00")
                        .setInActiveColor("#AAaAb0"))
                .addItem(new BottomNavigationItem(R.drawable.icon_login_logo, "Games")
                        .setActiveColor("#FFFF00")
                        .setInActiveColor("#000000"))
                .initialise();

        bottomNavigationBar
                .setActiveColor(R.color.line_color)
                .setInActiveColor("#FDB934")
                .setBarBackgroundColor("#FDB934");

        bottomNavigationBar
                .setMode(BottomNavigationBar.MODE_FIXED);

        bottomNavigationBar.setTabSelectedListener(new BottomNavigationBar.OnTabSelectedListener(){
            @Override
            public void onTabSelected(int position) {
                Log.d(TAG, "onTabSelected: "+position);
            }

            @Override
            public void onTabUnselected(int position) {
                Log.d(TAG, "onTabUnselected: " + position);
            }
            @Override
            public void onTabReselected(int position) {
                Log.d(TAG, "onTabReselected: " + position);

            }
        });
    }
}
