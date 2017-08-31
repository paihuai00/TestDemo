package com.mytestdemo.my_seekbar_bezier;

import android.animation.TypeEvaluator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;

/**
 * Created by cuishuxiang on 2017/8/2.
 *
 * 使用贝塞尔曲线
 * 模仿购物车，点击 + 一个红点，下落到购物车的view
 *
 * http://www.jianshu.com/p/f0d47b6c1e94
 */

public class RedCircleView extends View implements
        View.OnClickListener ,View.OnTouchListener{

    private static final String TAG = "RedCircleView";
    //开始点和结束点
    private int mStartXPoint;
    private int mStartYPoint;
    private int mEndXPoint;
    private int mEndYPoint;
    //控制点
//    private int mConXPoint;
//    private int mConYPoint;
    //移动点
    private int mMoveXPoint;
    private int mMoveYPoint;

    //路径和画笔
    private Path mPath;
    private Paint mPaint;

    //圆形半径，画笔
    private int mCircleRadius;
    private Paint mCirlcePaint;

    public RedCircleView(Context context) {
        super(context);
        init(context);
    }


    public RedCircleView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public RedCircleView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    /**
     * 进行初始化的一些操作
     */
    private void init(Context context) {

        //设置各点的位置
        mStartXPoint = 100;
        mStartYPoint = 100;
        mEndXPoint = 600;
        mEndYPoint = 600;
//        mConXPoint = 400;
//        mConYPoint = 0;
        mMoveXPoint = 100;
        mMoveYPoint = 100;

        //路径,画笔设置
        mPath = new Path();
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setColor(Color.BLUE);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(8);

        mCircleRadius = 20;
        mCirlcePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mCirlcePaint.setColor(Color.RED);
        mCirlcePaint.setStyle(Paint.Style.FILL);

        setOnClickListener(this);
        setOnTouchListener(this);
    }



    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mPath.reset();
        //贝塞尔曲线  辅助线
        mPath.moveTo(mStartXPoint, mStartYPoint);
        mPath.quadTo(600, 0, mEndXPoint, mEndYPoint);
        canvas.drawPath(mPath, mPaint);

        //画圆
        canvas.drawCircle(mStartXPoint, mStartYPoint, mCircleRadius, mCirlcePaint);
        canvas.drawCircle(mMoveXPoint, mMoveYPoint, mCircleRadius, mCirlcePaint);
        canvas.drawCircle(mEndXPoint, mEndYPoint, mCircleRadius, mCirlcePaint);

    }

    @Override
    public void onClick(View v) {
        Log.d(TAG, "onClick: ");
        ValueAnimator valueAnimator = ValueAnimator.ofObject(new CirclePointEvaluator(), new Point(mStartXPoint, mStartYPoint),
                new Point(mEndXPoint, mEndYPoint));
        valueAnimator.setDuration(1200);
        valueAnimator.setInterpolator(new AccelerateDecelerateInterpolator());
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                Point point = (Point) animation.getAnimatedValue();
                mMoveXPoint = point.x;
                mMoveYPoint = point.y;
                invalidate();
            }
        });
        valueAnimator.start();

    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                Log.d(TAG, "onTouchEvent:ACTION_DOWN " + super.onTouchEvent(event));
                break;

            case MotionEvent.ACTION_MOVE:
                Log.d(TAG, "onTouchEvent: ACTION_MOVE" + super.onTouchEvent(event));
                break;

            case MotionEvent.ACTION_UP:
                Log.d(TAG, "onTouchEvent: ACTION_UP" + super.onTouchEvent(event));
                break;
        }
        return super.onTouchEvent(event);
//        return true;
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                Log.d(TAG, "onTouch:ACTION_DOWN " );
                break;

            case MotionEvent.ACTION_MOVE:
                Log.d(TAG, "onTouch: ACTION_MOVE" );
                break;

            case MotionEvent.ACTION_UP:
                Log.d(TAG, "onTouch: ACTION_UP");
                break;
        }

        //返回false表示，事件没有被消耗，将向下传递
        return false;
    }

    /**
     * 计算贝塞尔曲线，path
     */
    private void getBeizerPath() {
        Path beizerPath = new Path();


    }


    /**
     * 自定义 坐标 插值器 Evaluator
     */
    public class CirclePointEvaluator implements TypeEvaluator {
        //控制点
        private int mConXPoint = 600;
        private int mConYPoint = 0;
        /**
         * @param t          当前动画进度
         * @param startValue 开始值
         * @param endValue   结束值
         * @return
         */
        @Override
        public Object evaluate(float t, Object startValue, Object endValue) {

            Point startPoint = (Point) startValue;
            Point endPoint = (Point) endValue;

            float temp = 1 - t;

            /**
             * 该贝塞尔曲线坐标，根据公式：
             * B(t)=(1-t)*(1-t)P0 + 2(1-t)*t*P1 + t*t*P2 ，0<=t<=1x
             */
            int x = (int) (temp * temp * startPoint.x + 2 * t * temp * mConXPoint + t * t * endPoint.x);
            int y = (int) (temp * temp * startPoint.y + 2 * t * temp * mConYPoint + t * t * endPoint.y);

            return new Point(x,y);
        }

    }
}
