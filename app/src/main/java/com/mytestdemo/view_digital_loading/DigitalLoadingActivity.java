package com.mytestdemo.view_digital_loading;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;

import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.mytestdemo.BaseActivity;

import com.mytestdemo.R;
import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import me.jessyan.progressmanager.ProgressListener;
import me.jessyan.progressmanager.ProgressManager;
import me.jessyan.progressmanager.body.ProgressInfo;

/**
 * Created by cuishuxiang on 2017/10/26.
 */

public class DigitalLoadingActivity extends BaseActivity {
    private static final String TAG = "DigitalLoadingActivity";
    @BindView(R.id.digital_view)
    DigitalLoadingView digitalView;
    @BindView(R.id.show_img)
    ImageView showImg;
    @BindView(R.id.start_btn)
    Button startBtn;
    @BindView(R.id.glide_loadView)
    GlideImgLoadingView glideLoadView;

    private String img = "http://img0.imgtn.bdimg.com/it/u=1677352010,3327455678&fm=11&gp=0.jpg";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_digital_view);
        ButterKnife.bind(this);

        initListener();
        //初始化
        Logger.addLogAdapter(new AndroidLogAdapter());

        Logger.d("DigitalLoadingActivityw1");
        //修改打印的TAG
        Logger.t(TAG).d("两个函数");

        //此处打印，类似C语音的%d  %s
        Logger.d("你好，我是%s，今年%d ", "csx", 20);
    }

    @OnClick(R.id.start_btn)
    public void onViewClicked() {
        glideLoadView.loadImg(img);

//        GlideApp.with(this)
//                .load(img)
//                .centerCrop()
//                .placeholder(R.color.colorPrimary)
//                .diskCacheStrategy(DiskCacheStrategy.NONE)
//                .into(showImg);

//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                for (int i = 0; i < 101; i++) {
//                    try {
//                        digitalView.setProgressNum(i);
//                        Thread.sleep(50);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                }
//            }
//        }).start();

    }

    private void initListener() {
        ProgressManager.getInstance().addResponseListener(img, new ProgressListener() {
            @Override
            public void onProgress(ProgressInfo progressInfo) {
                int percent = progressInfo.getPercent();
                Log.d(TAG, "onProgress: " + percent);

                if (percent == 100) {
                    Log.d(TAG, "onProgress:加载完成 " + percent);

                }
            }

            @Override
            public void onError(long id, Exception e) {
                Log.d(TAG, "Glide --> error: " + e);

            }
        });
    }
}
