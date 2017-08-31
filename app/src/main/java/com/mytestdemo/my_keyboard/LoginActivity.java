package com.mytestdemo.my_keyboard;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.mytestdemo.R;
import com.zhy.autolayout.AutoRelativeLayout;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by cuishuxiang on 2017/4/28.
 */

public class LoginActivity extends AppCompatActivity implements View.OnLayoutChangeListener {

    private static final String TAG = "LoginActivity";

    ImageView loginLogoImg;
    EditText userEdit;
    EditText pwdEdit;
    Button loginBtn;
    AutoRelativeLayout loginLayout;
    @BindView(R.id.second_layout)
    AutoRelativeLayout secondLayout;
//    AutoFrameLayout rootLayout;

    private int keyHeight = 0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_layout);
        ButterKnife.bind(this);
        getSupportActionBar().hide();

        loginLogoImg = (ImageView) findViewById(R.id.login_logo_img);
        loginBtn = (Button) findViewById(R.id.login_btn);
        loginLayout = (AutoRelativeLayout) findViewById(R.id.login_layout);
//        rootLayout = (AutoFrameLayout) findViewById(R.id.root_layout);
        initView();
    }

    private void initView() {
        keyHeight = getScreenHeight() / 4;
        Log.d(TAG, "initView: " + keyHeight);
//        rootLayout.addOnLayoutChangeListener(this);
        loginLayout.addOnLayoutChangeListener(this);


        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loginLogoImg.setVisibility(View.GONE);
            }
        });
    }

    //处理键盘弹出
    @Override
    public void onLayoutChange(View v, int left, int top, int right, int bottom, int oldLeft, int oldTop, int oldRight, int oldBottom) {
        //old是改变前的左上右下坐标点值，没有old的是改变后的左上右下坐标点值
        Log.d(TAG, "onLayoutChange: top          " + top);
        Log.d(TAG, "onLayoutChange: bottom       " + bottom);
        Log.d(TAG, "onLayoutChange: old top:     " + oldTop);
        Log.d(TAG, "onLayoutChange: old bottom:  " + oldBottom);
        Log.d(TAG, "onLayoutChange: oldBottom - bottom     " + (oldBottom - bottom));
        //现在认为只要控件将Activity向上推的高度超过了1/3屏幕高，就认为软键盘弹起
        if (oldBottom != 0 && bottom != 0 && (oldBottom - bottom > keyHeight)) {
            Log.d(TAG + "----->TAG", "" + (oldBottom - bottom));
            loginLogoImg.setVisibility(View.GONE);
            secondLayout.setGravity(Gravity.CENTER);

        } else if (oldBottom != 0 && bottom != 0 && (bottom - oldBottom > keyHeight)) {
            Log.d(TAG + "----->TAG2", "" + (bottom - oldBottom));

            loginLogoImg.setVisibility(View.VISIBLE);
            secondLayout.setGravity(Gravity.NO_GRAVITY);
        }

    }

    /**
     * 获取屏幕的高度（单位：px）
     *
     * @return 屏幕高px
     */
    public int getScreenHeight() {
        WindowManager windowManager = (WindowManager) getApplication().getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics dm = new DisplayMetrics();// 创建了一张白纸
        windowManager.getDefaultDisplay().getMetrics(dm);// 给白纸设置宽高
        return dm.heightPixels;
    }
}
