package com.mytestdemo.progress_manager;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.mytestdemo.BaseActivity;
import com.mytestdemo.R;

import javax.microedition.khronos.opengles.GL;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import me.jessyan.progressmanager.ProgressListener;
import me.jessyan.progressmanager.ProgressManager;
import me.jessyan.progressmanager.body.ProgressInfo;

/**
 * Created by cuishuxiang on 2017/10/26.
 * <p>
 * https://github.com/JessYanCoding/ProgressManager
 */

public class ProgressManagerActivity extends BaseActivity {
    private static final String TAG = "ProgressManagerActivity";
    @BindView(R.id.progress_img)
    ImageView progressImg;
    @BindView(R.id.start_btn)
    Button startBtn;

    public String mImageUrl = new String("https://raw.githubusercontent.com/JessYanCoding/MVPArmsTemplate/master/art/step.png");

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_progress_manager);
        ButterKnife.bind(this);

        initListener();
    }

    private void initListener() {
        //Glide 加载监听
        ProgressManager.getInstance().addResponseListener(mImageUrl, getGlideListener());
    }

    @OnClick(R.id.start_btn)
    public void onViewClicked() {

        Glide.with(this)
                .load(mImageUrl)
                .into(progressImg);
    }


    @NonNull
    private ProgressListener getGlideListener() {
        return new ProgressListener() {
            @Override
            public void onProgress(ProgressInfo progressInfo) {
                int progress = progressInfo.getPercent();
                Log.d(TAG, "--Glide-- " + progress + " %  " + progressInfo.getSpeed() + " byte/s  " + progressInfo.toString());
                if (progressInfo.isFinish()) {
                    //说明已经加载完成
                    Log.d(TAG, "--Glide-- finish");
                }
            }

            @Override
            public void onError(long id, Exception e) {
                Log.d(TAG, "onError: " + id);
            }
        };
    }
}
