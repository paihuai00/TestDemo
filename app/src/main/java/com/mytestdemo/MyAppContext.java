package com.mytestdemo;

import android.app.Activity;
import android.app.Application;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.bigkoo.pickerview.BuildConfig;
import com.bumptech.glide.annotation.GlideModule;
import com.bumptech.glide.module.AppGlideModule;
import com.kingja.loadsir.core.LoadSir;
import com.mytestdemo.load_state.callback.CustomCallback;
import com.mytestdemo.load_state.callback.EmptyCallback;
import com.mytestdemo.load_state.callback.ErrorCallback;
import com.mytestdemo.load_state.callback.LoadingCallback;
import com.mytestdemo.load_state.callback.TimeoutCallback;
import com.mytestdemo.my_galleryfinal.GlideImageLoader;
import com.orhanobut.logger.LogAdapter;
import com.orhanobut.logger.Logger;

import cn.finalteam.galleryfinal.CoreConfig;
import cn.finalteam.galleryfinal.FunctionConfig;
import cn.finalteam.galleryfinal.GalleryFinal;
import cn.finalteam.galleryfinal.ImageLoader;
import cn.finalteam.galleryfinal.ThemeConfig;
import me.jessyan.progressmanager.ProgressManager;
import okhttp3.OkHttpClient;

/**
 * Created by cuishuxiang on 2017/4/13.
 */
public class MyAppContext extends Application {
    private static final String TAG = "MyAppContext";

    private OkHttpClient mOkHttpClient;

    @Override
    public void onCreate() {
        initLiftcycle();
        super.onCreate();

        //照片选择器
        initGallerFilaa();

        //测试Application中的回调方法


        //配置  不同(网络错误，没有数据。。。)状态的加载
        initState();


        initOkhttp();

//        Logger.addLogAdapter(new LogAdapter() {
//            @Override
//            public boolean isLoggable(int priority, String tag) {
//                return false;
//            }
//
//            @Override
//            public void log(int priority, String tag, String message) {
//
//            }
//        });
    }

    /**
     * 使用 progressManager 获得加载的百分比
     */
    private void initOkhttp() {
        mOkHttpClient = ProgressManager.getInstance()
                .with(new OkHttpClient.Builder())
                .build();
    }

    public OkHttpClient getOkHttpClient() {
        if (mOkHttpClient == null) {
            mOkHttpClient = ProgressManager.getInstance()
                    .with(new OkHttpClient.Builder())
                    .build();
        }
        return mOkHttpClient;
    }

    private void initState() {
        LoadSir.beginBuilder()
                .addCallback(new ErrorCallback())//'添加各种状态页
                .addCallback(new EmptyCallback())
                .addCallback(new LoadingCallback())
                .addCallback(new TimeoutCallback())
                .addCallback(new CustomCallback())
                .setDefaultCallback(LoadingCallback.class)//设置默认状态页
                .commit();

    }

    private void initLiftcycle() {
        /**
         * 在子类的create()的super中执行
         * 参考：http://www.jianshu.com/p/75a5c24174b2#
         */
        registerActivityLifecycleCallbacks(new ActivityLifecycleCallbacks() {
            @Override
            public void onActivityCreated(final Activity activity, Bundle bundle) {
//                if (activity.getClass().getSimpleName().equals("MainActivity")) {
//                    activity.setContentView(((BaseInterface) activity).initView());
//                }

                Log.d(TAG, "onActivityCreated:   "+activity.getClass().getSimpleName());
                //这里全局给Activity设置toolbar和title,

                if (activity.getActionBar() != null && activity.getActionBar().isShowing()) {
                    activity.getActionBar().hide();
                }

                Log.d(TAG, "onActivityCreated:   "+activity.findViewById(R.id.all_middle_txt ));
                if (activity.findViewById(R.id.all_middle_txt )!= null) {
                    ((TextView) activity.findViewById(R.id.all_middle_txt)).setText(activity.getClass().getSimpleName() + "的标题");
                }

                if (activity.findViewById(R.id.all_left_back) != null) {
                    activity.findViewById(R.id.all_left_back).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            activity.onBackPressed();
                        }
                    });
                }
//                Toast.makeText(getBaseContext(), activity.getClass().getSimpleName()+activity.findViewById(R.id.lazy_btn), Toast.LENGTH_SHORT).show();
                if (activity.findViewById(R.id.lazy_btn) != null) {
                    Toast.makeText(getBaseContext(), activity.getClass().getSimpleName(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onActivityStarted(Activity activity) {
                Log.d(TAG, "onActivityStarted:  "+activity.getClass().getSimpleName());
            }

            @Override
            public void onActivityResumed(Activity activity) {
                Log.d(TAG, "onActivityResumed:    "+activity.getClass().getSimpleName());
            }

            @Override
            public void onActivityPaused(Activity activity) {
                Log.d(TAG, "onActivityPaused:   "+activity.getClass().getSimpleName());
            }

            @Override
            public void onActivityStopped(Activity activity) {
                Log.d(TAG, "onActivityStopped:  "+activity.getClass().getSimpleName());
            }

            @Override
            public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {

                Log.d(TAG, "onActivitySaveInstanceState:   "+activity.getClass().getSimpleName());
            }

            @Override
            public void onActivityDestroyed(Activity activity) {

                Log.d(TAG, "onActivityDestroyed:  "+activity.getClass().getSimpleName());
            }
        });
    }

    private void initGallerFilaa() {
        //配置主题
        //ThemeConfig.CYAN
        ThemeConfig theme = new ThemeConfig.Builder()
        .setTitleBarBgColor(Color.parseColor("#3F51B5"))
        .build();
        //配置功能
        FunctionConfig functionConfig = new FunctionConfig.Builder()
                .setEnableCamera(true)//开启相机功能
                .setEnableEdit(true)//开启编辑功能
                .setEnableCrop(true)//开启裁剪功能
                .setEnableRotate(true)//开启选择功能
                .setCropSquare(true)//裁剪正方形
                .setEnablePreview(true)//是否开启预览功能
                .build();

        //配置imageloader
        ImageLoader imageloader = new GlideImageLoader();
        //设置核心配置信息
        CoreConfig coreConfig = new CoreConfig.Builder(this, imageloader, theme)
                .setDebug(BuildConfig.DEBUG)
                .setFunctionConfig(functionConfig)
                .build();

        GalleryFinal.init(coreConfig);

    }
}
