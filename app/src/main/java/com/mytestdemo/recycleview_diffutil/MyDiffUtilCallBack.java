package com.mytestdemo.recycleview_diffutil;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.util.DiffUtil;

import java.util.List;

/**
 * Created by cuishuxiang on 2017/3/31.
 */

public class MyDiffUtilCallBack extends DiffUtil.Callback {
    private List<RecycleBean> mOldList;
    private List<RecycleBean> mNewList;

    public MyDiffUtilCallBack(List<RecycleBean> mOldList, List<RecycleBean> mNewList) {
        this.mOldList = mOldList;
        this.mNewList = mNewList;
    }

    @Override
    public int getOldListSize() {
        //条件运算符，如果为true 返回 size
        //                  false 返回 0
        return mOldList != null ? mOldList.size() : 0;
    }

    @Override
    public int getNewListSize() {
        return mNewList != null ? mNewList.size() : 0;
    }

    @Override
    public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
        /**
         * 这里根据你的需求去判断到底是刷新整个item还是，item只有一点变化
         * 从而去下面方法判断是否需要刷新
         *
         * 个人理解：就是该item从旧数据到新数据我们用到的layout都变了
         * 就是多布局，那这边直接返回false，刷新整个item；
         * 如果返回true  就继续执行下面的方法去判断
         */
        return mOldList.get(oldItemPosition).getName().equals(mNewList.get(newItemPosition).getName());
    }

    @Override
    public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
        /**
         * 这边也一样。如果返回true，就不刷新该item  如果返回false
         * 就只刷新该item，并且是动作非常小的刷新
         */
        RecycleBean oldBean = mOldList.get(oldItemPosition);
        RecycleBean newBean = mNewList.get(newItemPosition);

        if (!oldBean.getName().equals(newBean.getName())) {
            return false;
        }

        return true;
    }

    /**
     * 这个方法中你把更新情况存入一个对象后，在后面还能从同一个对象中把更新的情况取出来。
     * @param oldItemPosition
     * @param newItemPosition
     * @return
     */
    @Nullable
    @Override
    public Object getChangePayload(int oldItemPosition, int newItemPosition) {
        RecycleBean oldBean = mOldList.get(oldItemPosition);
        RecycleBean newBean = mNewList.get(newItemPosition);

        Bundle bundle = new Bundle();
        if (!oldBean.getName().equals(newBean.getName())) {
            bundle.putString("name", newBean.getName());
        }
        if (!oldBean.getImg_url().equals(newBean.getImg_url())) {
            bundle.putString("imt_url", newBean.getImg_url());
        }

        if (bundle.size() == 0) {
            return null;
        }else {
            return bundle;
        }

    }
}
