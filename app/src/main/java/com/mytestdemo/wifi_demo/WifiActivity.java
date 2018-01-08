package com.mytestdemo.wifi_demo;

import android.content.DialogInterface;
import android.net.wifi.ScanResult;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
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

    private MyWifiUtils myWifiUtils;

    private WifiRvListAdapter wifiRvListAdapter;

    private List<WifiInfoBean> wifiInfoBeanList = new ArrayList<>();

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

        wifiRvListAdapter = new WifiRvListAdapter(this, wifiInfoBeanList);

        dialog_rv.setLayoutManager(new LinearLayoutManager(this));

        dialog_rv.setAdapter(wifiRvListAdapter);

        if (myWifiUtils.isWifiEnable()){
            wifiInfoBeanList.clear();
            wifiInfoBeanList.addAll(myWifiUtils.getWifiInfoBeanList());
            wifiRvListAdapter.notifyDataSetChanged();
        }

        wifiRvListAdapter.setOnRvItemClickListener(new WifiRvListAdapter.OnRvItemClickListener() {
            @Override
            public void onItemClickListener(View view, int position) {
                final WifiInfoBean wifiInfoBean = wifiInfoBeanList.get(position);
                //1，首先判断是否配置过 -1没有配置过
                int netId = myWifiUtils.isWifiConfig(wifiInfoBean.getScanResult());
                if (-1 == netId) {
                    //2，判断是否要密码( 0 是没有密码)
                    if (MyWifiUtils.SECURITY_NONE != wifiInfoBean.getSecurityType()) {
                        final EditText pwdEt = new EditText(WifiActivity.this);
                        //弹出输入密码对话框
                        new AlertDialog.Builder(WifiActivity.this)
                                .setView(pwdEt)
                                .setTitle("请输入密码")
                                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        myWifiUtils.configWifi(wifiInfoBean.getScanResult(), pwdEt.getText().toString().trim(), wifiInfoBean.getSecurityType());
                                        myWifiUtils.connectWifi(myWifiUtils.isWifiConfig(wifiInfoBean.getSsid()));
                                        if (load_pb != null && load_pb.getVisibility() == View.INVISIBLE) {
                                            //loading状态显示
                                            load_pb.setVisibility(View.VISIBLE);
                                        }
                                    }
                                })
                                .setNegativeButton("取消", null)
                                .create().show();
                    }else {
                        //没配置，wifi也没密码的情况
                        myWifiUtils.configWifi(wifiInfoBean.getScanResult(), "", wifiInfoBean.getSecurityType());
                        myWifiUtils.connectWifi(myWifiUtils.isWifiConfig(wifiInfoBean.getSsid()));

                        if (load_pb != null && load_pb.getVisibility() == View.INVISIBLE) {
                            //loading状态显示
                            load_pb.setVisibility(View.VISIBLE);
                        }
                    }
                }
                //2，否配置过的 wifi
                if (-1 != netId) {
                    if (load_pb != null && load_pb.getVisibility() == View.INVISIBLE) {
                        //loading状态显示
                        load_pb.setVisibility(View.VISIBLE);
                    }
                    myWifiUtils.connectWifi(netId);
                }

            }

            @Override
            public void onLongItemClickListener(View view, int position) {
                //长按，如果是已经连接的，就弹框 是否断开
                final ScanResult result = wifiInfoBeanList.get(position).getScanResult();
                if (myWifiUtils.getConnectWifiInfo().getSSID().equals("\""+result.SSID+"\"")){
                    new AlertDialog.Builder(WifiActivity.this)
                            .setTitle("是否断开连接？")
                            .setPositiveButton("断开", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    myWifiUtils.disconnectWifi();
                                }
                            })
                            .setNegativeButton("不保存", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    myWifiUtils.forgetWifi(result.SSID);
                                }
                            })
                            .create().show();
                    return;
                }
            }
        });

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
         * 如果开的话，就扫描一次WiFi
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

        myWifiUtils.setOnWifiStateListener(new OnWifiStateLisenerAdapter() {
            @Override
            public void onWifiStateDisable() {
                super.onWifiStateDisable();
                Log.d(TAG, "onWifiStateDisable: ");
            }

            @Override
            public void onWifiStateDisabling() {
                super.onWifiStateDisabling();
                Log.d(TAG, "onWifiStateDisabling: ");
            }

            @Override
            public void onWifiStateEnable() {
                super.onWifiStateEnable();
                Log.d(TAG, "onWifiStateEnable: ");
            }

            @Override
            public void onWifiStateEnabling() {
                super.onWifiStateEnabling();
                Log.d(TAG, "onWifiStateEnabling: ");
            }

            @Override
            public void onWifiStateUnKnown() {
                super.onWifiStateUnKnown();
                Log.d(TAG, "onWifiStateUnKnown: ");
            }

            @Override
            public void onWifiConnecting() {
                super.onWifiConnecting();
                Log.d(TAG, "onWifiConnecting: ");
            }

            @Override
            public void onWifiGettingIp() {
                super.onWifiGettingIp();
                Log.d(TAG, "onWifiGettingIp: ");
            }

            @Override
            public void onWifiPwdError() {
                super.onWifiPwdError();
                Log.d(TAG, "onWifiPwdError: ");
            }

            @Override
            public void onWifiConnectedSuccess(String wifiName) {
                super.onWifiConnectedSuccess(wifiName);
                Log.d(TAG, "onWifiConnectedSuccess: "+wifiName);
                if (load_pb != null && load_pb.getVisibility() == View.VISIBLE) {
                    //loading状态显示
                    load_pb.setVisibility(View.INVISIBLE);
                }
            }

            @Override
            public void onWifiStrengthLevelChange(int level) {
                super.onWifiStrengthLevelChange(level);
                Log.d(TAG, "onWifiStrengthLevelChange: ");
            }

            @Override
            public void onWifiDisconnecting() {
                super.onWifiDisconnecting();
                Log.d(TAG, "onWifiDisconnecting: ");
            }

            @Override
            public void onWifiDisconnected() {
                super.onWifiDisconnected();
                Log.d(TAG, "onWifiDisconnected: ");
            }

            @Override
            public void onWifiConnectFail(String wifiName) {
                super.onWifiConnectFail(wifiName);
                Log.d(TAG, "onWifiConnectFail: " + wifiName);
            }
        });

    }

    @OnClick(R.id.open_wifi)
    public void onViewClicked() {
        if (wifiDialogPlus != null) {
            wifiDialogPlus.show();
        }
    }


    @Override
    protected void onDestroy() {
        if (myWifiUtils != null) {
            myWifiUtils.unRegisterWifiBc();
        }
        super.onDestroy();
    }
}
