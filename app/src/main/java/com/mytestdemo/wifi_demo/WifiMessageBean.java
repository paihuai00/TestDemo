package com.mytestdemo.wifi_demo;

import android.net.wifi.ScanResult;

/**
 * Created by cuishuxiang on 2017/12/26.
 *
 * wifi 信息 Bean
 */

public class WifiMessageBean {
    /**
     * SECURITY_WEP = 3;//WEP
     * SECURITY_WPA = 2;//WPA/WPA2
     * SECURITY_WPA_PSK = 1;//WPA-PSK/WPA2-PSK
     * SECURITY_NONE = 0;//没有密码
     */
    public int security_type;//加密类型
    public ScanResult scanResult;
    public int level;//信号强度

    public int getSecurity_type() {
        return security_type;
    }

    public void setSecurity_type(int security_type) {
        this.security_type = security_type;
    }

    public ScanResult getScanResult() {
        return scanResult;
    }

    public void setScanResult(ScanResult scanResult) {
        this.scanResult = scanResult;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }


    @Override
    public String toString() {
        return "WifiMessageBean{" +
                "security_type=" + security_type +
                ", scanResult=" + scanResult +
                ", level=" + level +
                '}';
    }
}
