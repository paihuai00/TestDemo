package com.mytestdemo.load_state;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;

import com.kingja.loadsir.callback.Callback;
import com.kingja.loadsir.core.LoadService;
import com.kingja.loadsir.core.LoadSir;
import com.mytestdemo.BaseActivity;
import com.mytestdemo.R;
import com.mytestdemo.load_state.callback.EmptyCallback;
import com.mytestdemo.load_state.callback.ErrorCallback;

/**
 * Created by cuishuxiang on 2017/9/27.
 * <p>
 * error/empty/success 状态
 * <p>
 * http://www.jianshu.com/p/2d3537101281
 */

public class LoadStateActivity extends BaseActivity {
    private static final String TAG = "LoadStateActivity";
    LoadService loadService;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loadstate);

        loadService = LoadSir.getDefault().register(this, new Callback.OnReloadListener() {
            @Override
            public void onReload(View v) {
                Log.d(TAG, "onReload: ");
                loadService.showCallback(EmptyCallback.class);
            }
        });

        loadService.showCallback(ErrorCallback.class);

//        loadService.showSuccess();
    }
}
