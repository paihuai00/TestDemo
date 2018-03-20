package com.wen.mob.utils;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.support.annotation.NonNull;
import android.support.annotation.StyleRes;
import android.util.TypedValue;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;



/**
 * 自定义的Dialog
 */

public class QuickDialog extends Dialog {
    private ViewHelper mViewHelper = null;

    public QuickDialog(@NonNull Context context, @StyleRes int themeResId) {
        super(context, themeResId);
    }

    /**
     * 绑定和设置参数
     */
    public QuickDialog apply(QuickBuilder params) {
        // 1.设置布局
        mViewHelper = null;
        if (params.mViewLayoutResId != 0) {
            mViewHelper = new ViewHelper(getContext(), params.mViewLayoutResId);
        }
        if (params.mView != null) {
            mViewHelper = new ViewHelper(params.mView);
        }
        if (mViewHelper == null) {
            throw new IllegalArgumentException("请调用setContentView方法设置布局");
        }
        // 创建前景和圆角
        final GradientDrawable bg = new GradientDrawable();
        float radius = dp2px(params.mBgRadius);
        bg.setCornerRadii(new float[]{radius, radius, radius, radius, radius, radius, radius, radius});// 1 2 3 4(顺时针)
        bg.setColor(params.mBgColor);
        // 设置背景
        View contentView = mViewHelper.getContentView();
        // 设置Dialog的布局
        setContentView(contentView);
        // 设置背景
        contentView.setBackgroundDrawable(bg);
        // 是否可以取消
        setCancelable(params.mCancelable);
        // 设置监听
        if (params.mOnCancelListener != null) {
            setOnCancelListener(params.mOnCancelListener);
        }
        if (params.mOnDismissListener != null) {
            setOnDismissListener(params.mOnDismissListener);
        }
        if (params.mOnKeyListener != null) {
            setOnKeyListener(params.mOnKeyListener);
        }

        Window window = getWindow();
        // 显示的位置
        window.setGravity(params.mGravity);
        // 动画
        if (params.mAnimation != 0) {
            window.setWindowAnimations(params.mAnimation);
        }
        // 宽高
        WindowManager.LayoutParams windowAttributes = window.getAttributes();
        windowAttributes.width = (int) (params.mWidth * params.mScale);
        windowAttributes.height = params.mHeight;
        window.setAttributes(windowAttributes);
        // 设置背景是否模糊
        if (!params.isDimEnabled) {
            window.setDimAmount(0f);
        }
        // 设置文本
        int textSize = params.mTextArray.size();
        for (int i = 0; i < textSize; i++) {
            mViewHelper.setText(params.mTextArray.keyAt(i), params.mTextArray.valueAt(i));
        }
        // 设置点击
        int clickSize = params.mClickArray.size();
        for (int i = 0; i < clickSize; i++) {
            mViewHelper.setOnClickListener(params.mClickArray.keyAt(i), params.mClickArray.valueAt(i));
        }
        return this;
    }

    public int dp2px(float dp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, getContext().getResources().getDisplayMetrics());
    }

    public void setText(int viewId, CharSequence text) {
        mViewHelper.setText(viewId, text);
    }

    public void setOnClickListener(int viewId, View.OnClickListener onClickListener) {
        mViewHelper.setOnClickListener(viewId, onClickListener);
    }

    public <T extends View> T getView(int viewId) {
        return mViewHelper.getView(viewId);
    }
}
