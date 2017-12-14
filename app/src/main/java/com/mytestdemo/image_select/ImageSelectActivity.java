package com.mytestdemo.image_select;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

import com.mytestdemo.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author cuishuxiang
 * @date 2017/12/7.
 * 自定义 图片选择器
 */

public class ImageSelectActivity extends AppCompatActivity {
    @BindView(R.id.select_btn)
    Button selectBtn;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_imageselect);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.select_btn)
    public void onViewClicked() {

    }
}
