package com.mytestdemo.my_side_bar;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by cuishuxiang on 2017/8/21.
 *
 * 索引字母view
 */

public class SideBarView extends View{
    private static final String TAG = "SideBarView";

    //26个字母
    private String[] letterList = new String[]{"A","B","C","D","E","F","G","H",
            "I","G","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z","#"};

    private Paint normalPaint;//普通画笔
    private Paint pressPoint;//按下的画笔

    //用于计算基线
    private Paint.FontMetrics fontMetrics;
    private int dy;

    private float letterWidth;//字母宽度
    private float letterHeight;//每个字母平分的高度

    private int pressPosition;//当前点击字母的位置

    public SideBarView(Context context) {
        this(context,null);
    }

    public SideBarView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SideBarView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        normalPaint = new Paint();
        normalPaint.setColor(Color.BLUE);
        normalPaint.setAntiAlias(true);
        normalPaint.setDither(true);
        normalPaint.setTextAlign(Paint.Align.CENTER);
        normalPaint.setTextSize(30);

        pressPoint = new Paint();
        pressPoint.setColor(Color.RED);
        pressPoint.setAntiAlias(true);
        pressPoint.setDither(true);
        pressPoint.setTextAlign(Paint.Align.CENTER);
        pressPoint.setTextSize(30);

        fontMetrics = normalPaint.getFontMetrics();

        //计算 字母宽度
        letterWidth = normalPaint.measureText("A");
        Log.d(TAG, "init: 字母宽度 letterWidth= " + letterWidth);
    }

    @Override
    protected void onDraw(Canvas canvas) {

        for (int i = 0; i < letterList.length; i++) {
            dy = (int) ((fontMetrics.bottom - fontMetrics.top) / 2 - fontMetrics.bottom);

            if (i == pressPosition) {
                canvas.drawText(letterList[i], getWidth()/2, i * letterHeight + letterHeight / 2 + dy, pressPoint);
            }else {
                canvas.drawText(letterList[i], getWidth()/2, i * letterHeight + letterHeight / 2 + dy, normalPaint);
            }
        }

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
//                break;
            case MotionEvent.ACTION_MOVE:
                //根据按下的Y坐标，计算当前点击的那个 字母
                float pressY = event.getY();
                pressPosition = (int) (pressY / letterHeight);

                Log.d(TAG, "onTouchEvent: 当前按下字母的位置：pressPosition=" + pressPosition);

                if (pressPosition<0)
                    pressPosition = 0;

                if (pressPosition>letterList.length-1)
                    pressPosition = letterList.length - 1;

                if (letterTouchListener != null) {
                    letterTouchListener.letterTouchListener(letterList[pressPosition], true);
                }

                invalidate();
                break;
            case MotionEvent.ACTION_UP:
                if (letterTouchListener != null) {
                    letterTouchListener.letterTouchListener(letterList[pressPosition], false);
                }
                break;
        }

        return true;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int width = (int) (getPaddingLeft() + getPaddingRight() + letterWidth + 20);
        int height = MeasureSpec.getSize(heightMeasureSpec);
        Log.d(TAG, "onMeasure: width= " + width + "  height= " + height);

        letterHeight = height / letterList.length;
        Log.d(TAG, "onMeasure: 每个字母平分的高度为：letterHeight=" + letterHeight);

        setMeasuredDimension(width,height);
    }

    private LetterTouchListener letterTouchListener;

    public void setLetterTouchListener(LetterTouchListener letterTouchListener) {
        this.letterTouchListener = letterTouchListener;
    }

    public interface LetterTouchListener {
        void letterTouchListener(String letter, boolean isPress);
    }

}
