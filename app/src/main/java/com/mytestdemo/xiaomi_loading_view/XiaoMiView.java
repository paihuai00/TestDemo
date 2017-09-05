package com.mytestdemo.xiaomi_loading_view;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.animation.LinearInterpolator;

/**
 * Created by cuishuxiang on 2017/9/1.
 */

public class XiaoMiView extends View {
    private static final String TAG = "XiaoMiView2";

    private Paint paint;
    private Path path;

    private PointF centerPoint;

    private Trangle[] trangles = new Trangle[4];
    private float edge = 200;

    //开启动画
    private ValueAnimator valueAnimator;

    private int[] trangleColors = new int[]{
            Color.parseColor("#BE8CD3"),
            Color.parseColor("#FAB130"),
            Color.parseColor("#65C7CA"),
            Color.parseColor("#EA767F")};

    private STATUS currentStatus = STATUS.SHOW_MID;
    private enum STATUS {
        //出现顺序 中 ---> 1-----> 2 ------>3
        SHOW_MID,SHOW_FIRST,SHOW_SECOND,SHOW_THRID,SHWO_COMPLET,

        //消失顺序 3---->1---->2---->中
        DISMISS_THRID,DISMISS_FIRST,DISMISS_SECOND,DISMISS_MID
    }


    public XiaoMiView(Context context) {
        this(context, null);
    }

