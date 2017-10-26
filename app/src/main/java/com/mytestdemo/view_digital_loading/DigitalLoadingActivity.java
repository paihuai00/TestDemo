package com.mytestdemo.view_digital_loading;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.Button;

import com.mytestdemo.BaseActivity;
import com.mytestdemo.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by cuishuxiang on 2017/10/26.
 */

public class DigitalLoadingActivity extends BaseActivity {
    @BindView(R.id.digital_view)
    DigitalLoadingView digitalView;
    @BindView(R.id.start_btn)
    Button startBtn;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_digital_view);
        ButterKnife.bind(this);

    }

    @OnClick(R.id.start_btn)
    public void onViewClicked() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 101; i++) {
                    try {
                        digitalView.setProgressNum(i);
                        Thread.sleep(50);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();

    }
}
