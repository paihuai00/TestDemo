package com.mytestdemo.my_navigation;

/**
 * Created by cuishuxiang on 2017/4/26.
 */

public class ContentModel {
    private int resId;
    private String name;

    public ContentModel(int resId, String name) {
        this.resId = resId;
        this.name = name;
    }

    public int getResId() {
        return resId;
    }

    public void setResId(int resId) {
        this.resId = resId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
