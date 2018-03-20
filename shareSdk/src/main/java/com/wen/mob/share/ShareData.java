package com.wen.mob.share;

import com.wen.mob.PlatformType;

import cn.sharesdk.framework.Platform.ShareParams;


/**
 * @author 要分享的数据实体
 */
public class ShareData {

    /**
     * 要分享到的平台
     */
    public PlatformType mPlatformType;

    /**
     * 要分享到的平台的参数
     */
    public ShareParams mShareParams;
}
