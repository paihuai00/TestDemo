package com.mytestdemo.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by cuishuxiang on 2017/4/22.
 */

public class MyRecyclerItemDivier extends RecyclerView.ItemDecoration {

    //设置分隔条Drawable资源的id
    private static final int[] ATTRS = new int[]{
            android.R.attr.listDivider
    };

    //分隔条 Drawable对象
    private Drawable mDivier;

    public MyRecyclerItemDivier(Context context) {
        TypedArray typedArray = context.obtainStyledAttributes(ATTRS);
        mDivier = typedArray.getDrawable(0);

        typedArray.recycle();
    }


    @Override
    public void onDraw(Canvas c, RecyclerView parent) {
        int left = parent.getPaddingLeft();

        int right = parent.getWidth() - parent.getPaddingRight();

        int count = parent.getChildCount();

        for (int i=0;i<count;i++) {
            //获得当前列表项
            View child = parent.getChildAt(i);

            RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child.getLayoutParams();

            int top = child.getBottom() + params.bottomMargin;

            int bottom = top + mDivier.getIntrinsicHeight();

            //设置绘制范围
            mDivier.setBounds(left, top, right, bottom);

            //开始绘制
            mDivier.draw(c);
        }
//        final int left = parent.getPaddingLeft();
//        final int right = parent.getWidth() - parent.getPaddingRight();
//
//        final int childCount = parent.getChildCount();
//        for (int i = 0; i < childCount; i++) {
//            final View child = parent.getChildAt(i);
//            android.support.v7.widget.RecyclerView v = new android.support.v7.widget.RecyclerView(parent.getContext());
//            final RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child
//                    .getLayoutParams();
//            final int top = child.getBottom() + params.bottomMargin;
//            final int bottom = top + mDivier.getIntrinsicHeight();
//            mDivier.setBounds(left, top, right, bottom);
//            mDivier.draw(c);
//        }
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        outRect.set(0, 0, 0, mDivier.getIntrinsicHeight());
//        outRect.set(0, 0, mDivier.getIntrinsicWidth(), 0);
    }
}
