package com.mytestdemo.view_digital_loading;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PointF;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;

import com.mytestdemo.R;

/**
 * Created by cuishuxiang on 2017/10/26.
 * <p>
 * 数字loadingView
 * <p>
 * 1,绘制  圆角矩形
 * 2,绘制  默认灰色的圆环
 */

public class DigitalLoadingView extends View {
    private static final String TAG = DigitalLoadingView.class.getSimpleName();

    private PointF centerPoint;//中心坐标

    private Paint whitePaint;//白色画笔

    private Paint defaultPaint;//默认灰色画笔
    private int defaultColor = Color.parseColor("#F3F3F3");//灰色
    private RectF defaultRectF;//默认圆弧外切矩形

    private Paint progressPaint;//进度条画笔
    private int mProgressColor;//进度条颜色
    private float progressNum = 0;//进度数值

    private int progressWidth = 10;//圆环宽度

    private Paint textPaint;
    private Paint.FontMetrics textMetrics;
    private float textOffset_Y;
    private int textColor;//中心文字的颜色
    private int textDefaultSize = 30;//默认文字size
    private String showText;

    private RectF rectF;//圆角矩形
    private float bgRectWidth = 200;//圆角矩形的宽度
    private float bgRectCorner = 10;//圆角矩形的角度

    public DigitalLoadingView(Context context) {
        this(context, null);
    }

    public DigitalLoadingView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public DigitalLoadingView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        initAttrs(context, attrs);

        initView();
    }

    /**
     * 初始化 属性
     */
    private void initView() {
        centerPoint = new PointF();
        if (whitePaint == null) {
            whitePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
            whitePaint.setColor(Color.WHITE);
            whitePaint.setAntiAlias(true);
            whitePaint.setDither(true);
        }

        if (defaultPaint == null) {
            defaultPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
            defaultPaint.setStrokeWidth(progressWidth);
            defaultPaint.setColor(defaultColor);
            defaultPaint.setStyle(Paint.Style.STROKE);
        }

        if (progressPaint == null) {
            progressPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
            progressPaint.setStrokeWidth(progressWidth);
            progressPaint.setColor(mProgressColor);
            progressPaint.setStyle(Paint.Style.STROKE);
            progressPaint.setStrokeCap(Paint.Cap.ROUND);
            progressPaint.setStrokeJoin(Paint.Join.ROUND);//设置画笔绘制图形的结合方式：平滑效果
        }

        if (textPaint == null) {
            textPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
            textPaint.setTextSize(textDefaultSize);
            textPaint.setColor(textColor);
            textPaint.setStrokeCap(Paint.Cap.ROUND);
            textPaint.setTextAlign(Paint.Align.CENTER);
            textPaint.setStrokeJoin(Paint.Join.ROUND);//设置画笔绘制图形的结合方式：平滑效果
            textMetrics = textPaint.getFontMetrics();
            //基线在 Y 轴的偏移量(重绘基线)
            textOffset_Y = -textMetrics.top / 2 - textMetrics.bottom / 2;
        }

    }

    /**
     * 初始化自定义属性
     *
     * @param context
     * @param attrs
     */
    private void initAttrs(Context context, AttributeSet attrs) {
        TypedArray typedArray = context.obtainStyledAttributes(R.styleable.DigitalLoadingView);

        //设置进度条默认颜色
        mProgressColor = typedArray.getColor(R.styleable.DigitalLoadingView_progress_color,
                ContextCompat.getColor(context, R.color.colorAccent));

        textColor=typedArray.getColor(R.styleable.DigitalLoadingView_progress_color,
                Color.GRAY);//默认灰色

        typedArray.recycle();

    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        drawRoundRect(canvas);

        drawDefaultArc(canvas);

        drawProgressArc(canvas);

        drawCenterText(canvas);

    }

    /**
     * 绘制圆心中间的文字
     * @param canvas
     */
    private void drawCenterText(Canvas canvas) {
        showText = (int) (progressNum * 100 / 360) + "%";//转换成100
        canvas.drawText(showText, centerPoint.x, centerPoint.y + textOffset_Y, textPaint);
    }

    /**
     * 3 - 绘制进度条  圆环
     * @param canvas
     */
    private void drawProgressArc(Canvas canvas) {
        canvas.drawArc(defaultRectF, -90, progressNum, false, progressPaint);
    }

    /**
     * 2 - 绘制默认 圆环
     * @param canvas
     */
    private void drawDefaultArc(Canvas canvas) {
        //3点钟方向为开始方向，扫过360度
        canvas.drawArc(defaultRectF, -90, 360, false, defaultPaint);
    }

    /**
     *  1 - 绘制 背景圆环
     * @param canvas
     */
    private void drawRoundRect(Canvas canvas) {
        canvas.drawRoundRect(rectF, dp2px(getContext(),bgRectCorner), dp2px(getContext(),bgRectCorner), whitePaint);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        float width = MeasureSpec.getSize(widthMeasureSpec);
        float height = MeasureSpec.getSize(heightMeasureSpec);

        centerPoint.x = width / 2;
        centerPoint.y = height / 2;

        rectF = new RectF(centerPoint.x - bgRectWidth / 2, centerPoint.y - bgRectWidth / 2,
                centerPoint.x + bgRectWidth / 2, centerPoint.y + bgRectWidth / 2);

        defaultRectF = new RectF(centerPoint.x - bgRectWidth / 3, centerPoint.y - bgRectWidth / 3,
                centerPoint.x + bgRectWidth / 3, centerPoint.y + bgRectWidth / 3);
    }

    /**
     * 外部更新进度的方法，0 -- 100
     */
    public void setProgressNum(float num) {
        if (num < 0) {
            progressNum = 0;
            return;
        }
        if (num > 100) {
            progressNum = 100;
            return;
        }

        //转换成  360°
        progressNum = num / 100 * 360;

        Log.d(TAG, "setProgressNum: " + progressNum);

        //刷新界面(postInvalidate 可以在子线程更新ui)
        postInvalidate();
    }


    public static float dp2px(Context context, float dp) {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp,
                context.getResources().getDisplayMetrics());
    }
}
