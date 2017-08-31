package com.mytestdemo.loading_view;

import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.animation.LinearInterpolator;

import com.mytestdemo.R;

/**
 * Created by cuishuxiang on 2017/8/14.
 *
 * 仿 百度贴吧  loading view
 */

public class LoadingView extends View {
    private static final String TAG = "LoadingView";
    private int width;//view 宽高
    private int height;

    //波浪
    private Paint wavePaint;
    private Path wavePath;
    private int mOffset;//偏移量

    //文字
    private Paint textPaint;
    private String textString = "贴";

    private int color;

    public LoadingView(Context context) {
        this(context,null);
    }

    public LoadingView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public LoadingView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context,attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        /**
         * 自定义属性 动态设置 中间的文字，跟颜色
         */
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.LoadingView);

        color = typedArray.getColor(R.styleable.LoadingView_loading_color, Color.BLUE);
        textString = typedArray.getString(R.styleable.LoadingView_middle_text);

        typedArray.recycle();
        //绘制波浪
        wavePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        wavePaint.setAntiAlias(true);
        wavePaint.setDither(true);
        wavePaint.setColor(color);

        wavePath = new Path();

        textPaint = new Paint();
        textPaint.setDither(true);
        textPaint.setAntiAlias(true);
        textPaint.setTypeface(Typeface.DEFAULT_BOLD);
        textPaint.setStyle(Paint.Style.FILL);
        textPaint.setTextSize(px2dip(context, 130));

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        drawWaveQuad();

        drawText(canvas);
    }

    private void drawText(Canvas canvas) {
        textPaint.setColor(color);//先绘制 最底层的字体，
        drawCenterText(canvas, textPaint, textString);//写字到 中心位置

        textPaint.setColor(Color.WHITE);//将上面的文字，改成白色

        canvas.clipPath(wavePath);
        //圆形
        canvas.drawCircle(width / 2, height / 2, width / 2, wavePaint);
        drawCenterText(canvas, textPaint, textString);
    }

    /**
     * 将文字 绘制到 控件中心
     * @param canvas
     * @param textPaint
     * @param textString
     */
    private void drawCenterText(Canvas canvas, Paint textPaint, String textString) {
        RectF rectF = new RectF(0, 0, width, height);
        textPaint.setTextAlign(Paint.Align.CENTER);

        Paint.FontMetrics fontMetrics = textPaint.getFontMetrics();
        float top = fontMetrics.top;
        float bottom = fontMetrics.bottom;

        int centerY = (int) (rectF.centerY() - top / 2 - bottom / 2);

        canvas.drawText(textString, rectF.centerX(), centerY, textPaint);
    }

    //波浪动画
    private void startWaveAnimator() {
        ValueAnimator waveAnimator = ValueAnimator.ofInt(0, width);
        waveAnimator.setRepeatCount(ValueAnimator.INFINITE);//无线重复
        waveAnimator.setDuration(1300);
        waveAnimator.setInterpolator(new LinearInterpolator());
        waveAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                //得到偏移量，并且重绘
                mOffset = (int) animation.getAnimatedValue();
                invalidate();
            }
        });
        waveAnimator.start();
    }
    /**
     * 绘制 2阶贝塞尔曲线  的波浪
     * @param
     */
    private Path drawWaveQuad() {
        //每次重置
        wavePath.reset();
        wavePath.moveTo(-width + mOffset, height / 2);//将起始点移动

        //简化写法
        for (int i = 0; i < 2; i++) {
            wavePath.quadTo(-width * 3 / 4 + (width * i) + mOffset, height / 2 - 70, -width / 2 + (width * i) + mOffset, height / 2);
            wavePath.quadTo(-width / 4 + (width * i) + mOffset, height / 2 + 70, +(width * i) + mOffset, height / 2);
        }

        wavePath.lineTo(width, height);//将路径闭合
        wavePath.lineTo(0,height);

//        canvas.drawPath(wavePath,wavePaint);
        return wavePath ;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        width = MeasureSpec.getSize(widthMeasureSpec);
        height = MeasureSpec.getSize(heightMeasureSpec);
        Log.d(TAG, "onMeasure: height="+width+"  height="+height);
        startWaveAnimator();
        //不可以在初始化中开启动画，初始化时 宽高
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    /**
     * 根据手机的分辨率从 px(像素) 的单位 转成为 dp
     */
    private int px2dip(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }
}
