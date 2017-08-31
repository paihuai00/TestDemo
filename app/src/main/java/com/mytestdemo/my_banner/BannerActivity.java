package com.mytestdemo.my_banner;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

import com.mytestdemo.R;
import com.zhouwei.mzbanner.MZBannerView;
import com.zhouwei.mzbanner.holder.MZHolderCreator;
import com.zhouwei.mzbanner.holder.MZViewHolder;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by cuishuxiang on 2017/6/16.
 * <p>
 * https://github.com/pinguo-zhouwei/MZBannerView
 */

public class BannerActivity extends AppCompatActivity {

    @BindView(R.id.banner_view)
    MZBannerView bannerView;


    List<Integer> list = new ArrayList<>();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_banner);
        ButterKnife.bind(this);

        list.add(R.drawable.pic1);
        list.add(R.drawable.pic2);
        list.add(R.drawable.pic3);
        list.add(R.drawable.pic4);

        bannerView.setPages(list, new MZHolderCreator() {
            @Override
            public MZViewHolder createViewHolder() {
                return new BannerViewHolder();
            }

        });

        bannerView.start();
    }

    public static class BannerViewHolder implements MZViewHolder<Integer> {
        private ImageView mImageView;
        @Override
        public View createView(Context context) {
            // 返回页面布局
            View view = LayoutInflater.from(context).inflate(R.layout.banner_item,null);
            mImageView = (ImageView) view.findViewById(R.id.banner_image);
            return view;
        }

        @Override
        public void onBind(Context context, int position, Integer data) {
            // 数据绑定
            mImageView.setImageResource(data);
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        bannerView.pause();//暂停轮播
    }

    @Override
    public void onResume() {
        super.onResume();
        bannerView.start();//开始轮播
    }

}
