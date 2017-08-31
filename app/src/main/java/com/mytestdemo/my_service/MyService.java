package com.mytestdemo.my_service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Created by cuishuxiang on 2017/6/17.
 *
 * 服务测试
 */

public class MyService extends Service {
    private static final String TAG = "MyService";


    private DownLoadBinder mBinder = new DownLoadBinder();

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.d(TAG, "onBind: ");
        return mBinder;
    }


    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "onCreate: ");


    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(TAG, "onStartCommand: ");
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy: ");
    }

    class DownLoadBinder extends Binder {
        public void startDownload() {
            Log.d(TAG, "startDownload: ");
        }

        public int getProgress(){
            Log.d(TAG, "getProgress: ");

            return 0;
        }
    }

}

