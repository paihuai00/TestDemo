package com.mytestdemo;

import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Administrator on 2017/6/28.
 *
 * 封装一个网络请求 Okhttp util
 */

public class HttpUtils {

    public static void getOkttp(String address, Callback callback) {
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url(address)
                .build();

        client.newCall(request).enqueue(callback);
    }

}
