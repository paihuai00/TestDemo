package com.mytestdemo.custom_dialog;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.StyleRes;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;

import com.mytestdemo.R;

import java.lang.ref.WeakReference;

/**
 * Created by cuishuxiang on 2017/10/12.
 *
 * 自定义 dialog
 */

public class CustomAlertDialog extends Dialog{
    private CustomAlertController mAlert;


    public CustomAlertDialog(@NonNull Context context, @StyleRes int themeResId) {
        super(context, themeResId);

        mAlert = new CustomAlertController(this,getWindow());
    }


    public static class  Builder{

        CustomAlertController.CustomAlertDialogParams P;

        public Builder(Context context) {
            //如果不传，style 就使用默认的style
            this(context, R.style.default_dialog_style);
        }
        public Builder(Context context, int themeResId) {
            P = new CustomAlertController.CustomAlertDialogParams(context,themeResId);
        }

        /**
         * 设置布局View
         * @param view
         * @return
         */
        public Builder setContentView(View view) {
            P.mView = view;
            P.mViewLayoutResId = 0;
            return this;
        }

        /**
         * 设置布局View id
         * @param layoutResId
         * @return
         */
        public Builder setContentView(int layoutResId) {
            P.mView = null;
            P.mViewLayoutResId = layoutResId;
            return this;
        }

        /**
         * 设置文本
         * @param viewId
         * @param charSequence
         * @return
         */
        public Builder setText(int viewId, CharSequence charSequence) {
            //可能会有多个文本，所以使用集合来存储
            P.mTextArray.put(viewId,charSequence);
            return this;
        }

        /**
         *设置点击事件
         * @param view
         * @return
         */
        public Builder setOnClickListener(int view, View.OnClickListener onClickListener) {
            //可能会有多个view 点击事件，所以使用集合来存储
            P.mClickListenerArray.put(view,onClickListener);
            return this;
        }

        public Builder setOnCancelListener(OnCancelListener onCancelListener) {
            P.mOnCancelListener = onCancelListener;
            return this;
        }

        public Builder setOnDismissListener(OnDismissListener onDismissListener) {
            P.mOnDismissListener = onDismissListener;
            return this;
        }

        public Builder setOnKeyListener(OnKeyListener onKeyListener) {
            P.mOnKeyListener = onKeyListener;
            return this;
        }

        //设置全屏
        public Builder fullWidth() {
            P.mWidth = ViewGroup.LayoutParams.MATCH_PARENT;
            return this;
        }



        /**
         * //底部弹出
         * @param isAnimator 是否有动画
         * @return
         */
        public Builder showFromBottom(boolean isAnimator) {
            if (isAnimator) {
                P.mAnimation = R.style.dialog_from_bottom;
            }
            P.mGravity = Gravity.BOTTOM;

            return this;
        }

        /**
         * 设置  宽 高
         *
         * @return
         */
        public Builder setWidthAndHeight(int width, int height) {
            P.mWidth = width;
            P.mHeight = height;
            return this;
        }

        /**
         * 添加动画
         * @return
         */
        public Builder addAnimation(int styleAnimation) {
            P.mAnimation = styleAnimation;
            return this;
        }

        public CustomAlertDialog create() {
            // We can't use Dialog's 3-arg constructor with the createThemeContextWrapper param,
            // so we always have to re-set the theme
            final CustomAlertDialog dialog = new CustomAlertDialog(P.mContext, P.mThemeResId);
            P.apply(dialog.mAlert);
            dialog.setCancelable(P.mCancelable);
            if (P.mCancelable) {
                dialog.setCanceledOnTouchOutside(true);
            }
            dialog.setOnCancelListener(P.mOnCancelListener);
            dialog.setOnDismissListener(P.mOnDismissListener);
            if (P.mOnKeyListener != null) {
                dialog.setOnKeyListener(P.mOnKeyListener);
            }
            return dialog;
        }

        public CustomAlertDialog show() {
            final CustomAlertDialog dialog = create();
            dialog.show();
            return dialog;
        }
    }

}
