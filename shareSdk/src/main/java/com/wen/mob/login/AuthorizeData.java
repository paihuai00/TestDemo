package com.wen.mob.login;

import com.wen.mob.PlatformType;

/**
 * 授权的JavaBean
 */

public class AuthorizeData {
    public String openId;
    public PlatformType platformType;

    public AuthorizeData(String openId, PlatformType platformType) {
        this.openId = openId;
        this.platformType = platformType;
    }
}
