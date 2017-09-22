package com.mytestdemo.clip_demo;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.mytestdemo.BaseActivity;
import com.mytestdemo.R;
import com.yalantis.ucrop.UCrop;
import com.yalantis.ucrop.UCropActivity;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.channels.FileChannel;
import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by cuishuxiang on 2017/9/21.
 * <p>
 * https://github.com/Yalantis/uCrop
 */

public class ClipActivity extends BaseActivity {
    private static final String TAG = "ClipActivity";
    @BindView(R.id.clip_btn)
    Button clipBtn;
    @BindView(R.id.show_clip_img)
    ImageView showClipImg;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clip);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.clip_btn)
    public void onViewClicked() {
//        Uri sourceUri = Uri.fromFile(new File("/storage/emulated/0/Pictures/Screenshots/Screenshot_20170920-113055.png"));
//        File outDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
//        if (!outDir.exists()) {
//            outDir.mkdirs();
//        }
//        File outFile = new File(outDir, System.currentTimeMillis() + ".jpg");
//        //裁剪后图片的绝对路径
//        String cameraScalePath = outFile.getAbsolutePath();
//        Uri destinationUri = Uri.fromFile(outFile);
//        UCrop.of(sourceUri, destinationUri)
//                .withAspectRatio(16, 9)
//                .withMaxResultSize(700, 500)
//                .start(this);
//        startUCrop(this, "/storage/emulated/0/Pictures/Screenshots/Screenshot_20170920-113055.png", 10, 1, 1);
        Uri sourceUri = Uri.parse("http://star.xiziwang.net/uploads/allimg/140512/19_140512150412_1.jpg");
        //裁剪后保存到文件中
        Uri destinationUri = Uri.fromFile(new File(getCacheDir(), System.currentTimeMillis() + "SampleCropImage.jpg"));
//        UCrop.of(sourceUri, destinationUri)
//                .withAspectRatio(16, 9)
//                .withMaxResultSize(700, 1900)
//                .start(this);
        UCrop uCrop = UCrop.of(sourceUri, destinationUri);
        uCrop = basisConfig(uCrop);

        uCrop.start(this);
    }

    private UCrop basisConfig(UCrop uCrop) {
        uCrop = uCrop.withAspectRatio(1, 1);

        //高级设置
        UCrop.Options options = new UCrop.Options();
        options.setCompressionFormat(Bitmap.CompressFormat.JPEG);

        options.setCompressionQuality(80);

        options.setHideBottomControls(false);//底部的控制栏
        options.setFreeStyleCropEnabled(true);//自由裁剪

        return uCrop.withOptions(options);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            //裁切成功
            if (requestCode == UCrop.REQUEST_CROP) {
                Uri croppedFileUri = UCrop.getOutput(data);
                Glide.with(ClipActivity.this)
                        .load(croppedFileUri)
                        .into(showClipImg);
                Log.d(TAG, "onActivityResult: " + croppedFileUri.getPath());
                //获取默认的下载目录
                String downloadsDirectoryPath = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).getAbsolutePath();
                String filename = String.format("%d_%s", Calendar.getInstance().getTimeInMillis(), croppedFileUri.getLastPathSegment());
                File saveFile = new File(downloadsDirectoryPath, filename);
                //保存下载的图片
                FileInputStream inStream = null;
                FileOutputStream outStream = null;
                FileChannel inChannel = null;
                FileChannel outChannel = null;
                try {
                    inStream = new FileInputStream(new File(croppedFileUri.getPath()));
                    outStream = new FileOutputStream(saveFile);
                    inChannel = inStream.getChannel();
                    outChannel = outStream.getChannel();
                    inChannel.transferTo(0, inChannel.size(), outChannel);
                    Toast.makeText(this, "裁切后的图片保存在：" + saveFile.getAbsolutePath(), Toast.LENGTH_SHORT).show();
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    try {
                        outChannel.close();
                        outStream.close();
                        inChannel.close();
                        inStream.close();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        //裁切失败
        if (resultCode == UCrop.RESULT_ERROR) {
            Toast.makeText(this, "裁切图片失败", Toast.LENGTH_SHORT).show();
        }
    }


    /**
     * 启动裁剪
     *
     * @param activity       上下文
     * @param sourceFilePath 需要裁剪图片的绝对路径
     * @param requestCode    比如：UCrop.REQUEST_CROP
     * @param aspectRatioX   裁剪图片宽高比
     * @param aspectRatioY   裁剪图片宽高比
     * @return
     */
    public static String startUCrop(Activity activity, String sourceFilePath,
                                    int requestCode, float aspectRatioX, float aspectRatioY) {
        Uri sourceUri = Uri.fromFile(new File(sourceFilePath));
        File outDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
        if (!outDir.exists()) {
            outDir.mkdirs();
        }
        File outFile = new File(outDir, System.currentTimeMillis() + ".jpg");
        //裁剪后图片的绝对路径
        String cameraScalePath = outFile.getAbsolutePath();
        Uri destinationUri = Uri.fromFile(outFile);
        //初始化，第一个参数：需要裁剪的图片；第二个参数：裁剪后图片
        UCrop uCrop = UCrop.of(sourceUri, destinationUri);
        //初始化UCrop配置
        UCrop.Options options = new UCrop.Options();
        //设置裁剪图片可操作的手势
        options.setAllowedGestures(UCropActivity.SCALE, UCropActivity.ROTATE, UCropActivity.ALL);
        //是否隐藏底部容器，默认显示
        options.setHideBottomControls(true);
        //设置toolbar颜色
        options.setToolbarColor(ActivityCompat.getColor(activity, R.color.colorPrimary));
        //设置状态栏颜色
        options.setStatusBarColor(ActivityCompat.getColor(activity, R.color.colorPrimary));
        //是否能调整裁剪框
        options.setFreeStyleCropEnabled(true);
        //UCrop配置
        uCrop.withOptions(options);
        //设置裁剪图片的宽高比，比如16：9
        uCrop.withAspectRatio(aspectRatioX, aspectRatioY);
        //uCrop.useSourceImageAspectRatio();
        //跳转裁剪页面
        uCrop.start(activity, requestCode);
        return cameraScalePath;
    }
}
