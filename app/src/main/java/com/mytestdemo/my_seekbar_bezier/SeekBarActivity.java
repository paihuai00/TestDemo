package com.mytestdemo.my_seekbar_bezier;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

import com.mytestdemo.R;
import com.mytestdemo.view.QuadView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by cuishuxiang on 2017/7/26.
 */

public class SeekBarActivity extends AppCompatActivity {
    private static final String TAG = "SeekBarActivity";
    @BindView(R.id.mSeekBar)
    SeekBar mSeekBar;
    @BindView(R.id.progress_txt)
    TextView progressTxt;
    @BindView(R.id.quad_view)
    QuadBezierView quadView;
    @BindView(R.id.cubic_view)
    CubicBezierView cubicView;
    @BindView(R.id.cubic_btn)
    Button cubicBtn;
    @BindView(R.id.mQuadView)
    MyQuadView mQuadView;
    @BindView(R.id.quad_view2)
    QuadView quadView2;
    private boolean change = true;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seekbar);
        ButterKnife.bind(this);

        initSeekBar();

    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        Log.d(TAG, "dispatchTouchEvent: " + super.dispatchTouchEvent(ev));
        return super.dispatchTouchEvent(ev);
//        return true;
    }

    private void initSeekBar() {
        mSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                Log.d(TAG, "onProgressChanged: " + i + "   " + b);
                progressTxt.setText(i + "");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    @OnClick({R.id.cubic_btn})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.cubic_btn:
                if (change) {
                    cubicView.setMode(1);

                } else {
                    cubicView.setMode(2);
                }
                change = !change;
                break;
        }
    }
}
