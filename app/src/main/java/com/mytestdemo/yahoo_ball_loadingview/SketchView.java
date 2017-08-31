package com.mytestdemo.yahoo_ball_loadingview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by cuishuxiang on 2017/8/23.
 */

public class SketchView extends View{
    private static final String TAG = SketchView.class.getSimpleName();

    private static final int NONE = 0;//没有点
    private static final int ONE_PRESS = 1;//按下的第一个点
    private static final int TWO_PRESS = 2;//按下的第二个点

    //屏幕上点的数量
    private int screenPointNum = 0;
    //屏幕的宽 高
    private int width, height;

    //记录按下第二个点与第一个点距离
    private float oldDistance;

    private Paint textPaint;
    private Path textPath;
    private Rect textRect;//绘制文字的rect
    private int rectLength;// rect，矩形的长度
    private PointF textPoint = new PointF();//每次绘制的文字，都位于中心，需要用户手动调整
    private PointF downPoint = new PointF();//记录每次按下点的位置
    private boolean isInnerRect = false;//设置标志位，判断按下点是否在文字的矩形内
    private String stringText="测试Text";

    private float textSize = 50;
    private float changeSize;//缩放后的大小
    private Paint linePaint;

    private float currentAngle = 0;
    private float tempAngle = 0;
    private float tempAngleStart = 0;


    private HandleMode handleMode = HandleMode.TEXT;

    public enum HandleMode {
        TEXT
    }

    public SketchView(Context context) {
        this(context, null);
    }

    public SketchView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SketchView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        textPaint = new Paint();
        textPaint.setColor(Color.BLACK);
        textPaint.setTextSize(textSize);
        textPaint.setDither(true);
        textPaint.setAntiAlias(true);
        textPaint.setTextAlign(Paint.Align.CENTER);

        textPath = new Path();

        linePaint = new Paint();
        linePaint.setColor(Color.RED);
        linePaint.setDither(true);
        linePaint.setAntiAlias(true);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        //判断当前操作类型
        switch (handleMode) {
            case TEXT:
//                if (screenPointNum == NONE || screenPointNum == ONE_PRESS) {

                    canvas.rotate(currentAngle + tempAngle,textPoint.x,textPoint.y);

                    canvas.drawText(stringText, textPoint.x, textPoint.y, textPaint);
                    Log.d(TAG, "onDraw: stringText x=" + textPoint.x + "  textPoint.y" + textPoint.y);
                    textRect = getTextRect(stringText, textPaint);
                    int rectLength = textRect.right - textRect.left;//得到矩形的长度

                    textRect.left += textPoint.x - rectLength / 2;
                    textRect.right += textPoint.x - rectLength / 2;
                    textRect.bottom += textPoint.y;
                    textRect.top += textPoint.y;

                    textPaint.setStyle(Paint.Style.STROKE);

                    canvas.drawRect(textRect, textPaint);

//                }
//                textRect = getTextRect(stringText, textPaint);
//                rectLength = textRect.right - textRect.left;//得到矩形的长度
//
//                textRect.left += textPoint.x - rectLength / 2;
//                textRect.right += textPoint.x - rectLength / 2;
//                textRect.bottom += textPoint.y;
//                textRect.top += textPoint.y;
//
//                if (screenPointNum == TWO_PRESS) {
//                    canvas.drawTextOnPath(stringText, textPath, 0, 0, textPaint);
//                }
                break;
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int action = event.getAction();
        int actionMask = event.getActionMasked();//多点触控

        switch (action & actionMask) {
            case MotionEvent.ACTION_DOWN:
                screenPointNum = ONE_PRESS;
                downPoint.x = event.getX();
                downPoint.y = event.getY();
                isInnerRect = isContain((int)downPoint.x, (int)downPoint.y, textRect);

                break;
            case MotionEvent.ACTION_POINTER_DOWN://多点触控
                oldDistance = getTwoPointDis(event);
                if (oldDistance > 10f) {
                    screenPointNum = TWO_PRESS;
                }
                tempAngleStart = getAngle(event.getX(), event.getY(), event.getX(1), event.getY(1));
                break;

            case MotionEvent.ACTION_MOVE:
                if (screenPointNum == TWO_PRESS) {
                    //正在移动的点到初始点距离
                    float newDist = getTwoPointDis(event);

                    if (newDist > oldDistance) {
                        zoomExpend(newDist);
                    }

                    if (newDist < oldDistance) {
                        zoomIn(newDist);
                    }
                    rotateText(event);

                }else if (screenPointNum == ONE_PRESS) {
                    //单指触控
                    //首先判断 按下点是否在矩形内
                    if (isInnerRect) {
                        textPoint.x = event.getX();
                        textPoint.y = event.getY();
                        invalidate();
                    }
                }
                break;

            case MotionEvent.ACTION_UP:
                isInnerRect = false;//抬起后，置为false
                break;
            case MotionEvent.ACTION_POINTER_UP://多点触控，抬起时的处理
                textSize = changeSize;//抬起后，将改变后的大小 赋值给原大小
                screenPointNum = NONE;
                currentAngle = tempAngle+currentAngle;
                tempAngle = 0;
                break;
        }

        return true;
}

