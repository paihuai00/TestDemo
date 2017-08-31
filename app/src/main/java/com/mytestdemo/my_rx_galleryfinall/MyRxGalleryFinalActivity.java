package com.mytestdemo.my_rx_galleryfinall;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.mytestdemo.R;

import cn.finalteam.rxgalleryfinal.RxGalleryFinalApi;
import cn.finalteam.rxgalleryfinal.rxbus.RxBusResultSubscriber;
import cn.finalteam.rxgalleryfinal.utils.Logger;
import cn.finalteam.rxgalleryfinal.utils.MediaScanner;

/**
 * Created by cuishuxiang on 2017/4/18.
 */

public class MyRxGalleryFinalActivity extends AppCompatActivity {
    private Button rx_btn;
    private ImageView choose_img;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rx_galleryfinal);

        choose_img = (ImageView) findViewById(R.id.choose_img);
        rx_btn = (Button) findViewById(R.id.rx_btn);
        rx_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                RxGalleryFinal.with(getBaseContext())
//                        .image()
////                        .radio()//视频
//                        .crop()//裁剪
//                        .imageLoader(ImageLoaderType.GLIDE)
//                        .subscribe(new RxBusResultSubscriber<ImageRadioResultEvent>() {
//                            @Override
//                            protected void onEvent(ImageRadioResultEvent imageRadioResultEvent) throws Exception {
//                                imageRadioResultEvent.getResult();
//                                imageRadioResultEvent.getResult();
//                                imageRadioResultEvent.getResult().getOriginalPath();
//
//                                Glide.with(getBaseContext())
//                                        .load(imageRadioResultEvent.getResult().getOriginalPath())
//                                        .into(choose_img);
//                            }
//                        }).openGallery();

//                RxGalleryFinal.with(getBaseContext())
//                        .image()
//                        .crop()//裁剪
//                        .imageLoader(ImageLoaderType.GLIDE)
//                        .subscribe(new RxBusResultSubscriber<ImageMultipleResultEvent>() {
//                            @Override
//                            protected void onEvent(ImageMultipleResultEvent imageMultipleResultEvent) throws Exception {
//                                Glide.with(getBaseContext())
//                                        .load(imageMultipleResultEvent.getResult().get(0).getOriginalPath())
//                                        .into(choose_img);
//                            }
//                        }).openGallery();

                //3.打开单选图片
                RxGalleryFinalApi.openRadioSelectImage(MyRxGalleryFinalActivity.this, new RxBusResultSubscriber() {
                    @Override
                    protected void onEvent(Object o) throws Exception {
                        o.toString();
                    }
                });


//                RxGalleryFinalApi.getInstance(MyRxGalleryFinalActivity.this)
//                        .setImageRadioResultEvent(new RxBusResultSubscriber<ImageRadioResultEvent>() {
//                    @Override
//                    protected void onEvent(ImageRadioResultEvent imageRadioResultEvent) throws Exception {
//                        Glide.with(getBaseContext())
//                                .load(imageRadioResultEvent.getResult().getOriginalPath())
//                                .into(choose_img);
//                    }
//                }).open();

            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        //选择调用：裁剪图片的回调
        RxGalleryFinalApi.cropActivityForResult(this, new MediaScanner.ScanCallback() {
            @Override
            public void onScanCompleted(String[] images) {
                Logger.i(String.format("裁剪图片成功,图片裁剪后存储路径:%s", images[0]));
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RxGalleryFinalApi.TAKE_IMAGE_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            RxGalleryFinalApi.openZKCameraForResult(MyRxGalleryFinalActivity.this, new MediaScanner.ScanCallback() {
                @Override
                public void onScanCompleted(String[] strings) {
                    Logger.i(String.format("拍照成功,图片存储路径:%s", strings[0]));
//                    if (mFlagOpenCrop) {
//                        Logger.d("演示拍照后进行图片裁剪，根据实际开发需求可去掉上面的判断");
//                        RxGalleryFinalApi.cropScannerForResult(MyRxGalleryFinalActivity.this, RxGalleryFinalApi.getModelPath(), strings[0]);//调用裁剪.RxGalleryFinalApi.getModelPath()为默认的输出路径
//                    }
                }
            });
        }
    }
}
