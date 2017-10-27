package com.mytestdemo.my_galleryfinal;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.mytestdemo.R;
import com.mytestdemo.view_digital_loading.GlideApp;

import java.io.ByteArrayOutputStream;
import java.util.List;

import cn.finalteam.galleryfinal.GalleryFinal;
import cn.finalteam.galleryfinal.model.PhotoInfo;

/**
 * Created by cuishuxiang on 2017/4/13.
 */

public class MyGalleryFinalActivity extends AppCompatActivity {
    private Button choose_img;

    private ImageView my_img;

    //图片选择器
    private final int REQUEST_CODE_CAMERA = 1000;
    private final int REQUEST_CODE_GALLERY = 1001;
    private final int REQUEST_CODE_CROP = 1002;
    private final int REQUEST_CODE_EDIT = 1003;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery_final_layout);

        initView();


    }

    private void initView() {
        my_img = (ImageView) findViewById(R.id.my_img);
        choose_img = (Button) findViewById(R.id.choose_img);
        choose_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                GalleryFinal.openGallerySingle(REQUEST_CODE_GALLERY, new GalleryFinal.OnHanlderResultCallback() {
                    @Override
                    public void onHanlderSuccess(int reqeustCode, List<PhotoInfo> resultList) {

                        GlideApp.with(getBaseContext())
                                .load(resultList.get(0).getPhotoPath())
                                .error(R.mipmap.ic_launcher)
                                .into(my_img);

                        Bitmap bitmap = BitmapFactory.decodeFile(resultList.get(0).getPhotoPath());
                        bitmap.getHeight();
                        bitmap.getWidth();
                        bitmap.getByteCount();//大小

//                        MyBitmapCompress.compressImage(bitmap).getByteCount();
//                        MyBitmapCompress.compressScale(bitmap).getByteCount();
//                        Toast.makeText(getBaseContext(), MyBitmapCompress.compressImage(bitmap).getByteCount() + "", Toast.LENGTH_SHORT).show();


                        Glide.with(getBaseContext())
                                .load(Bitmap2Bytes(MyBitmapCompress.compressScale(bitmap)))
                                .into(my_img);
                    }

                    @Override
                    public void onHanlderFailure(int requestCode, String errorMsg) {

                    }
                });
            }
        });



    }

    private byte[] Bitmap2Bytes(Bitmap bm){

        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        bm.compress(Bitmap.CompressFormat.PNG, 100, baos);

        return baos.toByteArray();

    }
}
