package com.mytestdemo.qq_step_count_view;

import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.animation.LinearInterpolator;

import com.mytestdemo.R;

/**
 * Created by cuishuxiang on 2017/8/17.
 *  仿 QQ 计步器
 *
 *  添加自定义属性，通过控制属性，来控制 颜色，文字大小等
 */

public class QQStepCountView extends View {
    private static final String TAG = "QqCountView";

    //外面圆弧的画笔
    private Paint outPaint;
    //内圆弧 画笔
    private Paint innerPaint;
    //文字 画笔
    private Paint textPaint;
    private Paint.FontMetrics textMetrics;
    private float textOffset_Y;//Y方向上的偏移量

    //当前 转过的角度
    private float currentAngle ;

    private RectF rectF;

    private int outArcColor ;//默认颜色
    private int progressColor ;//进度条颜色
    private int mTextColor;//文字的颜色
    private int mTextSize = 30;
    private String showText = "0";//显示的文字

    private int paintStrokeWidth = 20;//画笔宽度

    //文字显示 最大的数字
    private int maxCount;

    private ValueAnimator valueAnimator;
    private long pauseTime;

    public QQStepCountView(Context context) {
        this(context,null);
    }

    public QQStepCountView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public QQStepCountView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        initAttrs(context, attrs);
    }

    /**
     * 初始化自定义属性
     *
     * @param context
     * @param attrs
     */
    private void initAttrs(Context context, AttributeSet attrs) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.QqStepCountView);
        outArcColor = typedArray.getColor(R.styleable.QqStepCountView_outArcColor, Color.BLUE);
        progressColor = typedArray.getColor(R.styleable.QqStepCountView_innerArcColor, Color.RED);

        paintStrokeWidth =typedArray.getInt(R.styleable.QqStepCountView_storkeWidth, 20);

        mTextColor = typedArray.getColor(R.styleable.QqStepCountView_arcTextColor, Color.BLACK);
        mTextSize = typedArray.getDimensionPixelSize(R.styleable.QqStepCountView_arcTextSize, 30);

        maxCount = typedArray.getInt(R.styleable.QqStepCountView_maxCount, 100);

        typedArray.recycle();

        init();
    }

    private void init() {
        //1，设置 外圆弧 画笔
        outPaint = new Paint();
        outPaint.setColor(outArcColor);//默认外圈的颜色
        outPaint.setDither(true);
        outPaint.setAntiAlias(true);
        outPaint.setStyle(Paint.Style.STROKE);
        outPaint.setStrokeWidth(paintStrokeWidth);
        outPaint.setStrokeCap(Paint.Cap.ROUND);//设置画笔图形的样式：圆形
        outPaint.setStrokeJoin(Paint.Join.ROUND);//设置画笔绘制图形的结合方式：平滑效果

        //2，设置 内圆弧 画笔
        innerPaint = new Paint(outPaint);
        innerPaint.setColor(progressColor);

        //3，设置  文字 画笔
        textPaint = new Paint();
        textPaint.setColor(mTextColor);
        textPaint.setTextSize(mTextSize);
        textPaint.setDither(true);
        textPaint.setAntiAlias(true);
        //重新计算一下，text 基线 Y 位置，使得文字居中
        textMetrics = textPaint.getFontMetrics();
        textOffset_Y = -textMetrics.top / 2 - textMetrics.bottom / 2;
        textPaint.setTextAlign(Paint.Align.CENTER);//设置X 居中

        initAnimator();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        Log.d(TAG, "onDraw: getWidth()=" + getWidth() + "  getHeight()=" + getHeight());
        //这里就是 添加一个 padding 的效果
        rectF = new RectF(getWidth()/8, getHeight()/8, getWidth()*7/8, getHeight()*7/8);

        //1，绘制 外圆弧
        canvas.drawArc(rectF, 135, 270, false, outPaint);

        //2，绘制 内圆弧
        canvas.drawArc(rectF, 135, currentAngle, false, innerPaint);

        //3，绘制中心的文字  需要重新计算，基线的位置，才可以使得文字居中
        showText = (int) (currentAngle / 270 * maxCount) + "";
        Log.d(TAG, "onDraw: showText=  "+showText);
        canvas.drawText(showText, getWidth() / 2, getHeight() / 2 + textOffset_Y, textPaint);

        //画一条 辅助线，一会删除
//        canvas.drawLine(0,getHeight()/2,getWidth(),getHeight()/2,textPaint);
    }

    /**
     * 将控件 设置为正方形
     * @param widthMeasureSpec
     * @param heightMeasureSpec
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int width = MeasureSpec.getSize(widthMeasureSpec);
        int height = MeasureSpec.getSize(heightMeasureSpec);
        //设置控件为 正方形，取 设置最小的边长
        Log.d(TAG, "onMeasure: width > height ? height : width  =" + (width > height ? height : width) +
                "\nheight > width ? width : height = " + (height > width ? width : height));
        setMeasuredDimension(width > height ? height : width, height > width ? width : height);
    }

    /**
     * 开始 动画
     */
    private void initAnimator() {
        valueAnimator = ValueAnimator.ofFloat(0, 270);
        valueAnimator.setInterpolator(new LinearInterpolator());
        valueAnimator.setDuration(6000);
        valueAnimator.setRepeatCount(0);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                currentAngle = (float) valueAnimator.getAnimatedValue();
                invalidate();

            }
        });
    }

    /**
     * 开启记步 动画
     */
    public void startStepCount() {
        if (valueAnimator == null) {
            initAnimator();
        }
        //如果是暂停状态，就设置当前的进度值
        if (valueAnimator.isPaused()) {
            valueAnimator.start();
            valueAnimator.setCurrentPlayTime(pauseTime);
        }else {
            valueAnimator.start();
        }


    }

    /**
     * 停止记步动画
     */
    public void stopStepCount() {
        if (valueAnimator != null && valueAnimator.isStarted()) {
            valueAnimator.cancel();
//            valueAnimator = null;
        }
    }

    /**
     * 暂停
     */
    public void pauseStepCount() {
        if (valueAnimator != null && valueAnimator.isRunning()) {
            valueAnimator.pause();
            pauseTime=valueAnimator.getCurrentPlayTime();
        }
    }
}