    //获得旋转角
    public float getAngle(MotionEvent Event)
    {
        double Delta_X=Event.getX(0)-Event.getX(1);
        double Delta_Y=Event.getY(0)-Event.getY(1);
        return (float)Math.atan2(Delta_X, Delta_Y);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        width = MeasureSpec.getSize(widthMeasureSpec);
        height = MeasureSpec.getSize(heightMeasureSpec);
        Log.d(TAG, "onMeasure: width=" + width + "  height=" + height);
        //将初始点定到屏幕中心
        textPoint.x = width / 2;
        textPoint.y = height / 2;

        textPath.moveTo(width / 2, height / 2);
        textPath.lineTo(width, height / 2);
    }

    /**
     * 动态  改变文字 和  文字大小
     * @param drawString
     */
    public void setDrawString(String drawString) {
        stringText = drawString;
        invalidate();
    }
    public void setDrawString(String drawString, int textSize) {
        stringText = drawString;
        this.textSize = textSize;
        invalidate();
    }

    /**
     * 旋转
     * @param event
     */
    private void rotateText(MotionEvent event) {

        tempAngle = getAngle(event.getX(),event.getY(),event.getX(1),event.getY(1)) - tempAngleStart;
        Log.e("----------",String.valueOf(tempAngle));
        invalidate();
    }

    /**
     * 收缩
     */
    private void zoomIn(float newDist) {
        Log.d(TAG, "zoomIn: 此时收缩oldDistance=" + oldDistance+"   newDist="+newDist);
        float multiple = oldDistance / newDist;
        Log.d(TAG, "zoomIn: " + multiple);
        changeSize = textSize / multiple;//记录一下改变后字体大小，抬起时赋值给原字体值
        textPaint.setTextSize(textSize / multiple);
        invalidate();
    }

    /**
     * 扩大
     */
    private void zoomExpend(float newDist) {
        Log.d(TAG, "zoomExpend: 扩大oldDistance=" + oldDistance+"   newDist="+newDist);
        float multiple = newDist/oldDistance ;
        Log.d(TAG, "zoomExpend: " + multiple);
        changeSize = textSize * multiple;
        textPaint.setTextSize(textSize * multiple);
        invalidate();
    }

    /**
     * 得到两个 触控点的距离
     * @param event
     * @return
     */
    private float getTwoPointDis(MotionEvent event) {
        //注意，这里容易报角标越界异常
        //当多指触控，抬起一个手指之后 getX(1) 就会异常
        float x = event.getX(0) - event.getX(1);
        float y = event.getY(0) - event.getY(1);

        return (float) Math.sqrt(x * x + y * y);
    }

    /**
     * 计算文字所在矩形
     */
    private Rect getTextRect(String s, Paint drawTextPaint) {
        Rect rect = new Rect();
        drawTextPaint.getTextBounds(s, 0, s.length(), rect);
        Log.d(TAG, "getTextRect: rect.left=" + rect.left + "   rect.right=" + rect.right +
                "\nrect.top=" + rect.top + "   rect.bottom=" + rect.bottom+"\nrect.width()="+rect.width()+" rect.height()="+rect.height());
        return rect;
    }

    /**
     * 判断单指操作的时候，按下的点是否在 文本的Rect 中
     */
    private boolean isContain(int downX, int downY, Rect textRect) {
        if (textRect == null) {
            Log.d(TAG, "isContain: textRect == null");
            return false;
        }
        return textRect.contains(downX, downY);
    }

//    var p1:Point = new Point(40,30);
//    var p2:Point = new Point(80,60);
//
//    var angle:Number = Math.atan2((p2.y-p1.y), (p2.x-p1.x)) //弧度  0.6435011087932844
//    var theta:Number = angle*(180/Math.PI);
    private float getAngle(float x1 ,float y1,float x2,float y2){
        return (float) (Math.atan2(y2 - y1, x2 - x1) * (180 / Math.PI));
    }
}
