package com.mytestdemo.my_galleryfinal;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.Request;
import com.bumptech.glide.request.target.ImageViewTarget;
import com.mytestdemo.view_digital_loading.GlideApp;

import cn.finalteam.galleryfinal.widget.GFImageView;

/**
 * Created by cuishuxiang on 2017/4/13.
 */

public class GlideImageLoader implements cn.finalteam.galleryfinal.ImageLoader {

    @Override
    public void displayImage(Activity activity, String path, final GFImageView imageView, Drawable defaultDrawable, int width, int height) {
        GlideApp.with(activity)
                .load("file://" + path)
                .placeholder(defaultDrawable)
                .error(defaultDrawable)
                .override(width, height)
                .diskCacheStrategy(DiskCacheStrategy.NONE) //不缓存到SD卡
                .skipMemoryCache(true)
                //.centerCrop()
                .into(new ImageViewTarget<Drawable>(imageView) {
                    @Override
                    protected void setResource(@Nullable Drawable resource) {
                        imageView.setImageDrawable(resource);
                    }

                    @Override
                    public void setRequest(@Nullable Request request) {
                        imageView.setTag(request);
                    }

                    @Nullable
                    @Override
                    public Request getRequest() {
                        return (Request) imageView.getTag();
                    }
                });

    }

    @Override
    public void clearMemoryCache() {
    }
}