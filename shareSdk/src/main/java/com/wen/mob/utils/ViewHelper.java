package com.wen.mob.utils;

import android.content.Context;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import java.lang.ref.WeakReference;


/**
 * Dialog View显示的辅助类
 */

public class ViewHelper {
    /**
     * Dialog显示的View
     */
    private View mContentView;
    private SparseArray<WeakReference<View>> mViews = new SparseArray<>();

    public ViewHelper(Context context, int layoutResId) {
        mContentView = LayoutInflater.from(context).inflate(layoutResId, null);
    }

    public ViewHelper(View mView) {
        mContentView = mView;
    }

    public View getContentView() {
        return mContentView;
    }

    /**
     * 设置文本
     */
    public void setText(int viewId, CharSequence charSequence) {
        TextView tv = getView(viewId);
        if (tv != null && charSequence != null) {
            tv.setText(charSequence);
        }
    }

    public <T extends View> T getView(int viewId) {
        WeakReference<View> viewWeakReference = mViews.get(viewId);
        View view = null;
        if (viewWeakReference == null) {
            view = mContentView.findViewById(viewId);
            if (view != null) {
                mViews.put(viewId, new WeakReference<>(view));
            }
        } else {
            view = viewWeakReference.get();
        }
        return (T) view;
    }

    /**
     * 设置点击事件
     */
    public void setOnClickListener(int viewId, View.OnClickListener onClickListener) {
        View view = getView(viewId);
        if (view != null && onClickListener != null) {
            view.setOnClickListener(onClickListener);
        }
    }
}
