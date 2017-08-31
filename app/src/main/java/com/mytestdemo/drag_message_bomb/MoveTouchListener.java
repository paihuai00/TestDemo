package com.mytestdemo.drag_message_bomb;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.PixelFormat;
import android.graphics.PointF;
import android.graphics.drawable.AnimationDrawable;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.mytestdemo.R;

/**
 * Created by cuishuxiang on 2017/8/9.
 */

public class MoveTouchListener implements View.OnTouchListener, MessageBubbleBombView.MessageBubbleListener {
    private static final String TAG = "MoveTouchListener";
    private MessageBubbleBombView messageBubbleBombView;

    private Context context;

    private View dragView;//实现 拖拽的view

    private WindowManager windowManager;
    private WindowManager.LayoutParams params;

    //添加一个 爆炸的动画效果（添加一个ImageView 并设置帧动画）
    private FrameLayout bombFrameLayout;
    private ImageView bombImageView;
    private DismissCallBack disappearCallBack;//爆炸效果完成， 添加回调


    public MoveTouchListener(View view, Context context, DismissCallBack disappearCallBack) {
        messageBubbleBombView = new MessageBubbleBombView(context);
        messageBubbleBombView.setMessageBubbleListener(this);

        windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        params = new WindowManager.LayoutParams();
        params.format = PixelFormat.TRANSPARENT;


        dragView = view;
        this.context = context;
        bombFrameLayout = new FrameLayout(context);
        bombImageView = new ImageView(context);
        bombImageView.setLayoutParams(new FrameLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT
        ));
        bombFrameLayout.addView(bombImageView);
        this.disappearCallBack = disappearCallBack;
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        int action = event.getAction();

        switch (action) {
            case MotionEvent.ACTION_DOWN:
                //在窗口上 添加一个View
                windowManager.addView(messageBubbleBombView, params);

                //初始化贝塞尔View 的点，保证固定圆的中心在View的中心
                int[] location = new int[2];
                dragView.getLocationOnScreen(location);//这里得到的是，View 在整个屏幕的位置
                Log.d(TAG, "View的 location : View的Left 的X值：" + location[0] + " View的Top 的Y值：" + location[1]);

                messageBubbleBombView.initPoint(location[0] + dragView.getWidth() / 2,
                        location[1] + dragView.getHeight() / 2 - BubbleUtils.getStatusBarHeight(context));

                //将传入的view 转换成bitmap，绘制到window上
                Bitmap viewBitmap = getBitmapByView(dragView);
                messageBubbleBombView.setDragBitmap(viewBitmap);

                //需要将之前的View 隐藏，
                dragView.setVisibility(View.INVISIBLE);

                break;

            case MotionEvent.ACTION_MOVE:
                messageBubbleBombView.updateDragPoint(event.getRawX(), event.getRawY() - BubbleUtils.getStatusBarHeight(context));
                break;

            case MotionEvent.ACTION_UP:
                messageBubbleBombView.handleActionUp();
                break;
        }

        return true;
    }

    /**
     * 从一个View中获取Bitmap
     *
     * @param view
     * @return Bitmap
     */
    private Bitmap getBitmapByView(View view) {
        if (view == null) {
            return null;
        }
        view.buildDrawingCache();
        Bitmap bitmap = view.getDrawingCache();
        return bitmap;
    }

    @Override
    public void disappear(PointF lastPoint) {
        //执行一个，消失的动画(帧动画)
        Log.d(TAG, "disappear: ");
        windowManager.removeView(messageBubbleBombView);

        windowManager.addView(bombFrameLayout, params);
        bombImageView.setBackgroundResource(R.drawable.anim_bubble_pop);

        AnimationDrawable animDrawable = (AnimationDrawable) bombImageView.getBackground();
        //为消失动画，设置显示的位置
        bombImageView.setX(lastPoint.x - animDrawable.getIntrinsicWidth() / 2);
        bombImageView.setY(lastPoint.y - animDrawable.getIntrinsicHeight() / 2);
        //开始执行动画
        animDrawable.start();

        //动画执行完成，移除这个View
        bombImageView.postDelayed(new Runnable() {
            @Override
            public void run() {
                windowManager.removeView(bombFrameLayout);

                //接口回调，通知外面，View消失
                if (disappearCallBack != null) {
                    disappearCallBack.dismiss();
                }
            }
        }, getAnimationDrawableTime(animDrawable));
    }

    /**
     * 通过动画的帧数，得到持续的时间
     * @param animDrawable
     * @return
     */
    private long getAnimationDrawableTime(AnimationDrawable animDrawable) {
        int numbers = animDrawable.getNumberOfFrames();//得到动画的帧数
        long time = 0;

        for (int i=0;i<numbers;i++) {
            time += animDrawable.getDuration(i);//得到一帧 持续时间
        }

        return time;
    }

    /**
     * 如果 距离较小，就回弹
     */
    @Override
    public void restore() {
        Log.d(TAG, "restore:回弹效果 ");
        windowManager.removeView(messageBubbleBombView);
        dragView.setVisibility(View.VISIBLE);//将原来的显示
    }

    /**
     * view 消失的回调
     * 用于通知  外部，view消失
     */
    interface DismissCallBack {
        void dismiss();
    }

}