    public XiaoMiView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public XiaoMiView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        init();
    }

    private void init() {
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setAntiAlias(true);
        paint.setDither(true);

        path = new Path();

        centerPoint = new PointF();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        for (int i = 0; i < trangles.length; i++) {
            path.reset();

            path.moveTo(trangles[i].startX, trangles[i].startY);
            path.lineTo(trangles[i].cur_1X, trangles[i].cur_1Y);
            path.lineTo(trangles[i].cur_2X, trangles[i].cur_2Y);

            path.close();

            paint.setColor(trangleColors[i]);

            canvas.drawPath(path, paint);

            if (currentStatus == STATUS.SHOW_MID) {
                break;
            }

        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        centerPoint.x = MeasureSpec.getSize(widthMeasureSpec) / 2;
        centerPoint.y = MeasureSpec.getSize(heightMeasureSpec) / 2;

        calcuteTrangle();

        startValueAnimator();
    }

    /**
     * 开启动画
     */
    private void startValueAnimator() {
        if (valueAnimator != null && valueAnimator.isRunning()) {
            valueAnimator.cancel();
        }
        valueAnimator = ValueAnimator.ofFloat(0, 1);
        valueAnimator.setRepeatCount(ValueAnimator.INFINITE);//设置无限循环
//        valueAnimator.setRepeatMode(ValueAnimator.RESTART);//每次执行的方案都是从头开始
        valueAnimator.setInterpolator(new LinearInterpolator());//每次线性变换
        valueAnimator.setDuration(500);
        valueAnimator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationRepeat(Animator animation) {
                //监听重复事件
                if (currentStatus == STATUS.SHOW_MID) {//出现的顺序是  middle -1-2-3
                    currentStatus = STATUS.SHOW_FIRST;
                } else if (currentStatus == STATUS.SHOW_FIRST) {
                    currentStatus = STATUS.SHOW_SECOND;
                } else if (currentStatus == STATUS.SHOW_SECOND) {
                    currentStatus = STATUS.SHOW_THRID;
                } else if (currentStatus == STATUS.SHOW_THRID) {
                    currentStatus = STATUS.SHWO_COMPLET;
                    reverseStartPoint();//反转起始点
                } else if (currentStatus == STATUS.SHWO_COMPLET) {//消失的顺序是，3 - 1 -2 -middle
                    currentStatus = STATUS.DISMISS_THRID;
                } else if (currentStatus == STATUS.DISMISS_THRID) {
                    currentStatus = STATUS.DISMISS_FIRST;
                } else if (currentStatus == STATUS.DISMISS_FIRST) {
                    currentStatus = STATUS.DISMISS_SECOND;
                } else if (currentStatus == STATUS.DISMISS_SECOND) {
                    currentStatus = STATUS.DISMISS_MID;
                } else if (currentStatus == STATUS.DISMISS_MID) {
                    currentStatus = STATUS.SHOW_MID;
                    reverseStartPoint();//反转
                }
            }
        });
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float fraction = animation.getAnimatedFraction();
                Log.d(TAG, "onAnimationUpdate: fraction=" + fraction + "\ncurrentStatus=" + currentStatus);
                Trangle trangle = trangles[0];
                if (currentStatus == STATUS.SHOW_MID||currentStatus==STATUS.SHOW_MID) {
                    trangle = trangles[0];
                } else if (currentStatus == STATUS.SHOW_FIRST||currentStatus==STATUS.DISMISS_FIRST) {
                    trangle = trangles[1];
                } else if (currentStatus == STATUS.SHOW_SECOND||currentStatus==STATUS.DISMISS_SECOND) {
                    trangle = trangles[2];
                } else if (currentStatus == STATUS.SHOW_THRID||currentStatus==STATUS.DISMISS_THRID) {
                    trangle = trangles[3];
                } else if (currentStatus == STATUS.SHWO_COMPLET) {
                    //如果是COMPLETE状态的话，此次动画效果保持不变
                    invalidate();
                    return;
                }
                //消失动画，需要反过来
                if (currentStatus == STATUS.DISMISS_MID || currentStatus == STATUS.DISMISS_FIRST ||
                        currentStatus == STATUS.DISMISS_SECOND || currentStatus == STATUS.DISMISS_THRID) {
                    fraction = 1 - fraction;
                }

                trangle.cur_1X = trangle.startX + fraction * (trangle.endX_1 - trangle.startX);
                trangle.cur_1Y = trangle.startY + fraction * (trangle.endY_1 - trangle.startY);
                trangle.cur_2X = trangle.startX + fraction * (trangle.endX_2 - trangle.startX);
                trangle.cur_2Y = trangle.startY + fraction * (trangle.endY_2 - trangle.startY);

                invalidate();
            }
        });
        valueAnimator.start();
    }

    //计算4个三角形，各个点坐标
    private void calcuteTrangle() {
        //计算中间三角形坐标
        //得到顶点到底边的 高的长度
        int trangleHeight = (int) Math.sqrt(edge * edge - edge / 2 * edge / 2);

        Trangle middleTrangle = new Trangle();
        middleTrangle.startX = centerPoint.x + trangleHeight / 2;
        middleTrangle.startY = centerPoint.y + edge / 2;
        middleTrangle.endX_1 = centerPoint.x - trangleHeight / 2;
        middleTrangle.endY_1 = centerPoint.y;
        middleTrangle.endX_2 = centerPoint.x + trangleHeight / 2;
        middleTrangle.endY_2 = centerPoint.y - edge / 2;

        middleTrangle.bgColor = trangleColors[0];
        trangles[0] = middleTrangle;

        //计算第一个三角形 (middle 上面)
        Trangle firstTrangle = new Trangle();
        firstTrangle.startX = centerPoint.x - trangleHeight / 2;
        firstTrangle.startY = centerPoint.y;
        firstTrangle.endX_1 = centerPoint.x - trangleHeight / 2;
        firstTrangle.endY_1 = centerPoint.y - edge;
        firstTrangle.endX_2 = centerPoint.x + trangleHeight / 2;
        firstTrangle.endY_2 = centerPoint.y - edge / 2;

        firstTrangle.bgColor = trangleColors[1];
        trangles[1] = firstTrangle;

        //计算第二个三角形 (右侧)
        Trangle secondTrangle = new Trangle();
        secondTrangle.startX = centerPoint.x + trangleHeight / 2;
        secondTrangle.startY = centerPoint.y - edge / 2;
        secondTrangle.endX_1 = centerPoint.x + trangleHeight * 3 / 2;
        secondTrangle.endY_1 = centerPoint.y;
        secondTrangle.endX_2 = centerPoint.x + trangleHeight / 2;
        secondTrangle.endY_2 = centerPoint.y + edge / 2;

        secondTrangle.bgColor = trangleColors[2];
        trangles[2] = secondTrangle;

        //计算第三个三角形  左下
        Trangle thridTrangle = new Trangle();
        thridTrangle.startX = centerPoint.x + trangleHeight / 2;
        thridTrangle.startY = centerPoint.y + edge / 2;
        thridTrangle.endX_1 = centerPoint.x - trangleHeight / 2;
        thridTrangle.endY_1 = centerPoint.y + edge;
        thridTrangle.endX_2 = centerPoint.x - trangleHeight / 2;
        thridTrangle.endY_2 = centerPoint.y;

        thridTrangle.bgColor = trangleColors[3];
        trangles[3] = thridTrangle;

    }

    /**
     * 出现动画完成，反转 初始点跟 end_2 这个点
     */
    private void reverseStartPoint() {
        for (int i = 0; i < trangles.length; i++) {
            float startX = trangles[i].startX;
            float startY = trangles[i].startY;

            trangles[i].startX = trangles[i].endX_2;
            trangles[i].startY = trangles[i].endY_2;

            trangles[i].endX_2 = startX;
            trangles[i].endY_2 = startY;

            trangles[i].cur_2X = startX;
            trangles[i].cur_2Y = startY;
        }
    }

    class Trangle {
        public float startX, startY;//初始点
        public float cur_1X, cur_1Y;//一条边上的点
        public float cur_2X, cur_2Y;//另一条边上的点
        public float endX_1, endY_1;
        public float endX_2, endY_2;

        public int bgColor;
    }

}
