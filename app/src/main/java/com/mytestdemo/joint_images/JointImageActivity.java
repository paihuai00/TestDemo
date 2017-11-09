package com.mytestdemo.joint_images;

import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.widget.Button;

import com.mytestdemo.BaseActivity;
import com.mytestdemo.MainActivity;
import com.mytestdemo.R;
import com.zhihu.matisse.Matisse;
import com.zhihu.matisse.MimeType;
import com.zhihu.matisse.engine.impl.GlideEngine;
import com.zhihu.matisse.filter.Filter;
import com.zhihu.matisse.internal.entity.CaptureStrategy;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author cuishuxiang
 * @date 2017/11/6.
 *
 *  参考：http://www.cnblogs.com/tony-yang-flutter/p/3559715.html
 */

public class JointImageActivity extends BaseActivity {

    @BindView(R.id.joint_view)
    JointImagesView jointView;
    @BindView(R.id.joint_btn)
    Button jointBtn;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_joint_images);
        ButterKnife.bind(this);
        /**
         * 这里使用BitmapFactory.decodeStream(InputStream is);
         * 方法加载图片可以有效的防止
         * 当内存过大时出现内存溢出的情况
         */


//        Matisse.from(this)
//                .choose(MimeType.ofAll(), false)//选择mime类型,
//                .countable(true)
//                .capture(true)
//                .captureStrategy(
//                        new CaptureStrategy(true, "com.mytestdemo.fileprovider"))
//                .maxSelectable(5)//最多选择5张图片
//                .theme(R.style.Matisse_Zhihu)
//                .thumbnailScale(1f)//缩略图比例
//                .imageEngine(new GlideEngine())//图片加载引擎
//                .forResult(1);//作为标记的 请求码

        Matisse.from(JointImageActivity.this)
                .choose(MimeType.ofAll())
                .countable(true)
                .maxSelectable(9)

                .gridExpectedSize(35)
                .restrictOrientation(ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED)
                .thumbnailScale(0.85f)
                .imageEngine(new GlideEngine())
                .forResult(2);
    }


    /**
     * 封装一个方法
     * 将drawable下的image，转换成Bitmap ，再转换成 InputStream
     */
    public InputStream getInputStreamFromDrawanle(int drawableId) {

        Bitmap bitmap = BitmapFactory.decodeResource(this.getResources(), drawableId);

        InputStream is = Bitmap2InputStream(bitmap);

        if (is != null) {
            return is;
        } else {
            return null;
        }
    }

    /**
     * 将Bitmap转换成InputStream
     * @param bm
     * @return
     */
    public InputStream Bitmap2InputStream(Bitmap bm) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bm.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        InputStream is = new ByteArrayInputStream(baos.toByteArray());
        return is;
    }

    @OnClick(R.id.joint_btn)
    public void onViewClicked() {

        /**
         * 这里使用BitmapFactory.decodeStream(InputStream is);
         * 方法加载图片可以有效的防止
         * 当内存过大时出现内存溢出的情况
         */
        Bitmap bit1 = BitmapFactory.decodeStream(getInputStreamFromDrawanle(R.drawable.big_img));
        Bitmap bit2 = BitmapFactory.decodeStream(getInputStreamFromDrawanle(R.drawable.icon_bg_img));

        jointView.setBitmaps(bit1, bit2);

    }
}
