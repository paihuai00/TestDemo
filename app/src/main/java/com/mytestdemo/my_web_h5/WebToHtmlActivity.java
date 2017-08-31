package com.mytestdemo.my_web_h5;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.Toast;

import com.mytestdemo.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by cuishuxiang on 2017/7/5.
 */

public class WebToHtmlActivity extends AppCompatActivity {
    private static final String TAG = "WebToHtmlActivity";
    @BindView(R.id.webView)
    WebView webView;
    WebSettings webSettings;
    @BindView(R.id.call_js_without_args_btn)
    Button callJsWithoutArgsBtn;
    @BindView(R.id.call_js_with_args_btn)
    Button callJsWithArgsBtn;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_html);
        ButterKnife.bind(this);
        initWebView();

        //测试建造者模式
        initBuilderTest();
    }


    private void initBuilderTest() {
        MyBuilderTest myBuilderTest = new MyBuilderTest.Builder(this)
                .setAge(1)
                .setName("name-1")
                .setSchool("school-1")
                .setSex("sex-1")
                .create();

        MyBuilderTest m = new MyBuilderTest.Builder(this)
                .setName("zhangsan22222222222")
                .create();

        Log.d(TAG, "initBuilderTest: " + myBuilderTest.toString());

        Log.d(TAG, "initBuilderTest: " + m.toString());

    }



    private void initWebView() {
        webSettings = webView.getSettings();

        //设置支持  js
        webSettings.setJavaScriptEnabled(true);

//        webView.addJavascriptInterface(new AndroidToJs(), "myAndroid");

        webView.loadUrl("file:///android_asset/my_html.html");
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true);

        webView.setWebChromeClient(new WebChromeClient() {
            /**
             * js  调用 Android
             * @param view
             * @param url
             * @param message
             * @param result
             * @return
             */
            @Override
            public boolean onJsConfirm(WebView view, String url, String message, JsResult result) {
                Log.d(TAG, "onJsConfirm: " + view.getUrl());
                Log.d(TAG, "onJsConfirm: " + result.toString());
                if ("getDevInfo".equals(message)) {
                    showDevInfoToH5();
                } else if (TextUtils.isEmpty(message)) {
                    Toast.makeText(getApplication(), "Android_Native_Toast!", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplication(), "Android_Native_Toast!接收到的参数message：" + message, Toast.LENGTH_SHORT).show();
                }
                //回调通知H5页面用户操作已完成，可以再次点击相关按钮
                result.confirm();
                //设为true，可以消耗掉H5页面的confirm弹窗。
                return true;
            }
        });
    }

    @OnClick({R.id.call_js_without_args_btn, R.id.call_js_with_args_btn})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.call_js_without_args_btn:
                //Android  调用 js  无参的方法
                callJsWithoutArgsBtn.setText("Native调用WebView的有参JS脚本");
                webView.loadUrl("javascript:changeDemoSubtitle()");
                break;
            case R.id.call_js_with_args_btn:
                callJsWithArgsBtn.setText("参数为：" + showDevInfo());
                showDevInfoToH5();
                break;
        }
    }

//    class AndroidToJs {
//
//        @JavascriptInterface
//        public void show(String message) {
//            Toast.makeText(getBaseContext(), "Js 调用了Android" + message, Toast.LENGTH_SHORT).show();
//        }
//    }

    /**
     * android 调用js 带参数
     */
    private void showDevInfoToH5() {
        webView.loadUrl("javascript:" + showDevInfo());
    }

    private String showDevInfo() {
        return "showDevInfo('手机型号:" + Build.MODEL +
                ",SDK版本:" + Build.VERSION.SDK +
                ",系统版本:" + Build.VERSION.RELEASE + "')";
    }
}
