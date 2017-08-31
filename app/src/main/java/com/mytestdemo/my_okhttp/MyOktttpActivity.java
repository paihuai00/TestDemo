package com.mytestdemo.my_okhttp;

import android.Manifest;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.mytestdemo.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * 测试 OkHttp
 */
public class MyOktttpActivity extends AppCompatActivity {
    private static final String TAG = "MyOktttpActivity";

    @BindView(R.id.get_btn)
    Button getBtn;
    @BindView(R.id.name_edit)
    EditText nameEdit;
    @BindView(R.id.editText2)
    EditText editText2;
    @BindView(R.id.login_btn)
    Button loginBtn;
    @BindView(R.id.service_btn)
    Button serviceBtn;
    @BindView(R.id.toobar)
    Toolbar toobar;
    @BindView(R.id.down_btn)
    Button downBtn;
    @BindView(R.id.activity_my_oktttp)
    LinearLayout activityMyOktttp;

    private String url = "http://101.200.233.12:8068/post/selectPlateList?termId=426";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_oktttp);
        ButterKnife.bind(this);

        setSupportActionBar(toobar);

        initService();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.my_okhttp_test, menu);
        return true;
    }

    private DownloadService.DownloadBinder downloadBinder;

    private ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            downloadBinder = (DownloadService.DownloadBinder) service;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };

    //启动后台服务下载文件
    private void initService() {

        Intent intent = new Intent(this, DownloadService.class);
        startService(intent);//启动服务

        bindService(intent, connection, BIND_AUTO_CREATE);//绑定服务

        if (ContextCompat.checkSelfPermission(MyOktttpActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions
                    (MyOktttpActivity.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
        }

    }

    //测试 get请求
    public void getRequest() {
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url("http://10.4.67.142/cteaController/getTermList")
                .build();

        //根据Request对象发起Get异步Http请求，并添加请求回调
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.d(TAG, "onFailure: 数据请求失败" + e.getMessage());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String result = response.body().string();

                initJsonResult(result);
                Log.d(TAG, "onResponse: 数据请求成功" + result);

                Log.d(TAG, "onResponse: " + response.message());

                //关闭，防止内存泄漏
                if (response.body() != null) {
                    response.close();
                }
            }
        });
    }

    private void initJsonResult(String result) {
        try {
            JSONObject jsonObject = new JSONObject(result);
            final String msg = jsonObject.optString("msg");

            //主线程里面更新UI
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    getBtn.setText(msg);
                }
            });
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    @OnClick({R.id.get_btn, R.id.login_btn, R.id.service_btn})
    public void onClick(View view) {
        if (downloadBinder == null) {
            return;
        }
        switch (view.getId()) {
            case R.id.get_btn:
                getRequest();
                break;
            case R.id.login_btn:

                postLogin();
                break;

            case R.id.service_btn:
                String downloadUrl = "http://sw.bos.baidu.com/sw-search-sp/software/3286ec7d3719a/QQ_8.9.3.21159_setup.exe";

                downloadBinder.startDownload(downloadUrl);
                break;
        }
    }

    private void postLogin() {
        String loginUrl = "http://10.4.67.142/userCenter/userLogin";

        String name = nameEdit.getText().toString().trim();
        String pwd = editText2.getText().toString().trim();

        OkHttpClient client = new OkHttpClient();

        //传递参数
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("usr_name", name);

            jsonObject.put("usr_pwd", pwd);

            jsonObject.put("role_enname", "teacher");

        } catch (JSONException e) {
            e.printStackTrace();
        }


        String jsonParams = jsonObject.toString();

        Log.d(TAG, "postLogin: " + jsonParams);

        RequestBody body = RequestBody.create(MediaType.parse("application/json"), jsonParams);

        final Request request = new Request.Builder()
                .url(loginUrl)
                .post(body)
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

                if (response.isSuccessful()) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(getBaseContext(), "登录成功！", Toast.LENGTH_SHORT).show();
                        }
                    });


                    Log.d(TAG, "onResponse: " + response.body().string());
                }
            }
        });


        //------------*************第二种测试方法**************-------------


    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbindService(connection);

    }
}
