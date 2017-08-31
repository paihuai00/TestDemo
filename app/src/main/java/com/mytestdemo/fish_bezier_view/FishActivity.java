package com.mytestdemo.fish_bezier_view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.mytestdemo.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by cuishuxiang on 2017/8/13.
 */

public class FishActivity extends AppCompatActivity {

    @BindView(R.id.fish_img)
    FishDrawableView fishImg;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fish_view);
        ButterKnife.bind(this);


    }

}
