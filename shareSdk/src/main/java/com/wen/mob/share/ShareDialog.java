package com.wen.mob.share;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.wen.mob.PlatformType;

import com.wen.mob.R;
import com.wen.mob.utils.QuickBuilder;
import com.wen.mob.utils.QuickDialog;

import java.io.File;
import java.io.FileOutputStream;
import java.util.HashMap;

import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.PlatformActionListener;


/**
 * 分享的一个Dialog
 */

public class ShareDialog implements View.OnClickListener {
    private static final String TAG = "ShareDialog";
    
    private final QuickDialog mShareDialog;
    private String mShareTitle; //指定分享内容标题
    private String mShareText; //指定分享内容文本
    private String mShareImageUrl; //指定分享图片链接
    private String mUrl;// 分享的链接
    private int mShareType = Platform.SHARE_WEBPAGE;// 分享的类型
    private Activity mActivity;
    private final TextView mTvName;
    private String mImagePath;

    public ShareDialog(Activity activity) {
        this.mActivity = activity;
        mShareDialog = QuickBuilder.create(activity)
                .setContentView(R.layout.dialog_share)
                .setContentViewBgRadius(0)
                .fullWidth()
                .fromBottom(true)
                .create();
        mTvName = mShareDialog.getView(R.id.tv_name);
        mShareDialog.setOnClickListener(R.id.tv_wechat, this);
        mShareDialog.setOnClickListener(R.id.tv_wechat_moments, this);
        mShareDialog.setOnClickListener(R.id.tv_qq, this);
        mShareDialog.setOnClickListener(R.id.tv_weibo, this);
        mShareDialog.setOnClickListener(R.id.tv_cancel, this);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.tv_wechat) {
            share(PlatformType.WeChat);
        } else if (id == R.id.tv_wechat_moments) {
            share(PlatformType.WechatMoments);
        } else if (id == R.id.tv_qq) {
            share(PlatformType.QQ);
        } else if (id == R.id.tv_weibo) {
            share(PlatformType.SinaWeibo);
        } else if (id == R.id.tv_cancel) {
            dismiss();
        }
    }

    public void show() {
        //数据埋点：点击展开分享
        mShareDialog.show();
    }

    public void dismiss() {
        mShareDialog.dismiss();
    }

    /**
     * 分享的方法
     *
     * @param platformType
     */
    private void share(PlatformType platformType) {
        ShareData mData = new ShareData();
        Platform.ShareParams params = new Platform.ShareParams();
        params.setShareType(mShareType);
        params.setTitle(mShareTitle);
        params.setText(mShareText);
        params.setUrl(mUrl);
        params.setTitleUrl(mUrl);
        // 本地图片
        if (mImagePath != null) {
            params.setImagePath(mImagePath);
        }
        // 网络图片
        if (!TextUtils.isEmpty(mShareImageUrl)) {
            params.setImageUrl(mShareImageUrl);
        }
        mData.mPlatformType = platformType;
        mData.mShareParams = params;
        ShareManager.getInstance(mActivity).shareData(mData, mListener);
        dismiss();
    }

    public ShareDialog shareTitle(String shareTitle) {
        mShareTitle = shareTitle;
        return this;
    }

    public ShareDialog shareName(String name) {
        if (!TextUtils.isEmpty(name)) {
            mTvName.setText("分享" + name + "到");
        }
        return this;
    }

    public ShareDialog shareText(String shareText) {
        mShareText = shareText;
        return this;
    }

    public ShareDialog shareUrl(String url) {
        mUrl = url;
        return this;
    }

    public ShareDialog shareImageRes(int imageRes) {
        Bitmap bitmap = BitmapFactory.decodeResource(mActivity.getResources(), imageRes);
        // 缓存到本地
        File file = saveImage(bitmap);
        if (file != null) {
            mImagePath = file.getAbsolutePath();
        }
        return this;
    }

    /**
     * 缓存本地图片
     */
    public File saveImage(Bitmap bmp) {
        File appDir = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM), "image");
        if (!appDir.exists()) {
            appDir.mkdir();
        }
        String fileName = "shares.jpg";
        File file = new File(appDir, fileName);
        if (file.exists() && file.isFile()) {
            return file;
        }
        try {
            FileOutputStream fos = new FileOutputStream(file);
            bmp.compress(Bitmap.CompressFormat.JPEG, 100, fos);
            fos.flush();
            fos.close();
            return file;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public ShareDialog shareType(int shareType) {
        mShareType = shareType;
        return this;
    }

    public ShareDialog shareImageUrl(String shareImageUrl) {
        mShareImageUrl = shareImageUrl;
        return this;
    }

    private PlatformActionListener mListener = new PlatformActionListener() {
        @Override
        public void onComplete(Platform platform, int i, HashMap<String, Object> hashMap) {
            //ToastUtils.showLong("分享成功");
            Log.d(TAG, "onComplete: ");
        }

        @Override
        public void onError(Platform platform, int i, Throwable throwable) {
            // ToastUtils.showLong("分享失败");
            Log.d(TAG, "onError: ");
        }

        @Override
        public void onCancel(Platform platform, int i) {
            Log.d(TAG, "onCancel: ");
        }
    };

}
