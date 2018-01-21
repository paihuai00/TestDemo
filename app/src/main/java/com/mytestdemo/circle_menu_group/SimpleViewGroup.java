package com.mytestdemo.circle_menu_group;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import com.zhy.autolayout.widget.MetroLayout;

/**
 * @Created by cuishuxiang
 * @date 2018/1/18.
 *
 * ViewGroup学习，在其内部 上下左右四个角，放置4个view
 */

public class SimpleViewGroup extends ViewGroup {
    private static final String TAG = "SimpleViewGroup";

    public SimpleViewGroup(Context context) {
        this(context,null);
    }

    public SimpleViewGroup(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public SimpleViewGroup(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public SimpleViewGroup(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    /**
     * 设置 childView 位置
     * @param changed
     * @param l left
     * @param t top
     * @param r right
     * @param b bottom
     */
    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        //得到子View的个数
        int cCount = getChildCount();
        int cWidth = 0;
        int cHeight = 0;
        MarginLayoutParams cParams = null;

        /**
         * 遍历所有 ChildView 根据宽高，以及 margin 设置位置
         */
        for (int i = 0; i < cCount; i++) {
            View cView = getChildAt(i);
            cWidth = cView.getMeasuredWidth();
            cHeight = cView.getMeasuredHeight();
            cParams = (MarginLayoutParams) cView.getLayoutParams();

            int cleft = 0, cTop = 0, cRight = 0, cBottom = 0;
            Log.d(TAG, "onLayout: ");
            switch (i) {
                case 0:
                    cleft = cParams.leftMargin;
                    cTop = cParams.topMargin;
                    break;
                case 1:
                    cleft = getWidth() - cWidth - cParams.leftMargin - cParams.rightMargin;
                    cTop = cParams.topMargin;
                    break;
                case 2:
                    cleft = cParams.leftMargin;
                    cTop = getHeight()-cHeight-cParams.bottomMargin;
                    break;
                case 3:
                    cleft = getWidth() - cWidth - cParams.leftMargin - cParams.rightMargin;
                    cTop = getHeight() - cHeight - cParams.bottomMargin;
                    break;
            }
            cRight = cleft + cWidth;
            cBottom = cTop + cHeight;

            Log.d(TAG, "onLayout: getWidth() = " + getWidth() + "getHeight()" + getHeight());

            cView.layout(cleft, cTop, cRight, cBottom);
        }


    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        /**
         * ViewGroup 上级容器为其推荐的宽高
         * 计算
         */
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int sizeWidth = MeasureSpec.getSize(widthMeasureSpec);
        int sizeHeight = MeasureSpec.getSize(heightMeasureSpec);

        // 计算出所有的childView的宽和高
        measureChildren(widthMeasureSpec, heightMeasureSpec);

        //---------------以下为 wrap_content处理----------------------------------
        /**
         * 记录如果是wrap_content是设置的宽和高
         */
        int width = 0;
        int height = 0;

        int childCounts = getChildCount();

        int cWidth = 0;
        int cHeight = 0;
        MarginLayoutParams cParams = null;

        //计算 左/右边 两个ChildView的高度,取2者最大值
        int leftHeight = 0;
        int rightHeight = 0;

        //计算 上/下  两个ChildView的宽度，取最大值
        int topWidth = 0;
        int bottomWidth = 0;

        for (int i = 0; i < childCounts; i++) {
            View view = getChildAt(i);
            cWidth = view.getMeasuredWidth();
            cHeight = view.getMeasuredHeight();
            cParams = (MarginLayoutParams) view.getLayoutParams();


            //上面两个childView
            if (i == 0 || i == 1) {
                topWidth += cWidth + cParams.leftMargin + cParams.rightMargin;
            }
            if (i == 2 || i == 3) {
                bottomWidth += cWidth + cParams.leftMargin + cParams.rightMargin;
            }


            if (i == 0 || i == 1) {
                leftHeight += cHeight + cParams.topMargin + cParams.bottomMargin;
            }
            if (i == 2 || i == 3) {
                rightHeight += cHeight + cParams.topMargin + cParams.bottomMargin;
            }
            width = Math.max(topWidth, bottomWidth);
            height = Math.max(leftHeight, rightHeight);




            /**
             * 如果wrap_content 则需要设置成 我们计算的值
             *
             * 否则：直接设置为父容器计算的值
             */
            int finalWidth = (widthMode == MeasureSpec.EXACTLY) ? sizeWidth : width;
            int finalHeight = (heightMode == MeasureSpec.EXACTLY) ? sizeHeight : height;
            Log.d(TAG, "onMeasure: 计算后的值：width = " + width + "  height = " + height + "\n finalWidth = " + finalWidth
                    + "  finalHeight = " + finalHeight);

            setMeasuredDimension(finalWidth, finalHeight);
        }
    }

    /**
     * 只需要ViewGroup能够支持margin即可，
     * 那么我们直接使用系统的MarginLayoutParams
     * @param attrs
     * @return
     */
    @Override
    public LayoutParams generateLayoutParams(AttributeSet attrs) {
        return new MarginLayoutParams(getContext(),attrs);
    }
}
