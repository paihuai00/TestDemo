package com.mytestdemo.my_state_bar;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

import com.mytestdemo.R;

/**
 * Created by cuishuxiang on 2017/4/11.
 */

public class MyStateActivity extends AppCompatActivity {

    protected int statusBarHeight;//顶部状态栏的高度
    private View statusBar;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_state_layout);

        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            // 透明状态栏
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            statusBarHeight = getStatusBarHeight(this);
            statusBar=findViewById(R.id.status_bar_top);
            ViewGroup.LayoutParams params = statusBar.getLayoutParams();
            params.height = statusBarHeight;
            statusBar.setLayoutParams(params);
            statusBar.setVisibility(View.VISIBLE);//设置View显示
        }
    }
    //获取app顶部状态栏的高度
    public int getStatusBarHeight(Context context) {
        if (statusBarHeight != 0)
            return statusBarHeight;
        //getIdentifier(资源的名称,资源的类型(drawable,string等),包名) 注：这里的status_bar_height是dimen里面的名字
        int resourceId = context.getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            statusBarHeight = context.getResources().getDimensionPixelSize(resourceId);
        }
        return statusBarHeight;
    }

}
