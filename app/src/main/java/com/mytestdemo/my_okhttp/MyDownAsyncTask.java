package com.mytestdemo.my_okhttp;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Environment;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.lang.reflect.Method;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Administrator on 2017/6/28.
 */

public class MyDownAsyncTask extends AsyncTask<String, Integer, Integer> {
    private static final int TYPE_SUCCESS = 0;
    private static final int TYPE_FAILED = 1;
    private static final int TYPE_PAUSED = 2;
    private static final int TYPE_CANCELED = 3;

    private DownListener downListener;

    private boolean isCanceled = false;
    private boolean isPaused = false;
    private int lastProgress;

    public MyDownAsyncTask(DownListener downListener) {
        this.downListener = downListener;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected Integer doInBackground(String... params) {

        InputStream inputStream = null;

        RandomAccessFile saveFile = null;

        File file = null;
        try {

            long downLoadLength = 0;
            String downloadUrl = params[0];
            String fileName = downloadUrl.substring(downloadUrl.lastIndexOf("/"));
            String directory = Environment.
                    getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)
                    .getPath();
            file = new File(directory + fileName);

            if (file.exists()) {
                downLoadLength = file.length();
            }

            long contentLength = getContentLength(downloadUrl);

            if (contentLength == 0) {
                return TYPE_FAILED;
            } else if (contentLength == downLoadLength) {
                //已下载字节数，跟文件总的字节数相同，说明下载完成
                return TYPE_SUCCESS;
            }

            OkHttpClient okHttpClient = new OkHttpClient();
            Request request = new Request.Builder()
                    //断点下载，制定从哪个字节开始下载
                    .addHeader("RANGE", "bytes=" + downLoadLength + "-")
                    .url(downloadUrl)
                    .build();

            Response response = okHttpClient.newCall(request).execute();

            if (response != null) {
                inputStream = response.body().byteStream();

                saveFile = new RandomAccessFile(file, "rw");
                saveFile.seek(downLoadLength);//跳过已经下载的字节

                byte[] bytes = new byte[1024];

                int total = 0;

                int len;

                while ((len = inputStream.read(bytes)) != -1) {
                    if (isCanceled) {
                        return TYPE_CANCELED;
                    } else if (isPaused) {
                        return TYPE_PAUSED;
                    } else {
                        total += len;

                        saveFile.write(bytes, 0, len);

                        //计算下载的百分比
                        int progress = (int) ((total + downLoadLength) * 100 / contentLength);
                        publishProgress(progress);
                    }
                }

                response.body().close();

                return TYPE_SUCCESS;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                if (inputStream != null) {
                    inputStream.close();
                }
                if (saveFile != null) {
                    saveFile.close();
                }

                if (isCanceled && file != null) {
                    file.delete();
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return TYPE_FAILED;
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        int progress = values[0];

        if (progress > lastProgress) {
            downListener.onProgress(progress);
            lastProgress = progress;
        }
    }

    @Override
    protected void onPostExecute(Integer integer) {
        switch (integer) {
            case TYPE_SUCCESS:
                downListener.onSucceed();
                break;
            case TYPE_PAUSED:
                downListener.onPaused();
                break;
            case TYPE_FAILED:
                downListener.onFailed();
                break;
            case TYPE_CANCELED:
                downListener.onCanceled();
                break;
        }
    }

    public void pauseDownload() {
        isPaused = true;
    }

    public void cancelDownload() {
        isCanceled = true;
    }

    private long getContentLength(String downloadUrl) throws IOException {
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url(downloadUrl)
                .build();

        Response response = client.newCall(request).execute();

        if (request != null && response.isSuccessful()) {
            long contentLength = response.body().contentLength();

            response.body().close();

            return contentLength;
        }

        return 0;
    }
}
