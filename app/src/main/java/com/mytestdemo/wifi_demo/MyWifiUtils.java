package com.mytestdemo.wifi_demo;

import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.text.TextUtils;

import com.zhy.autolayout.utils.L;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
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

    //广播
    private WifiBoardCastReceiver wifiBoardCastReceiver;

    //Wifi 加密方式
    static final int SECURITY_NONE = 0;
    static final int SECURITY_WEP = 1;
    static final int SECURITY_PSK = 2;
    static final int SECURITY_EAP = 3;

    //标志位，判断是否注册广播
    public boolean isRegister = false;

    //设置回调
    private OnWifiStateListener onWifiStateListener;

    /**
     * 注册监听的时候，一并注册广播
     *
     * 记得在生命周期结束的时候，{@link #unRegisterWifiBc()} ()}
     *
     * @param onWifiStateListener
     */
    public void setOnWifiStateListener(OnWifiStateListener onWifiStateListener) {
        if (isRegister) return;

        registerWifiBc();

        this.onWifiStateListener = onWifiStateListener;
    }

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

    //----------------------------获取 扫描结果; 当前连接的wifi信息；获取已配置的wifi列表 , 计算wifi信号强度，获取Wifi网卡状态----------------------------------

    /**
     * 获取扫描的结果
     *
     * @return
     */
    public List<ScanResult> getScanResults() {
        //自动排序
        mScanResults = sortByLevel(mScanResults);
        return mScanResults == null ? null : mScanResults;
    }

    /**
     * 将搜索到的wifi根据信号强度从强到时弱进行排序
     * @param list  存放周围wifi热点对象的列表
     */
    private List<ScanResult> sortByLevel(List<ScanResult> list) {
        Collections.sort(list, new Comparator<ScanResult>() {

            @Override
            public int compare(ScanResult lhs, ScanResult rhs) {
                return rhs.level - lhs.level;
            }
        });

        return list;
    }

    /**
     * 获取当前连接 wifi 的信息
     *
     * @return
     */
    public WifiInfo getConnectWifiInfo() {
        return wifiManager.getConnectionInfo();
    }

    /**
     * 根据 getScanResults()
     * 扫描的结果，得到 定义的 WifiInfoBeanList
     */
    public List<WifiInfoBean> getWifiInfoBeanList() {
        if (wifiInfoBeanList == null)
            wifiInfoBeanList = new ArrayList<>();

        wifiInfoBeanList.clear();

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
     *
     * @return
     */
    public List<WifiConfiguration> getConfigWifiList() {
        return wifiManager.getConfiguredNetworks();
    }

    /**
     * 获取wifi的加密方式 , 根据 ScanResult 的capabilityes 来判断
     * <p>
     * 返回 -1 capabilityes 有误
     * <p>
     * 注：这里只是根据capabilityes 简单判断加密方式；
     */
    public int getWifiSecurity(String capabilityes) {
        if (TextUtils.isEmpty(capabilityes)) {
            return -1;
        }
        if (capabilityes.contains("WEP")) {
            return SECURITY_WEP;
        } else if (capabilityes.contains("PSK")) {
            return SECURITY_PSK;
        } else if (capabilityes.contains("EAP")) {
            return SECURITY_EAP;
        } else {
            //不加密
            return SECURITY_NONE;
        }
    }

    /**
     * 得到wifi信号强度level
     *
     * @param rssi         WifiInfo 中的rssi； 或者 ScanResult 中的level
     * @param levelYouWant 你想生成的wifi 范围
     * @return
     */
    public int getWifiLevel(int rssi, int levelYouWant) {
        return WifiManager.calculateSignalLevel(rssi, levelYouWant);
    }


    /**
     * 获取Wifi网卡状态
     * 1.WIFI_STATE_DISABLED : WIFI网卡不可用（1）
     * 2.WIFI_STATE_DISABLING : WIFI网卡正在关闭（0）
     * 3.WIFI_STATE_ENABLED : WIFI网卡可用（3）
     * 4.WIFI_STATE_ENABLING : WIFI网正在打开（2） （WIFI启动需要一段时间）
     * 5.WIFI_STATE_UNKNOWN  : 未知网卡状态
     * <p>
     * return -1 则为wifi处于关闭状态
     */
    public int getWifiState() {
        if (wifiManager.isWifiEnabled()) {
            return wifiManager.getWifiState();
        }
        return -1;
    }


    //------------------判断wifi 是否保存,连接 / 断开 / 忘记 wifi 网络---------------------------------------

    /**
     * 判断该wifi是否已经保存
     *
     * @return 返回-1表示没保存，已经保存返回网络id
     */
    public int isWifiConfig(String ssid) {
        if (getConfigWifiList() == null || 0 == getConfigWifiList().size()) {
            return -1;
        }
        List<WifiConfiguration> configWifiList = getConfigWifiList();
        for (WifiConfiguration configuration : configWifiList) {
            if (configuration.SSID.equals("\"" + ssid + "\"")) {
                return configuration.networkId;
            }
        }
        return -1;
    }

    /**
     * 判断该wifi是否已经保存
     *
     * @param scanResult
     * @return 返回-1表示没保存，否则返回网络id
     */
    public int isWifiConfig(ScanResult scanResult) {
        String ssid = scanResult.SSID;
        if (getConfigWifiList() == null || 0 == getConfigWifiList().size()) {
            return -1;
        }
        List<WifiConfiguration> configWifiList = getConfigWifiList();
        for (WifiConfiguration configuration : configWifiList) {
            if (configuration.SSID.equals("\"" + ssid + "\"")) {
                return configuration.networkId;
            }
        }
        return -1;
    }

    /**
     * 连接wifi   并在此处  处理wifi 密码 正确 / 错误的回调
     *
     * @param netId wifi网络id
     * @return 连接结果
     */
    public boolean connectWifi(int netId) {
        if (!wifiManager.enableNetwork(netId, true) && onWifiStateListener != null) {
            onWifiStateListener.onWifiPwdError();
        }
        return wifiManager.enableNetwork(netId, true);
    }

    /**
     * 配置没有保存的wifi
     * 一般只要配置一下几个属性就可以了，其他使用其，默认值
     * @param scanResult
     * @param pwd
     * @return 保存成功则返回该Wifi的网络id，否则-1
     */
    public int configWifi(ScanResult scanResult, String pwd,int security) {
        return configWifi(scanResult.SSID, pwd, security);

    }
    /**
     * 配置方法重载
     */
    public int configWifi(String ssid, String pwd,int security) {
        int result = -1;
        for (ScanResult s : mScanResults) {
            if (s.SSID.equals(ssid)) {
                WifiConfiguration config = new WifiConfiguration();
                config.allowedAuthAlgorithms.clear();
                config.allowedGroupCiphers.clear();
                config.allowedKeyManagement.clear();
                config.allowedPairwiseCiphers.clear();
                config.allowedProtocols.clear();
                config.SSID = "\"" + ssid + "\"";
                config.hiddenSSID = false;
                config.status = WifiConfiguration.Status.ENABLED;

                //配置wifi的时候，增加一个判断，是否已经配置过了；
                if (-1 != isWifiConfig(ssid)) {
                    WifiConfiguration tempConfig = null;

                    for (WifiConfiguration configuration : getConfigWifiList()) {
                        if (configuration.SSID.equals("\"" + ssid + "\"")) {
                            tempConfig = configuration;
                        }
                    }

                    wifiManager.removeNetwork(tempConfig.networkId);
                }


                switch (security){
                    case SECURITY_NONE: // 无密码
//                        config.wepKeys[0] ="\""+pwd+"\"";
//                        config.wepTxKeyIndex = 0;
                        config.allowedKeyManagement.set(WifiConfiguration.KeyMgmt.NONE);
                        break;
                    case SECURITY_WEP:
                        config.wepKeys[0]= "\""+pwd+"\"";
                        config.allowedKeyManagement.set(WifiConfiguration.KeyMgmt.NONE);
                        config.wepTxKeyIndex = 0;
                        config.hiddenSSID = true;
                        config.allowedAuthAlgorithms.set(WifiConfiguration.AuthAlgorithm.SHARED);
                        config.allowedGroupCiphers.set(WifiConfiguration.GroupCipher.CCMP);
                        config.allowedGroupCiphers.set(WifiConfiguration.GroupCipher.TKIP);
                        config.allowedGroupCiphers.set(WifiConfiguration.GroupCipher.WEP40);
                        config.allowedGroupCiphers.set(WifiConfiguration.GroupCipher.WEP104);

                        break;
                    case SECURITY_EAP:
                    case SECURITY_PSK:
                        config.preSharedKey = "\"" + pwd + "\"";
                        config.hiddenSSID = true;
                        config.allowedAuthAlgorithms.set(WifiConfiguration.AuthAlgorithm.OPEN);
                        config.allowedGroupCiphers.set(WifiConfiguration.GroupCipher.TKIP);
                        config.allowedKeyManagement.set(WifiConfiguration.KeyMgmt.WPA_PSK);
                        config.allowedPairwiseCiphers.set(WifiConfiguration.PairwiseCipher.TKIP);
                        //config.allowedProtocols.set(WifiConfiguration.Protocol.WPA);
                        config.allowedGroupCiphers.set(WifiConfiguration.GroupCipher.CCMP);
                        config.allowedPairwiseCiphers.set(WifiConfiguration.PairwiseCipher.CCMP);
                        config.status = WifiConfiguration.Status.ENABLED;
                        break;
                    default:
                        break;
                }
                result = wifiManager.addNetwork(config);
                break;
            }
        }
        return result;
    }


    /**
     * 删除/忘记一个wifi（也就是通常的不保存）
     *
     * @param ssid 要忘记网络名称
     * @return 执行结果
     */
    public boolean forgetWifi(String ssid) {
        for (WifiConfiguration c : getConfigWifiList()) {
            if (c.SSID.equals("\"" + ssid + "\"")) {
                disconnectWifi();
                return wifiManager.removeNetwork(c.networkId);
            }
        }
        return false;
    }

    /**
     * 断开连接
     * @return
     */
    public boolean disconnectWifi(){
        return wifiManager.disableNetwork(getConnectWifiInfo().getNetworkId());
        // mWifiManager.disconnect();//断流
    }


    //-----------------------wifi广播，监听状态-------------------------------
    class WifiBoardCastReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            //如果没有注册广播，直接return
            if (!isRegister) return;

            String action = intent.getAction();

            switch (action) {
                //1,监听 wifi 状态，开启/正在开启/关闭......与wifi的连接无关
                case WifiManager.WIFI_STATE_CHANGED_ACTION:
                    int wifiState = intent.getIntExtra(WifiManager.EXTRA_WIFI_STATE, 0);
                    switch (wifiState) {
                        case WifiManager.WIFI_STATE_ENABLING:
                            //wifi正在启用
                            onWifiStateListener.onWifiStateEnabling();
                            break;
                        case WifiManager.WIFI_STATE_ENABLED:
                            //Wifi已启用
                            onWifiStateListener.onWifiStateEnable();
                            break;
                        case WifiManager.WIFI_STATE_DISABLING:
                            //wifi正在关闭
                            onWifiStateListener.onWifiStateDisabling();
                            break;
                        case WifiManager.WIFI_STATE_DISABLED:
                            //wifi已经关闭
                            onWifiStateListener.onWifiStateDisable();
                            break;
                        case WifiManager.WIFI_STATE_UNKNOWN:
                            //wifi发生未知错误
                            onWifiStateListener.onWifiStateUnKnown();
                            break;
                    }
                    break;

                //2,wifi 连接时   状态
                case WifiManager.NETWORK_STATE_CHANGED_ACTION:
                    NetworkInfo networkInfo = intent.getParcelableExtra(WifiManager.EXTRA_NETWORK_INFO);
                    NetworkInfo.State state = networkInfo.getState();
                    switch (state) {
                        case CONNECTING://wifi正在连接
                            onWifiStateListener.onWifiConnecting();
                            break;
                        case CONNECTED://wifi已连接
                            onWifiStateListener.onWifiConnectedSuccess(networkInfo.getExtraInfo());
                            break;
                        case UNKNOWN://wifi未知状态
                            onWifiStateListener.onWifiStateUnKnown();
                            break;
                        case SUSPENDED://wifi挂起
                            break;
                        case DISCONNECTING://wifi正在断开
                            onWifiStateListener.onWifiDisconnecting();
                            break;
                        case DISCONNECTED://wifi已经断开
                            onWifiStateListener.onWifiDisconnected();
                            break;
                    }
                    //在获取wifi ip状态
                    if (NetworkInfo.DetailedState.OBTAINING_IPADDR.equals(networkInfo.getDetailedState())) {
                        onWifiStateListener.onWifiGettingIp();
                    } else if (NetworkInfo.DetailedState.FAILED.equals(networkInfo.getDetailedState())) {
                        //连接失败
                        onWifiStateListener.onWifiConnectFail(networkInfo.getExtraInfo());
                    }
                    break;

                //3,wifi 强度变化
                case WifiManager.RSSI_CHANGED_ACTION:
                    int level = getWifiLevel(getConnectWifiInfo().getRssi(), 5);
                    onWifiStateListener.onWifiStrengthLevelChange(level);
                    break;

                //4,wifi 密码错误
                case WifiManager.SUPPLICANT_STATE_CHANGED_ACTION:
                    int error = intent.getIntExtra(WifiManager.EXTRA_SUPPLICANT_ERROR, 0);
                    if (WifiManager.ERROR_AUTHENTICATING == error) {
                        //身份认证错误
                        onWifiStateListener.onWifiPwdError();
                    }
                    break;
            }
        }
    }

    /**
     * 注册广播
     */
    private void registerWifiBc() {
        wifiBoardCastReceiver = new WifiBoardCastReceiver();
        IntentFilter mFilter = new IntentFilter();
        mFilter.addAction(WifiManager.RSSI_CHANGED_ACTION); //信号强度变化
        mFilter.addAction(WifiManager.NETWORK_STATE_CHANGED_ACTION); //网络状态变化
        mFilter.addAction(WifiManager.WIFI_STATE_CHANGED_ACTION); //wifi状态，是否连上，密码
        mFilter.addAction(WifiManager.SUPPLICANT_STATE_CHANGED_ACTION); //是不是正在获得IP地址
        mFilter.addAction(WifiManager.NETWORK_IDS_CHANGED_ACTION);
//        mFilter.addAction(ConnectivityManager.CONNECTIVITY_ACTION);
        context.registerReceiver(wifiBoardCastReceiver, mFilter);
        isRegister = true;
    }

    /**
     * 取消广播
     */
    public void unRegisterWifiBc() {
        if (!isRegister) return;
        isRegister = false;
        context.unregisterReceiver(wifiBoardCastReceiver);
    }
}
