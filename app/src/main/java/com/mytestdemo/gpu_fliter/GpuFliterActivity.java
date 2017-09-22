package com.mytestdemo.gpu_fliter;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.mytestdemo.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by cuishuxiang on 2017/9/18.
 */

public class GpuFliterActivity extends AppCompatActivity {

    @BindView(R.id.show_img)
    ImageView showImg;

    private String img_url = "http://pic36.nipic.com/20131225/15361977_174053547194_2.jpg";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gpu_fliter);
        ButterKnife.bind(this);

        showImg.setImageBitmap(
                GPUImageUtil.getGPUImageFromURL(img_url));

//        GPUImage gpuImage=
//        GPUImageUtil.getFilter()
    }
}
