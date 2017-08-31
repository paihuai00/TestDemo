package com.mytestdemo.my_navigation;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v7.app.AppCompatActivity;

import com.mytestdemo.R;

/**
 * Created by cuishuxiang on 2017/4/26.
 */

public class MyNavigationActivity2 extends AppCompatActivity {
    private NavigationView my_navi;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation_layout2);

        my_navi = (NavigationView) findViewById(R.id.my_navi);
        my_navi.setItemIconTintList(null);
    }
}
