package com.mytestdemo.my_service;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.mytestdemo.MainActivity;
import com.mytestdemo.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by cuishuxiang on 2017/6/17.
 */

public class MyServiceActivity extends AppCompatActivity {

    @BindView(R.id.bind_service)
    Button bindService_btn;
    @BindView(R.id.unbind_service)
    Button unbindService_btn;
    @BindView(R.id.start_service)
    Button startService;
    @BindView(R.id.stop_service)
    Button stopService_Btn;
    @BindView(R.id.notification_btn)
    Button notificationBtn;

    private MyService.DownLoadBinder downLoadBinder;

    private ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            downLoadBinder = (MyService.DownLoadBinder) iBinder;
            downLoadBinder.getProgress();
            downLoadBinder.startDownload();
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            //解除绑定
        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service);
        ButterKnife.bind(this);


    }

    @OnClick({R.id.bind_service, R.id.unbind_service, R.id.start_service, R.id.stop_service,R.id.notification_btn})
    public void onViewClicked(View view) {
        switch (view.getId()) {


            case R.id.start_service:
                Intent startIntent = new Intent(this, MyService.class);
                startService(startIntent);
                break;
            case R.id.stop_service:

                Intent stop = new Intent(this, MyService.class);
                stopService(stop);
                break;

            case R.id.bind_service:
                Intent bindIntent = new Intent(this, MyService.class);
                bindService(bindIntent, connection, BIND_AUTO_CREATE);

                break;
            case R.id.unbind_service:
                unbindService(connection);
                break;

            case R.id.notification_btn:
                NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

                //Pending 是用户点击通知栏 进行的操作
                PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, new Intent(this, MainActivity.class), 1);

                Notification notification = new Notification.Builder(this)
                        .setSmallIcon(R.mipmap.ic_launcher)
                        .setTicker("Ticker")
                        .setContentText("Content")
                        .setContentTitle("Title")
//                        .setContentIntent(pendingIntent)
                        .build();

                notificationManager.notify(1,notification);
                break;

        }
    }

}
