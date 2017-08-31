package com.mytestdemo.my_okhttp;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Binder;
import android.os.Environment;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.widget.Toast;

import com.mytestdemo.R;

import java.io.File;

/**
 * Created by Administrator on 2017/6/28.
 */

public class DownloadService extends Service {

    private MyDownAsyncTask downAsyncTask;

    private String downloadUrl;

    private DownListener listener = new DownListener() {
        @Override
        public void onProgress(int progress) {
            getNotificationManager().notify(1, getNotification("Downloading.....", progress));
        }

        @Override
        public void onSucceed() {
            downAsyncTask = null;
            //下载成功，通知将前台服务通知关闭
            stopForeground(true);

            getNotificationManager().notify(1, getNotification("Download Success!", -1));

            Toast.makeText(getBaseContext(), "下载完成!", Toast.LENGTH_LONG).show();

        }

        @Override
        public void onFailed() {
            downAsyncTask = null;

            stopForeground(true);

            getNotificationManager().notify(1, getNotification("Download Failed", -1));

            Toast.makeText(getBaseContext(), "下载失败!", Toast.LENGTH_LONG).show();
        }

        @Override
        public void onPaused() {
            downAsyncTask = null;
            Toast.makeText(getBaseContext(), "下载暂停!", Toast.LENGTH_LONG).show();
        }

        @Override
        public void onCanceled() {
            downAsyncTask = null;

            stopForeground(true);

            Toast.makeText(getBaseContext(), "取消下载!", Toast.LENGTH_LONG).show();
        }
    };
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return new DownloadBinder();
    }

    class DownloadBinder extends Binder {
        public void startDownload(String url) {
            if (downAsyncTask == null) {
                downloadUrl = url;
                downAsyncTask = new MyDownAsyncTask(listener);
                downAsyncTask.execute(downloadUrl);

                startForeground(1, getNotification("Downloading...", 0));

                Toast.makeText(getBaseContext(), "开始下载!", Toast.LENGTH_LONG).show();
            }
        }

        public void pauseDownload() {
            if (downAsyncTask != null) {
                downAsyncTask.pauseDownload();
            }

        }
    }


    private void cancelDownload() {
        if (downAsyncTask != null) {
            downAsyncTask.cancelDownload();
        } else {
            if (downloadUrl != null) {
                //取消下载时候，需要将文件删除，并将通知关闭
                String fileName = downloadUrl.substring(downloadUrl.lastIndexOf("/"));

                String directory = Environment.getExternalStoragePublicDirectory
                        (Environment.DIRECTORY_DOWNLOADS).getPath();

                File file = new File(directory + fileName);

                if (file.exists()) {
                    file.delete();
                }

                getNotificationManager().cancel(1);

                stopForeground(true);

                Toast.makeText(this, "取消下载!", Toast.LENGTH_LONG).show();
            }
        }


    }

    private NotificationManager getNotificationManager() {
        return (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
    }

    private Notification getNotification(String title, int progress) {

        NotificationCompat.Builder notificationCompat = new NotificationCompat.Builder(this);

        notificationCompat.setSmallIcon(R.mipmap.ic_launcher);
        notificationCompat.setContentText(title);

        if (progress >= 0) {
            notificationCompat.setContentText(progress + "%");
            notificationCompat.setProgress(100, progress, false);
        }
        return notificationCompat.build();
    }
}
