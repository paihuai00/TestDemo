package com.mytestdemo.wifi_demo;

import android.net.wifi.ScanResult;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ProgressBar;
import android.widget.Switch;

import com.mytestdemo.BaseActivity;
import com.mytestdemo.R;
import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.OnClickListener;
import com.orhanobut.dialogplus.ViewHolder;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by cuishuxiang
 * date  2018/1/4.
 *
 * wifi 相关
 */

public class WifiActivity extends BaseActivity {
    private static final String TAG = "WifiActivity";
    @BindView(R.id.open_wifi)
    Button openWifi;

    private DialogPlus wifiDialogPlus;
    private SwipeRefreshLayout swipe_refresh;
    private RecyclerView dialog_rv;//wifi列表的rv
    private Switch wifiSwitch;//wifi 开关
    private ProgressBar load_pb;//wifi loading 圆圈

    private List<ScanResult> scanResultList = new ArrayList<>();

    private MyWifiUtils myWifiUtils;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wifi);
        ButterKnife.bind(this);

        initWifiUtils();

        initWifiDialog();
    }

    private void initWifiUtils() {
        myWifiUtils = MyWifiUtils.getInstance(this);

        myWifiUtils.startWiFiScan();//开启扫描

        scanResultList = myWifiUtils.getScanResults();
        Log.d(TAG, "initWifiUtils: scanResultList.size() = " + scanResultList.size());
    }

    //初始化 wifi弹框 相关
    private void initWifiDialog() {
        View wifiDialogView = LayoutInflater.from(this).inflate(R.layout.dialog_wifi_connect, null);

        //wifi 列表
        dialog_rv = (RecyclerView) wifiDialogView.findViewById(R.id.wif_rv);
        //下拉刷新
        swipe_refresh = (SwipeRefreshLayout) wifiDialogView.findViewById(R.id.swipe_refresh);
        //wifi 开关
        wifiSwitch = (Switch) wifiDialogView.findViewById(R.id.wifi_switch);
        //loading 转圈
        load_pb = (ProgressBar) wifiDialogView.findViewById(R.id.load_pb);





        wifiDialogPlus = DialogPlus.newDialog(this)
                .setContentHolder(new ViewHolder(wifiDialogView))
                .setGravity(Gravity.CENTER)
                .setCancelable(false)
                .setContentWidth(800)
                .setContentBackgroundResource(R.drawable.bg_wifi_dialog)
                .setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(DialogPlus dialog, View view) {
                        switch (view.getId()) {
                            case R.id.close_dialog:
                                if (wifiDialogPlus != null && wifiDialogPlus.isShowing()) {
                                    wifiDialogPlus.dismiss();
                                }
                                break;
                        }
                    }
                })
                .create();

        /**
         * 根据开关，判断当前wifi 开闭状态
         */
        wifiSwitch.setChecked(myWifiUtils.isWifiEnable());
        wifiSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                Log.d(TAG, "onCheckedChanged: isChecked = " + isChecked);
                if (isChecked) {
                    myWifiUtils.openWifi();
                }
                if (!isChecked) {
                    myWifiUtils.closeWifi();
                }
            }
        });
    }

    @OnClick(R.id.open_wifi)
    public void onViewClicked() {
        if (wifiDialogPlus != null) {
            wifiDialogPlus.show();
        }
    }
}
