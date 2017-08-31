package com.mytestdemo.my_httpurlconnection;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ScrollView;
import android.widget.TextView;

import com.mytestdemo.HttpUtils;
import com.mytestdemo.MainActivity;
import com.mytestdemo.R;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Administrator on 2017/6/27.
 */

public class MyHttpUrlConnetcionActivity extends AppCompatActivity {
    private static final String TAG = "MyHttpUrlConnetcionActi";

    @BindView(R.id.content_txt)
    TextView contentTxt;
    @BindView(R.id.scrollview)
    ScrollView scrollview;
    @BindView(R.id.http_btn)
    Button httpBtn;
    @BindView(R.id.okhttp_btn)
    Button okhttpBtn;

    private HttpURLConnection httpURLConnection;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_httpurlconnection);
        ButterKnife.bind(this);

//        initHttpConnection();
//        initOkHttp();
    }

    /**
     * httpURLConnection 实现请求
     */
    private void initHttpConnection() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    URL url = new URL("https://www.baidu.com");

                    try {
                        httpURLConnection = (HttpURLConnection) url.openConnection();

                        httpURLConnection.setReadTimeout(8000);
                        httpURLConnection.setRequestMethod("GET");
                        InputStream inputStream = httpURLConnection.getInputStream();

                        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

                        StringBuilder response = new StringBuilder();

                        String line;

                        while ((line = bufferedReader.readLine()) != null) {
                            response.append(line);
                        }


                        showText(response.toString());

                        if (bufferedReader != null) {
                            bufferedReader.close();
                        }

                        if (httpURLConnection != null) {

                            httpURLConnection.disconnect();
                        }

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }
            }
        }).start();

    }

    /**
     * OkHttp实现请求
     */
    private void initOkHttp() {

//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                try {
//                    OkHttpClient okHttpClient = new OkHttpClient();
//
//                    Request request = new Request.Builder()
//                            .url("https://www.baidu.com")
//                            .build();
//
//                    Response response = okHttpClient.newCall(request).execute();
//
//                    String text = response.body().string();
//
//                    showText(text);
//
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//        }).start();

        HttpUtils.getOkttp("https://www.baidu.com", new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.d(TAG, "onFailure: " + e.getMessage());

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                showText(response.body().string());

            }
        });

    }

    private void showText(final String response) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                contentTxt.setText(response);

            }
        });

    }

    @OnClick({R.id.http_btn, R.id.okhttp_btn})
    public void onClick(View view) {
        contentTxt.setText("");
        switch (view.getId()) {
            case R.id.http_btn:
                initHttpConnection();

                break;
            case R.id.okhttp_btn:
                initOkHttp();

                //测试  通知
//                NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
//
//                Intent intent = new Intent(this, MainActivity.class);
//
//                PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, 0);
//
//                Notification notification = new Notification.Builder(this)
//                        .setSmallIcon(R.mipmap.ic_launcher)
//                        .setContentIntent(pendingIntent)
//                        .setContentTitle("测试Notification")
//                        .build();
//
//                manager.notify(1,notification);

                break;
        }
    }


}
