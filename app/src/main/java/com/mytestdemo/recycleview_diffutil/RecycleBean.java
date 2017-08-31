package com.mytestdemo.recycleview_diffutil;

/**
 * Created by cuishuxiang on 2017/3/31.
 */

public class RecycleBean {
    private String name;
    private String img_url;

    public RecycleBean(String name, String img_url) {
        this.name = name;
        this.img_url = img_url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImg_url() {
        return img_url;
    }

    public void setImg_url(String img_url) {
        this.img_url = img_url;
    }

    @Override
    public String toString() {
        return super.toString()+name.toString();
    }

}
