package com.mytestdemo.wifi_demo;

/**
 * @Created by cuishuxiang
 * @date 2018/1/5.
 */

public interface OnWifiStateListener {
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
    void onWifiStateDisable();

    void onWifiStateDisabling();

    void onWifiStateEnable();//WIFI网卡可用

    void onWifiStateEnabling();//WIFI网正在打开

    void onWifiStateUnKnown();//未知网卡状态

    /**
     * wifi连接状态
     * <p>
     * 1，正在连接
     * 2，获取ip
     * 3，密码错误
     * 4，连接成功
     * 5, wifi强度变化
     * 6, 正在断开
     * 7，已断开
     * 8, 连接失败
     */
    void onWifiConnecting();

    void onWifiGettingIp();

    void onWifiPwdError();

    void onWifiConnectedSuccess(String wifiName);

    void onWifiStrengthLevelChange(int level);

    void onWifiDisconnecting();

    void onWifiDisconnected();

    void onWifiConnectFail(String wifiName);
}
