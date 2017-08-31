package com.mytestdemo.qq_step_count_view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.mytestdemo.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by cuishuxiang on 2017/8/17.
 */

public class QQStepCountActivity extends AppCompatActivity {
    @BindView(R.id.start)
    Button start;
    @BindView(R.id.pause)
    Button pause;
    @BindView(R.id.end)
    Button end;
    @BindView(R.id.step_view)
    QQStepCountView stepView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qq_count);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.start, R.id.pause, R.id.end})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.start:
                stepView.startStepCount();
                break;
            case R.id.pause:
                stepView.pauseStepCount();
                break;
            case R.id.end:
                stepView.stopStepCount();
                break;
        }
    }
}
