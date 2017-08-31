package com.mytestdemo.my_okhttp;

/**
 * Created by Administrator on 2017/6/28.
 */

public interface DownListener {
    void onProgress(int progress);

    void onSucceed();

    void onFailed();

    void onPaused();

    void onCanceled();
}
