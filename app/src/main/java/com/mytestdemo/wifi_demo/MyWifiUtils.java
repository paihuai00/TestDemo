package com.mytestdemo.wifi_demo;

import android.annotation.SuppressLint;
import android.content.Context;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiManager;
import android.text.TextUtils;

import com.zhy.autolayout.utils.L;

import java.util.ArrayList;
import java.util.List;

/**
 * @Created by cuishuxiang
 * @date 2018/1/4.
 * <p>
 * Wifi 工具类 (6.0以上，需要注意权限处理)
 */
@SuppressLint("MissingPermission")
public class MyWifiUtils {
    private static final String TAG = MyWifiUtils.class.getSimpleName().toString();

    public static MyWifiUtils myWifiUtils;

    private WifiManager wifiManager;
    private Context context;

    //扫描出的Wifi列表
    private List<ScanResult> mScanResults;

    //获得 当前列表的信息
    private List<WifiInfoBean> wifiInfoBeanList;


    //Wifi 加密方式
    static final int SECURITY_NONE = 0;
    static final int SECURITY_WEP = 1;
    static final int SECURITY_PSK = 2;
    static final int SECURITY_EAP = 3;


    /**
     * 私有构造器
     *
     * @param context
     */
    private MyWifiUtils(Context context) {
        this.context = context;
        //获得 wifiManager
        wifiManager = (WifiManager) context.getApplicationContext().getSystemService(Context.WIFI_SERVICE);
    }

    /**
     * 单例
     *
     * @param context
     * @return
     */
    public static MyWifiUtils getInstance(Context context) {
        if (myWifiUtils == null) {
            synchronized (MyWifiUtils.class) {
                if (myWifiUtils == null) {
                    myWifiUtils = new MyWifiUtils(context);
                }
            }
        }
        return myWifiUtils;
    }


    //-------------------------- Wifi开闭 扫描的方法--------------------------------------------
    /**
     * 打开 Wifi 开关
     */
    public void openWifi() {
        //关闭状态，就打开
        if (wifiManager != null && !isWifiEnable()) {
            wifiManager.setWifiEnabled(true);
        }
    }

    /**
     * 关闭wifi开关
     */
    public void closeWifi() {
        //打开状态，就关闭
        if (wifiManager != null && isWifiEnable()) {
            wifiManager.setWifiEnabled(false);
        }
    }

    /**
     * 扫描当前可用WiFi
     */
    public void startWiFiScan() {
        //1,判断wifi是否打开
        if (!isWifiEnable()) {
            wifiManager.setWifiEnabled(true);
        }
        //2，开始扫描
        wifiManager.startScan();
        //3,得到扫描结果
        mScanResults = wifiManager.getScanResults();
    }

    /**
     * 判断当前 WiFi 打开状态；
     *
     * @return {@code true} if Wi-Fi is enabled
     */
    public boolean isWifiEnable() {
        if (wifiManager != null) {
            return wifiManager.isWifiEnabled();
        }
        return false;
    }

    //----------------------------获取 扫描结果，获取已配置的wifi列表 , 计算wifi信号强度----------------------------------
    /**
     * 获取扫描的结果
     * @return
     */
    public List<ScanResult> getScanResults() {
        return mScanResults == null ? null : mScanResults;
    }

    /**
     * 根据 getScanResults()
     * 扫描的结果，得到 需的WifiInfoBean
     */
    public List<WifiInfoBean> getWifiInfoBeanList() {
        if (wifiInfoBeanList==null)
            wifiInfoBeanList = new ArrayList<>();

        if (getScanResults() == null) {
            //mScanResults =null 先扫描
            startWiFiScan();
        }

        //通过 mScanResults 得到所需要的 info
        for (ScanResult scanResult : mScanResults) {
            WifiInfoBean wifiInfoBean = new WifiInfoBean();
            wifiInfoBean.setScanResult(scanResult);
            wifiInfoBean.setLevel(getWifiLevel(scanResult.level, 5));//需要验证等级是否有问题
            wifiInfoBean.setSsid(scanResult.SSID);//wifi名称
            wifiInfoBean.setSecurityType(getWifiSecurity(scanResult.capabilities));//wifi加密类型

            wifiInfoBeanList.add(wifiInfoBean);

        }

        return wifiInfoBeanList;
    }

    /**
     * 得到已配置过的wifi列表
     * @return
     */
    public List<WifiConfiguration> getConfigWifiList() {
        return wifiManager.getConfiguredNetworks();
    }

    /**
     * 获取wifi的加密方式 , 根据 ScanResult 的capabilityes 来判断
     *
     * 返回 -1 capabilityes 有误
     *
     * 注：这里只是根据capabilityes 简单判断加密方式；
     */
    public int getWifiSecurity(String capabilityes){
        if (TextUtils.isEmpty(capabilityes)) {
            return -1;
        }
        if (capabilityes.contains("WEP")) {
            return SECURITY_WEP;
        } else if (capabilityes.contains("PSK")) {
            return SECURITY_PSK;
        } else if (capabilityes.contains("EAP")) {
            return SECURITY_EAP;
        }else {
            //不加密
            return SECURITY_NONE;
        }
    }

    /**
     * 得到wifi信号强度level
     * @param rssi  WifiInfo 中的rssi； 或者 ScanResult 中的level
     * @param levelYouWant 你想生成的wifi 范围
     * @return
     */
    public int getWifiLevel(int rssi,int levelYouWant) {
        return WifiManager.calculateSignalLevel(rssi, levelYouWant);
    }


}
