package com.mytestdemo.wifi_demo;

/**
 * @Created by cuishuxiang
 * @date 2018/1/5.
 */

public abstract class OnWifiStateLisenerAdapter implements OnWifiStateListener {
    //该类，为了不复写所有 OnWifiStateListener 的接口方法
    @Override
    public void onWifiStateDisable() {

    }

    @Override
    public void onWifiStateDisabling() {

    }

    @Override
    public void onWifiStateEnable() {

    }

    @Override
    public void onWifiStateEnabling() {

    }

    @Override
    public void onWifiStateUnKnown() {

    }

    @Override
    public void onWifiConnecting() {

    }

    @Override
    public void onWifiGettingIp() {

    }

    @Override
    public void onWifiPwdError() {

    }

    @Override
    public void onWifiConnectedSuccess(String wifiName) {

    }

    @Override
    public void onWifiStrengthLevelChange(int level) {

    }

    @Override
    public void onWifiDisconnecting() {

    }

    @Override
    public void onWifiDisconnected() {

    }

    @Override
    public void onWifiConnectFail(String wifiName) {

    }
}
