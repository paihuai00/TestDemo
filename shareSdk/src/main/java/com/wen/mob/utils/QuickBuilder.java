package com.wen.mob.utils;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.util.DisplayMetrics;
import android.util.SparseArray;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;

import com.wen.mob.R;


/**
 * Dialog的Builder控制建造类
 */

public class QuickBuilder {
    private QuickDialog mDialog;
    boolean mCancelable = true;
    DialogInterface.OnCancelListener mOnCancelListener;
    DialogInterface.OnDismissListener mOnDismissListener;
    DialogInterface.OnKeyListener mOnKeyListener;
    /**
     * Dialog的View 与Dialog的布局ID 设置一个即可
     */
    View mView;
    /**
     * Dialog的View 与Dialog的布局ID 设置一个即可
     */
    int mViewLayoutResId;
    /**
     * 存放文本的集合
     */
    SparseArray<CharSequence> mTextArray = new SparseArray<>();
    /**
     * 存放点击事件的集合
     */
    SparseArray<View.OnClickListener> mClickArray = new SparseArray<>();
    /**
     * 宽,默认是屏幕的宽
     */
    int mWidth;
    int mHeight = ViewGroup.LayoutParams.WRAP_CONTENT;
    /**
     * 显示位置
     */
    int mGravity = Gravity.CENTER;
    /**
     * 显示动画
     */
    int mAnimation;
    /**
     * 宽，默认缩放
     */
    float mScale = 0.85f;
    /**
     * 背景圆角，默认5
     */
    int mBgRadius = 5;
    /**
     * 背景色，默认白色
     */
    int mBgColor = Color.parseColor("#ffffffff");
    /**
     * 是否模糊
     */
    boolean isDimEnabled = true;


    private QuickBuilder(Context context, int themeResId) {
        DisplayMetrics dm = context.getResources().getDisplayMetrics();
        mWidth = dm.widthPixels;
        this.mDialog = new QuickDialog(context, themeResId);
    }

    private QuickBuilder(Context context) {
        this(context, R.style.Theme_Dialog);
    }

    public static QuickBuilder create(Context context, int themeResId) {
        return new QuickBuilder(context, themeResId);
    }

    public static QuickBuilder create(Context context) {
        return new QuickBuilder(context);
    }

    public QuickBuilder setText(int viewId, CharSequence text) {
        mTextArray.put(viewId, text);
        return this;
    }

    public QuickBuilder setOnClickListener(int viewId, View.OnClickListener onClickListener) {
        mClickArray.put(viewId, onClickListener);
        return this;
    }

    /**
     * 设置显示的View
     */
    public QuickBuilder setContentView(int layoutResId) {
        mView = null;
        mViewLayoutResId = layoutResId;
        return this;
    }

    /**
     * 设置显示的布局
     */
    public QuickBuilder setContentView(View view) {
        mView = view;
        mViewLayoutResId = 0;
        return this;
    }

    /**
     * 设置ContentView背景的颜色：默认白色
     */
    public QuickBuilder setContentViewBgColor(int resId) {
        mBgColor = resId;
        return this;
    }

    /**
     * 设置ContentView背景的圆角：默认10dp
     *
     * @param radus 内部已转成dp
     * @return
     */
    public QuickBuilder setContentViewBgRadius(int radus) {
        mBgRadius = radus;
        return this;
    }

    /**
     * 设置背景是否模糊：默认是模糊的
     *
     * @param isDimEnabled
     * @return
     */
    public QuickBuilder setIsDimEnabled(boolean isDimEnabled) {
        this.isDimEnabled = isDimEnabled;
        return this;
    }

    /**
     * 设置宽度占满的比例
     */
    public QuickBuilder setWidthScale(float scale) {
        mScale = scale;
        return this;
    }

    public QuickBuilder fullWidth() {
        mScale = 1.0f;
        return this;
    }

    public QuickBuilder fromBottom(boolean isAnim) {
        if (isAnim) {
            mAnimation = R.style.Anim_Dialog_Bottom;
        }
        mGravity = Gravity.BOTTOM;
        return this;
    }

    public QuickBuilder setAnimation(int resId) {
        mAnimation = resId;
        return this;
    }


    public QuickBuilder setWidth(int width) {
        mWidth = width;
        return this;
    }

    public QuickBuilder setHeight(int height) {
        mHeight = height;
        return this;
    }

    /**
     * 设置点击空白是否消失
     */
    public QuickBuilder setCancelable(boolean cancelable) {
        mCancelable = cancelable;
        return this;
    }

    /**
     * 设置取消的监听
     */
    public QuickBuilder setOnCancelListener(DialogInterface.OnCancelListener onCancelListener) {
        mOnCancelListener = onCancelListener;
        return this;
    }

    /**
     * 设置Dialog消息的监听
     */
    public QuickBuilder setOnDismissListener(DialogInterface.OnDismissListener onDismissListener) {
        mOnDismissListener = onDismissListener;
        return this;
    }

    /**
     * 设置按键的监听
     */
    public QuickBuilder setOnKeyListener(DialogInterface.OnKeyListener onKeyListener) {
        mOnKeyListener = onKeyListener;
        return this;
    }

    public QuickDialog create() {
        mDialog.apply(this);
        return mDialog;
    }

    public QuickDialog show() {
        QuickDialog dialog = create();
        dialog.show();
        return dialog;
    }


}
