package com.mytestdemo.view_digital_loading;

import android.content.Context;

import com.bumptech.glide.Glide;
import com.bumptech.glide.GlideBuilder;
import com.bumptech.glide.Registry;
import com.bumptech.glide.annotation.GlideModule;
import com.bumptech.glide.integration.okhttp3.OkHttpUrlLoader;
import com.bumptech.glide.load.model.GlideUrl;
import com.bumptech.glide.module.AppGlideModule;
import com.mytestdemo.MyAppContext;

import java.io.InputStream;

/**
 * Created by cuishuxiang on 2017/10/27.
 */

@GlideModule
public class MyGlideModule extends AppGlideModule {
    private static final String TAG = "GlideConfiguration";

    @Override
    public void applyOptions(Context context, GlideBuilder builder) {

    }

    @Override
    public void registerComponents(Context context, Glide glide, Registry registry) {

        MyAppContext myAppContext = new MyAppContext();
        //Glide 底层默认使用 HttpConnection 进行网络请求,这里替换为 Okhttp 后才能使用本框架,进行 Glide 的加载进度监听
        registry.replace(GlideUrl.class, InputStream.class, new OkHttpUrlLoader.Factory(myAppContext.getOkHttpClient()));

    }

    @Override
    public boolean isManifestParsingEnabled() {
        return false;
    }
}
