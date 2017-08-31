package com.mytestdemo.my_bitmap;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.ImageView;

import com.mytestdemo.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by cuishuxiang on 2017/7/4.
 * <p>
 * bitmap  图片压缩 (二次采样)
 * <p>
 * http://www.jianshu.com/p/04d18e83eb87
 */

public class BitmapActivity extends AppCompatActivity {
    @BindView(R.id.bitmap_btn)
    Button bitmapBtn;
    @BindView(R.id.bitmap_img)
    ImageView bitmapImg;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bitmap);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.bitmap_btn)
    public void onClick() {
        bitmapImg.setImageDrawable(getDrawable(R.drawable.big_img));

    }

    /**
     * 目前市场上的主流手机分辨率一般最低是720-1280了所以就按照此分辨率进行压缩
     *
     * @param filePath
     * @return
     */

    //图片压缩，(二次采样)
    public Bitmap getBitmap(String filePath) {
        //第一次采样
        BitmapFactory.Options options = new BitmapFactory.Options();
        //该属性设置为true只会加载图片的边框进来，并不会加载图片具体的像素点,也就是说不会把图片加载到内存中
        options.inJustDecodeBounds = true;
        //第一次加载图片，这时只会加载图片的边框进来，并不会加载图片中的像素点
        BitmapFactory.decodeFile(filePath, options);
        //获得原图的宽和高
        int outWidth = options.outWidth;
        int outHeight = options.outHeight;

        //原始图片的宽度与720的比值，然后向上取整这里为8
        int wRatio = (int) Math.ceil(options.outWidth / (float) 720);
        //原始图片的高度与1280的比值，然后向上取整这里为3
        int hRatio = (int) Math.ceil(options.outHeight / (float) 1280); //获取采样率

        if (wRatio > 1 && hRatio > 1) {
            if (wRatio > hRatio) {
                options.inSampleSize = wRatio;
            } else {
                options.inSampleSize = hRatio;
            }

        }


        //至此，第一次采样已经结束，我们已经成功的计算出了sampleSize的大小
        /********************************************************************************************/
        //二次采样开始
        //二次采样时我需要将图片加载出来显示，不能只加载图片的框架，因此inJustDecodeBounds属性要设置为false
        options.inJustDecodeBounds = false;
        //设置缩放比例
        options.inPreferredConfig = Bitmap.Config.ARGB_8888;
        //加载图片并返回
        return BitmapFactory.decodeFile(filePath, options);
    }
}