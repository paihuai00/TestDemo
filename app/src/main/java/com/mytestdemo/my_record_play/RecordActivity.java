package com.mytestdemo.my_record_play;

import android.media.MediaRecorder;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.mytestdemo.R;

import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by cuishuxiang on 2017/8/5.
 */

public class RecordActivity extends AppCompatActivity {
    private static final String TAG = "RecordActivity";

    MediaRecorder recorder;

    private String PATH_NAME;

    @BindView(R.id.record_btn)
    Button recordBtn;
    @BindView(R.id.stop_record_btn)
    Button stop_record_btn;
    @BindView(R.id.play_record_btn)
    Button playRecordBtn;
    @BindView(R.id.stop_btn)
    Button stopBtn;
    @BindView(R.id.replay_btn)
    Button replayBtn;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record);
        ButterKnife.bind(this);

        initView();
    }

    private void initView() {
        //设置sdcard路径
        PATH_NAME = Environment.getExternalStorageDirectory().getAbsolutePath();
        PATH_NAME = PATH_NAME + "/audioTest.mp3";

        recorder = new MediaRecorder();

        //设置音频资源的来源包括：麦克风，通话上行，通话下行等；程序中设定音频来源为麦克风
        recorder.setAudioSource(MediaRecorder.AudioSource.MIC);

        //设置音频编码器，程序中设定音频编码为AMR窄带编码
        recorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);

        recorder.setOutputFile(PATH_NAME);

        try {
            recorder.prepare();
        } catch (IOException e) {
            Log.d(TAG, "initView: " + e.getMessage());
            e.printStackTrace();
        }

    }

    @OnClick({R.id.record_btn, R.id.stop_record_btn, R.id.play_record_btn, R.id.stop_btn, R.id.replay_btn})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.record_btn:
                Log.d(TAG, "onViewClicked: 录音按钮");

                recorder.start();
                break;
            case R.id.stop_record_btn:
                recorder.stop();
                break;

            case R.id.play_record_btn:

                break;
            case R.id.stop_btn:
                break;
            case R.id.replay_btn:
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (recorder != null) {
            recorder.release();
        }
    }
}
