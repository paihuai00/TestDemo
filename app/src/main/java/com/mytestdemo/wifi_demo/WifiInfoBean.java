package com.mytestdemo.wifi_demo;

import android.net.wifi.ScanResult;

/**
 * @Created by cuishuxiang
 * @date 2018/1/5.
 *
 * 定义一个  wifi 的bean对象；
 */

public class WifiInfoBean {
    private ScanResult scanResult;
    private String ssid;
    private int level;//wifi信号 0-5

    /**
     * Wifi 加密方式
     * static final int SECURITY_NONE = 0;  无密码
     * static final int SECURITY_WEP = 1;
     * static final int SECURITY_PSK = 2;
     * static final int SECURITY_EAP = 3;
     *
     */
    private int securityType;


    public ScanResult getScanResult() {
        return scanResult;
    }

    public void setScanResult(ScanResult scanResult) {
        this.scanResult = scanResult;
    }

    public String getSsid() {
        return ssid;
    }

    public void setSsid(String ssid) {
        this.ssid = ssid;
    }

    public int getSecurityType() {
        return securityType;
    }

    public void setSecurityType(int securityType) {
        this.securityType = securityType;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }
}
