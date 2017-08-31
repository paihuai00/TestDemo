package com.mytestdemo.my_flowerview;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.TypeEvaluator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.PointF;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.mytestdemo.R;

import java.util.Random;

/**
 * Created by cuishuxiang on 2017/8/7.
 *
 * 实现一个点赞效果  点击左上角的button ，底部出现图片 缓慢移动的上面的动画效果
 *
 * 使用：属性动画， 三阶贝塞尔曲线
 */

public class FlowerBezierViewGroup extends RelativeLayout {
    private static final String TAG = "LikeBezierViewGroup";
    //底部出现的图片源
    private int[] imgRes = new int[]{R.drawable.icon_mine_msg, R.drawable.icon_mine_say,
            R.drawable.icon_mine_service, R.drawable.icon_mine_setting};

    //控件 宽 高
    private int mScreenWidth, mScreenHeight;

    //图片 宽 高
    private int mDrawableWidth, mDrawableHeight;

    //生产随机数
    private Random random=new Random();

    //产生的动画 集合
    private AnimatorSet animatorSet;

    private LayoutParams layoutParams;

    public FlowerBezierViewGroup(Context context) {
        this(context, null);
    }

    public FlowerBezierViewGroup(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public FlowerBezierViewGroup(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        initDrawable();

        initParams();

    }

    //初始化params
    private void initParams() {
        layoutParams = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        //设置底部，居中
        layoutParams.addRule(ALIGN_PARENT_BOTTOM);
        layoutParams.addRule(CENTER_HORIZONTAL);
    }

    //获得图片的宽高
    private void initDrawable() {
        //图片大小一样
        Drawable drawable = ContextCompat.getDrawable(getContext(), R.drawable.icon_mine_service);

        mDrawableWidth = drawable.getIntrinsicWidth();
        mDrawableHeight = drawable.getIntrinsicHeight();
        Log.d(TAG, "ImageView 的宽: " + mDrawableWidth + " 高： " + mDrawableHeight);
    }


    //初始化  动画集合
    private AnimatorSet getAnimatorSet(ImageView imageView) {

        AnimatorSet animatorSet = new AnimatorSet();

        ObjectAnimator scaleAnimator = ObjectAnimator.ofFloat(imageView, "scaleX", 0.3f, 1f);
        ObjectAnimator alphaAnimator = ObjectAnimator.ofFloat(imageView, "alpha", 0.3f, 1f);

        animatorSet.setDuration(5000);//持续时间，暂时设置5s

        animatorSet.playTogether(scaleAnimator, alphaAnimator);

        return animatorSet;
    }

    // 得到贝塞尔 曲线的动画，并动态设置 ImageView坐标
    private ValueAnimator getBezierAnimator(final ImageView imageView) {
        PointF pointF0 = new PointF(mScreenWidth / 2, mScreenHeight - mDrawableHeight / 2);
        //确保 P2点的 Y值 > P1点的 Y值
        PointF pointF1 = new PointF(random.nextInt(mScreenWidth), random.nextInt(mScreenHeight / 2));
        PointF pointF2 = new PointF(random.nextInt(mScreenWidth), random.nextInt(mScreenHeight));

        PointF pointF3 = new PointF(random.nextInt(mScreenWidth), 0);

        ValueAnimator valueAnimator = ValueAnimator.ofObject(new PointTypeEvaluator(pointF1, pointF2), pointF0, pointF3);
        valueAnimator.setDuration(3000);
        valueAnimator.setInterpolator(new AccelerateDecelerateInterpolator());
        valueAnimator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                //动画结束，移除当前ImageView
                Log.d(TAG, "onAnimationEnd: 动画完成，移除ImageView"+imageView.toString());
                removeView(imageView);
            }
        });
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                PointF pointF = (PointF) animation.getAnimatedValue();
                imageView.setX(pointF.x);
                imageView.setY(pointF.y);
                imageView.setAlpha(1 - animation.getAnimatedFraction() );
            }
        });

        return valueAnimator;
    }

    public void addImageView() {
        //动态添加 ImageView
        final ImageView imageView = new ImageView(getContext());
        imageView.setLayoutParams(layoutParams);
        imageView.setImageResource(imgRes[random.nextInt(imgRes.length - 1)]);

        addView(imageView);//将ImageView 添加到父控件

        getBezierAnimator(imageView).start();

//        animatorSet = new AnimatorSet();
////        animatorSet.playTogether(getBezierAnimator());
//        animatorSet.playSequentially( getBezierAnimator(imageView));
//        animatorSet.start();
    }

    //得到 当前view 的宽高
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        mScreenWidth = MeasureSpec.getSize(widthMeasureSpec);
        mScreenHeight = MeasureSpec.getSize(heightMeasureSpec);

    }

    /**
     * 自定义  一个插值器
     */
    class PointTypeEvaluator implements TypeEvaluator<PointF> {
        //两个控制点
        private PointF pointF1, pointF2;
        public PointTypeEvaluator(PointF pointF1, PointF pointF2) {
            this.pointF1 = pointF1;
            this.pointF2 = pointF2;
        }

        /**
         * 三阶：B(t) = P0*(1-t)^3 +3*P1*t*(1-t)^2+3*P2*t^2*(1-t)+P3*t^3 ,0<=t<=3
         * @param t  这里的t 相当于，贝塞尔曲线公式里面的t  0-->1
         * @param startValue  相当于贝塞尔曲线 P0
         * @param endValue    相当于贝塞尔曲线 P3
         * @return
         */
        @Override
        public PointF evaluate(float t, PointF startValue, PointF endValue) {
            PointF pointF = new PointF();
            Log.d(TAG, "evaluate: t= " + t);

            pointF.x = startValue.x * (1 - t) * (1 - t) * (1 - t)
                    + 3 * pointF1.x * t * (1 - t) * (1 - t)
                    + 3 * pointF2.x * t * t * (1 - t)
                    + endValue.x * t * t * t;

            pointF.y = startValue.y * (1 - t) * (1 - t) * (1 - t)
                    + 3 * pointF1.y * t * (1 - t) * (1 - t)
                    + 3 * pointF2.y * t * t * (1 - t)
                    + endValue.y * t * t * t;

            return pointF;
        }
    }


}
