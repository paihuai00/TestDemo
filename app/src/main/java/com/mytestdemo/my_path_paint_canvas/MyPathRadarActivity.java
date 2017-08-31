package com.mytestdemo.my_path_paint_canvas;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.mytestdemo.R;

/**
 * Created by cuishuxiang on 2017/4/19.
 * 用path 画出雷达图。参考：http://blog.csdn.net/crazy__chen/article/details/50163693
 */

public class MyPathRadarActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_path_radar);
    }
}
