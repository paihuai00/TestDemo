package com.mytestdemo.recyclerview_brvah;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.View;

import com.mytestdemo.R;
/**
 * Created by Administrator on 2017/9/20.
 *
 * 时间轴
 */

public class TimeLineItemDecoration2 extends RecyclerView.ItemDecoration {

    private static final String TAG = "TimeLineItemDecoration2";
    private int mLineWidth;
    private Paint mLinePaint;
    private Paint mBigGreenCirclePaint;
    private Paint mSmallCirclePaint;
    private Paint mSmallGreenCirclePaint;
    private float mOutWidth;
    private float mPadding;
    private Context mContext;
    private float mBigCircleRadius;

    public TimeLineItemDecoration2(Context context, int radius) {
        this.mContext = context;
        mPadding = dipToPx(10);
        //圆的直径加上10dp的padding
        mOutWidth = dipToPx(radius * 2) + mPadding;
        mBigCircleRadius = dipToPx(radius);
        mLineWidth = context.getResources().getDimensionPixelOffset(R.dimen.line_height);

        mLinePaint = new Paint();
        mLinePaint.setAntiAlias(true);
        mLinePaint.setDither(true);
        mLinePaint.setColor(ContextCompat.getColor(context, R.color.line_color));

        mBigGreenCirclePaint = new Paint();
        mBigGreenCirclePaint.setAntiAlias(true);
        mBigGreenCirclePaint.setDither(true);
        mBigGreenCirclePaint.setStyle(Paint.Style.FILL);
        mBigGreenCirclePaint.setColor(ContextCompat.getColor(context, R.color.transparent_green));

        mSmallGreenCirclePaint = new Paint();
        mSmallGreenCirclePaint.setAntiAlias(true);
        mSmallGreenCirclePaint.setDither(true);
        mSmallGreenCirclePaint.setStyle(Paint.Style.FILL);
        mSmallGreenCirclePaint.setColor(ContextCompat.getColor(context, R.color.green));

        mSmallCirclePaint = new Paint();
        mSmallCirclePaint.setAntiAlias(true);
        mSmallCirclePaint.setDither(true);
        mSmallCirclePaint.setStyle(Paint.Style.FILL);
        mSmallCirclePaint.setColor(ContextCompat.getColor(context, R.color.line_color));
    }

    @Override
    public void getItemOffsets(Rect outRect, View view,
                               RecyclerView parent,
                               RecyclerView.State state) {
        RecyclerView.Adapter adapter = parent.getAdapter();
        int position = parent.getChildAdapterPosition(view);
        if (position == 0) {
//            outRect.set(0, 0, 0, 0);
            outRect.set((int) mOutWidth, 0, 0, mLineWidth);
        } else {
            outRect.set((int) mOutWidth, 0, 0, mLineWidth);
        }
    }

    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        RecyclerView.Adapter adapter = parent.getAdapter();
        int childCount = parent.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View view = parent.getChildAt(i);
            int childPosition = parent.getChildAdapterPosition(view);
            if (childPosition != RecyclerView.NO_POSITION) {
                if (adapter.getItemCount() != 0) {
                    if (childPosition == 0) {
                        //画第一个
                        drawFirstTimeLine(c, parent, view);
                    } else if (childPosition == adapter.getItemCount() - 1) {
                        //画最后一个
                        drawLastTimeLine(c, parent, view);
                    } else {
                        //画其他
                        drawNormalTimeLine(c, parent, view);
                    }
                }
            }
        }
    }

    private void drawLastTimeLine(Canvas canvas, RecyclerView parent, View view) {
        float cx = mOutWidth - mBigCircleRadius;
        float cy = view.getTop() + parent.getPaddingTop() + mPadding + mBigCircleRadius;
        canvas.drawCircle(cx, cy, mBigCircleRadius / 2.0f, mSmallCirclePaint);

        float verticalStartX = cx;
        float verticalEndX = cx;
        float verticalStartY = view.getTop();
        float verticalEndY = verticalStartY + mPadding + mBigCircleRadius / 1.0f;
        canvas.drawLine(verticalStartX, verticalStartY, verticalEndX, verticalEndY, mLinePaint);
    }

    private void drawNormalTimeLine(Canvas canvas, RecyclerView parent, View view) {
        float cx = mOutWidth - mBigCircleRadius;
        float cy = view.getTop() + parent.getPaddingTop() + mPadding + mBigCircleRadius;
        canvas.drawCircle(cx, cy, mBigCircleRadius / 2.0f, mSmallCirclePaint);

        float verticalStartX = cx;
        float verticalEndX = cx;
        float verticalStartY = view.getTop();
        float verticalEndY = view.getBottom() + mLineWidth;
        canvas.drawLine(verticalStartX, verticalStartY, verticalEndX, verticalEndY, mLinePaint);

        float horizontalStartX = mOutWidth + mPadding;
        float horizontalEndX = view.getRight() - mPadding;
        float horizontalStartY = view.getBottom();
        float horizontalEndY = horizontalStartY;
        canvas.drawLine(horizontalStartX, horizontalStartY, horizontalEndX, horizontalEndY, mLinePaint);
    }

    private void drawFirstTimeLine(Canvas canvas, RecyclerView parent, View view) {
        float cx = mOutWidth - mBigCircleRadius;
        float cy = view.getTop() + parent.getPaddingTop() + mPadding + mBigCircleRadius;
        canvas.drawCircle(cx, cy, mBigCircleRadius, mBigGreenCirclePaint);
        canvas.drawCircle(cx, cy, mBigCircleRadius * 2.0f / 3.0f, mSmallGreenCirclePaint);

        float verticalStartX = cx;
        float verticalEndX = cx;
        float verticalStartY = cy + mBigCircleRadius;
        float verticalEndY = view.getBottom() + mLineWidth;
        canvas.drawLine(verticalStartX, verticalStartY, verticalEndX, verticalEndY, mLinePaint);

        float horizontalStartX = mOutWidth + mPadding;
        float horizontalEndX = view.getRight() - mPadding;
        float horizontalStartY = view.getBottom();
        float horizontalEndY = horizontalStartY;
        canvas.drawLine(horizontalStartX, horizontalStartY, horizontalEndX, horizontalEndY, mLinePaint);
    }

    private float dipToPx(int dip) {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dip, mContext.getResources().getDisplayMetrics());
    }
}
