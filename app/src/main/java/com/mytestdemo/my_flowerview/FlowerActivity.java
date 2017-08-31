package com.mytestdemo.my_flowerview;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

import com.mytestdemo.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by cuishuxiang on 2017/8/8.
 * <p>
 * 花束点赞效果 (贝塞尔曲线)
 */

public class FlowerActivity extends AppCompatActivity {
    @BindView(R.id.show_img_btn)
    Button showImgBtn;
    @BindView(R.id.like_layout)
    FlowerBezierViewGroup likeLayout;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flowerview);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.show_img_btn)
    public void onViewClicked() {
        likeLayout.addImageView();
    }
}
