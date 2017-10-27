package com.mytestdemo.view_digital_loading;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.mytestdemo.R;

import me.jessyan.progressmanager.ProgressListener;
import me.jessyan.progressmanager.ProgressManager;
import me.jessyan.progressmanager.body.ProgressInfo;

/**
 * Created by cuishuxiang on 2017/10/26.
 *
 * 使用Glide 加载图片，并使用自定义的DigitalLoadingView 显示进度
 */

public class GlideImgLoadingView extends RelativeLayout {
    private static final String TAG = GlideImgLoadingView.class.getSimpleName();

    private Context mContext;

    private ImageView img_view;

    private DigitalLoadingView loading_view;

    private String url;

    public GlideImgLoadingView(Context context) {
        this(context, null);
    }

    public GlideImgLoadingView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public GlideImgLoadingView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        mContext = context;

        initView();
    }

    /**
     * 获取布局的view
     */
    private void initView() {
        View view = LayoutInflater.from(mContext).inflate(R.layout.glide_img_loading_layout, this, true);

        img_view = (ImageView) view.findViewById(R.id.img_view);

        loading_view = (DigitalLoadingView) view.findViewById(R.id.loading_view);

    }

    /**
     * 加载图片
     * @param imgUrl
     */
    public void loadImg(String imgUrl) {

        this.url = imgUrl;
        initListener();

        GlideApp.with(mContext)
                .load(imgUrl)
                .centerCrop()
                .placeholder(R.color.colorPrimary)
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .into(img_view);
    }

    /**
     * 初始化监听图片下载进度的监听器
     */
    private void initListener() {
        ProgressManager.getInstance().addResponseListener(url, new ProgressListener() {
            @Override
            public void onProgress(ProgressInfo progressInfo) {
                int percent = progressInfo.getPercent();
                loading_view.setVisibility(VISIBLE);
                loading_view.setProgressNum(percent);
                Log.d(TAG, "onProgress: " + percent);

                if (percent == 100) {
                    Log.d(TAG, "onProgress:加载完成 " + percent);

                    loading_view.setVisibility(INVISIBLE);
                }
            }

            @Override
            public void onError(long id, Exception e) {
                Log.d(TAG, "Glide --> error: " + e);
                loading_view.setVisibility(INVISIBLE);
            }
        });
    }
}
