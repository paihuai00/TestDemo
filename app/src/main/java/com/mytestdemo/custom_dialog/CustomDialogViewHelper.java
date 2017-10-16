package com.mytestdemo.custom_dialog;

import android.view.View;

import java.lang.ref.WeakReference;

/**
 * Created by cuishuxiang on 2017/10/12.
 *
 * 自定义 dialog  View的辅助处理类
 */
class CustomDialogViewHelper {
    private View mConetntView;
    private int mContentViewId;

    public void setContentView(View contentView) {
        this.mConetntView = contentView;
    }

    public void setContentView(int contentViewId) {
        this.mContentViewId = contentViewId;
    }

    public void setText(int viewId, CharSequence charSequence) {

    }

    public void setOnClickListener(int viewId, WeakReference<View.OnClickListener> onClickListenerWeakReference) {


    }

    public int getContentViewId() {
        return mContentViewId;
    }

    public View getContentView() {
        return mConetntView;
    }
}
