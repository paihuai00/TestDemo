package com.wen.mob.login;

import com.wen.mob.PlatformType;

import java.util.HashMap;

import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.PlatformActionListener;
import cn.sharesdk.framework.PlatformDb;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.sina.weibo.SinaWeibo;
import cn.sharesdk.tencent.qq.QQ;
import cn.sharesdk.wechat.friends.Wechat;
import cn.sharesdk.wechat.moments.WechatMoments;

/**
 * 三方登录的管理类
 */

public class AuthorizeManager implements PlatformActionListener {
    /**
     * 要登录到的平台
     */
    private PlatformType platformType;
    private OnAuthorizeListener mOnAuthorizeListener;

    public static AuthorizeManager create() {
        return new AuthorizeManager();
    }

    /**
     * 设置登录的三方平台
     */
    public AuthorizeManager setPlatformType(PlatformType platformType) {
        this.platformType = platformType;
        return this;
    }

    /**
     * 设置授权成功监听
     */
    public AuthorizeManager setOnAuthorizeListener(OnAuthorizeListener onAuthorizeListener) {
        mOnAuthorizeListener = onAuthorizeListener;
        return this;
    }

    /**
     * 三方登录授权
     */
    public void authorize() {
        if (platformType == null) {
            return;
        }
        Platform mCurrentPlatform = null;
        switch (platformType) {
            case QQ:
                mCurrentPlatform = ShareSDK.getPlatform(QQ.NAME);
                break;
            case SinaWeibo:
                mCurrentPlatform = ShareSDK.getPlatform(SinaWeibo.NAME);
                break;
            case WeChat:
                mCurrentPlatform = ShareSDK.getPlatform(Wechat.NAME);
                break;
            case WechatMoments:
                mCurrentPlatform = ShareSDK.getPlatform(WechatMoments.NAME);
                break;
            default:
                break;
        }
        authorize(mCurrentPlatform);
    }

    /**
     * 发起授权
     */
    private void authorize(Platform plat) {
        if (plat == null) {
            return;
        }
        if (plat.isAuthValid()) {
            plat.removeAccount(true);
        }
        plat.setPlatformActionListener(this);
        plat.showUser(null);
    }

    @Override
    public void onComplete(Platform platform, int i, HashMap<String, Object> hashMap) {
        if (platform == null) {
            return;
        }
        PlatformDb db = platform.getDb();
        String userId = db.getUserId();
        AuthorizeData data = new AuthorizeData(userId, platformType);
        if (mOnAuthorizeListener != null) {
            mOnAuthorizeListener.onAuthorizeSucceed(data);
        }
    }

    @Override
    public void onError(Platform platform, int i, Throwable throwable) {

    }

    @Override
    public void onCancel(Platform platform, int i) {

    }

    /**
     * 授权监听
     */
    public interface OnAuthorizeListener {
        void onAuthorizeSucceed(AuthorizeData authorizeData);
    }
}
