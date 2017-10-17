package com.mytestdemo.custom_dialog;

import android.content.Context;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import java.lang.ref.WeakReference;

import static android.R.attr.id;

/**
 * Created by cuishuxiang on 2017/10/12.
 *
 * 自定义 dialog  View的辅助处理类
 */
class CustomDialogViewHelper {
    private View mConetntView;
    private int mContentViewId;

    //优化：减少findViewById  WeakReference,防止内存泄漏
    private SparseArray<WeakReference<View>> mViews;

    public CustomDialogViewHelper(){
        mViews = new SparseArray<>();
    }

    public CustomDialogViewHelper(Context context,int layoutResId){
        this();//记得调用this，因为SparseArray 可能会没有实例化
        mConetntView = LayoutInflater.from(context).inflate(layoutResId, null);
    }

    public void setContentView(View contentView) {
        this.mConetntView = contentView;
    }

    public void setContentView(int contentViewId) {
        this.mContentViewId = contentViewId;
    }

    /**
     * 设置文本呢
     * @param viewId
     * @param charSequence
     */
    public void setText(int viewId, CharSequence charSequence) {
        //通过mConetntView 实例化 view，并设置文本

        TextView textView=getView(viewId);

        if (textView != null) {
            textView.setText(charSequence);
        }
    }

    /**
     * 设置点击事件
     *
     * @param viewId
     * @param onClickListener
     */
    public void setOnClickListener(int viewId, View.OnClickListener onClickListener) {
        //先从 集合中，遍历  查看是否存在View  减少View次数
        View view = getView(viewId);
        if (view != null) {
            view.setOnClickListener(onClickListener);
        }

    }

    /**
     * 先看看，mView中，是否存在该View
     *
     * 此处 使用泛型  公共方法
     * @param viewId
     * @return
     */
    public  <T extends View> T getView(int viewId) {
        WeakReference<View> weakReference = mViews.get(viewId);
        View view = null;

        if (weakReference != null) {
            //获得对象
            view = weakReference.get();
        }

        if (view == null) {
            view=mConetntView.findViewById(viewId);
            if (view != null) {//避免 viewId 不存在
                mViews.put(viewId,new WeakReference<>(view));
            }
        }
        return (T) view;
    }

    /**
     * 获取ContentView
     * @return
     */
    public View getContentView() {
        return mConetntView;
    }
}
