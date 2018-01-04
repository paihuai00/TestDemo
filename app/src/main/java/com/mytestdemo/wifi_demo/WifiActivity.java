package com.mytestdemo.wifi_demo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.Button;

import com.mytestdemo.BaseActivity;
import com.mytestdemo.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by cuishuxiang
 * date  2018/1/4.
 *
 * wifi 相关
 */

public class WifiActivity extends BaseActivity {
    @BindView(R.id.open_wifi)
    Button openWifi;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wifi);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.open_wifi)
    public void onViewClicked() {
    }
}
