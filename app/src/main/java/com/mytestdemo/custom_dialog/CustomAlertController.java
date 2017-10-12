package com.mytestdemo.custom_dialog;

import android.content.Context;
import android.content.DialogInterface;
import android.util.SparseArray;
import android.view.View;
import android.view.Window;

import java.lang.ref.WeakReference;
import java.util.Dictionary;

/**
 * Created by cuishuxiang on 2017/10/12.
 */

class CustomAlertController {
    private CustomAlertDialog mCustomAlertDialog;
    private Window mWindow;

    private View mView;
    private int mViewLayoutResId;

    public CustomAlertController(CustomAlertDialog customAlertDialog, Window window) {
        this.mCustomAlertDialog = customAlertDialog;
        this.mWindow = window;
    }

    /**
     * 获取 自定义 dialog
     * @return
     */
    public CustomAlertDialog getCustomDialog() {
        return mCustomAlertDialog;
    }

    /**
     * 由于继承自 Dialog ，获取Dialog 的Window
     * @return
     */
    public Window getWindow() {
        return mWindow;
    }


    public static class CustomAlertDialogParams {
        public Context mContext;
        public int mThemeResId;
        public boolean mCancelable = true;//点击阴影，是否可以取消

        //dialog 取消的监听
        public DialogInterface.OnCancelListener mOnCancelListener;
        //dialog 消失的监听
        public DialogInterface.OnDismissListener mOnDismissListener;
        //dialog 按键监听
        public DialogInterface.OnKeyListener mOnKeyListener;

        //布局
        public View mView;
        //布局resId
        public int mViewLayoutResId;

        //用于存放，多个文本
        SparseArray<CharSequence> mTextArray = new SparseArray<>();
        //用于存放，多个View 的点击事件,此时使用软引用，避免内存泄漏
        SparseArray<WeakReference<View.OnClickListener>> mClickListenerArray = new SparseArray<>();

        public CustomAlertDialogParams(Context context, int themeResId) {
            this.mContext = context;
            this.mThemeResId = themeResId;
        }

        /**
         * 绑定 设置参数
         *
         * @param mAlert
         */
        public void apply(CustomAlertController mAlert) {
            //1，设置ViewHelper
            CustomDialogViewHelper viewHelper = null;
            if (viewHelper == null) {
                viewHelper = new CustomDialogViewHelper();

                if (mView!=null)
                    viewHelper.setContentView(mView);

                if (mViewLayoutResId!=0)
                    viewHelper.setContentView(mViewLayoutResId);

            }

            if (viewHelper == null) {
                throw new IllegalArgumentException("CustomAlertDialog 请设置布局setContentView()");
            }

            //设置 布局
            if (viewHelper.getContentViewId()!=0)
                mAlert.getCustomDialog().setContentView(viewHelper.getContentViewId());
            if (viewHelper.getContentView()!=null)
                mAlert.getCustomDialog().setContentView(viewHelper.getContentView());

            //2，设置文本
            int textArraySize = mTextArray.size();
            for (int i = 0; i < textArraySize; i++) {
                viewHelper.setText(mTextArray.keyAt(i), mTextArray.valueAt(i));
            }

            //3,设置点击事件
            int clickArraySize = mClickListenerArray.size();
            for (int i = 0; i < clickArraySize; i++) {
                viewHelper.setOnClickListener(mClickListenerArray.keyAt(i), mClickListenerArray.valueAt(i));

            }
        }
    }
}
