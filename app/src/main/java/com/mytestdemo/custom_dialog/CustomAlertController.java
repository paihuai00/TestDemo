package com.mytestdemo.custom_dialog;

import android.content.Context;
import android.content.DialogInterface;
import android.util.SparseArray;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

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
        public SparseArray<CharSequence> mTextArray = new SparseArray<>();
        //用于存放，多个View 的点击事件,此时使用软引用，避免内存泄漏
        public SparseArray<View.OnClickListener> mClickListenerArray = new SparseArray<>();

        //
        public int mWidth = ViewGroup.LayoutParams.WRAP_CONTENT;

        public int mHeight = ViewGroup.LayoutParams.WRAP_CONTENT;
        /**
         * 默认中间
         */
        public int mGravity = Gravity.CENTER;

        /**
         * 动画
         */
        public int mAnimation = 0;


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

            if (mViewLayoutResId!=0){
                viewHelper = new CustomDialogViewHelper(mContext,mViewLayoutResId);
            }

            if (mView != null) {
                viewHelper=new CustomDialogViewHelper();
                viewHelper.setContentView(mView);
            }
            //如果此时，viewHelper == null 说明没有 设置布局，抛出异常
            if (viewHelper == null) {
                throw new IllegalArgumentException("CustomAlertDialog 请设置布局setContentView()");
            }

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

            //给dialog设置布局
            mAlert.getCustomDialog().setContentView(viewHelper.getContentView());

            //4,配置自定义效果， 全屏  底部弹出  动画 等....

            Window window = mAlert.getWindow();

            //设置位置
            window.setGravity(mGravity);

            if (mAnimation != 0) {
                window.setWindowAnimations(mAnimation);
            }

            //设置  宽  高
            WindowManager.LayoutParams layoutParams = window.getAttributes();
            layoutParams.width = mWidth;
            layoutParams.height = mHeight;
            window.setAttributes(layoutParams);
        }
    }
}
