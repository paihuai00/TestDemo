package com.mytestdemo.city_loadview;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mytestdemo.R;

/**
 * Created by cuishuxiang on 2017/8/16.
 *
 * 仿 58同城 加载动画
 */

public class CityLoadView extends LinearLayout {
    private static final String TAG = "CityLoadView";

    private Context context;

    //三个控件
    private View viewLayout;
    private ShapeLoadView loading_img;
    private View shadow_img;
    private TextView loading_text;

    //动画持续时间
    private final int ANIMATOR_DURATION = 500;

    public CityLoadView(Context context) {
        this(context, null);
    }

    public CityLoadView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CityLoadView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        initLayout();
        init();
    }

    private void initLayout() {
        viewLayout = inflate(context, R.layout.view_58_loading, this);//等价于 上面
        loading_img = (ShapeLoadView) viewLayout.findViewById(R.id.animator_img);
        shadow_img =viewLayout.findViewById(R.id.shadow_img);
        loading_text = (TextView) viewLayout.findViewById(R.id.load_txt);

    }

    private void init() {
        startDownAnimator();

    }

    @Override
    protected void onDraw(Canvas canvas) {

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    /**
     * 旋转动画
     */
    private ObjectAnimator getRotationAnimator() {
        ObjectAnimator rotationAnimator = ObjectAnimator.ofFloat(loading_img, "rotation", 0, 180);
        rotationAnimator.setDuration(ANIMATOR_DURATION);
//        rotationAnimator.setRepeatCount(ValueAnimator.INFINITE);
        return rotationAnimator;
    }

    /**
     * 上抛动画
     */
    private void startUpAnimator() {
        ObjectAnimator loading_up_animator = ObjectAnimator.ofFloat(loading_img, "translationY", 100, 0);

        ObjectAnimator scale_shadow_animator = ObjectAnimator.ofFloat(shadow_img, "scaleX", 0.3f, 1f);

        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.setDuration(ANIMATOR_DURATION);
        animatorSet.setInterpolator(new DecelerateInterpolator());
        animatorSet.playTogether(loading_up_animator, scale_shadow_animator, getRotationAnimator());
        animatorSet.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                startDownAnimator();//动画结束，开启上抛动画
            }
        });
        animatorSet.start();
    }

    /**
     * 下落动画
     */
    private void startDownAnimator() {
        ObjectAnimator loading_up_animator = ObjectAnimator.ofFloat(loading_img, "translationY", 0, 100);

        ObjectAnimator scale_shadow_animator = ObjectAnimator.ofFloat(shadow_img, "scaleX", 1f, 0.3f);

        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.setDuration(ANIMATOR_DURATION);
        animatorSet.setInterpolator(new AccelerateInterpolator());
        animatorSet.playTogether(loading_up_animator, scale_shadow_animator, getRotationAnimator());
        animatorSet.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                startUpAnimator();//动画结束，开启上抛动画
                loading_img.changeShape();
            }
        });
        animatorSet.start();
    }

}
