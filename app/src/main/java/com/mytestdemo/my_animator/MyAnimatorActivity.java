package com.mytestdemo.my_animator;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.BounceInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.mytestdemo.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by cuishuxiang on 2017/4/20.
 * <p>
 * 属性动画
 */

public class MyAnimatorActivity extends AppCompatActivity implements View.OnClickListener {

    @BindView(R.id.img_animtorSet)
    ImageView imgAnimtorSet;
    //ObjectAnimator
    private int[] res = {R.id.red, R.id.a, R.id.b, R.id.c, R.id.d};
    private List<ImageView> list = new ArrayList<>();
    private boolean flag = false;

    //ValueAnimator
    private Button btn;

    //平移
    private Button translation_btn;

    //XML组合动画
    private Button group_btn;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animator_layout);
        ButterKnife.bind(this);

        for (int i = 0; i < res.length; i++) {
            ImageView imageView = (ImageView) findViewById(res[i]);
            imageView.setOnClickListener(this);
            list.add(imageView);
        }

        initValueAnimator();


        normalAnimator();

        groupXmlAnimator();

        initAnimatorSet();

    }

    private void initAnimatorSet() {
        //组合动画
        ObjectAnimator animator = ObjectAnimator.ofInt(imgAnimtorSet, "backgroundColor", 0xFFFF0000, 0xFFFF00FF);
        ObjectAnimator animator1 = ObjectAnimator.ofFloat(imgAnimtorSet, "translationX", 0.0f, 200.0f, 0f);
        ObjectAnimator animator2 = ObjectAnimator.ofFloat(imgAnimtorSet, "scaleX", 1.0f, 2.0f);
        ObjectAnimator animator3 = ObjectAnimator.ofFloat(imgAnimtorSet, "rotationX", 0.0f, 90.0f, 0.0F);
        ObjectAnimator animator4 = ObjectAnimator.ofFloat(imgAnimtorSet, "alpha", 1.0f, 0.2f, 1.0F);

        //组合动画方式1
        AnimatorSet set = new AnimatorSet();
        ((set.play(animator).with(animator1).before(animator2)).before(animator3)).after(animator4);
        set.setDuration(5000);
        set.start();

        set.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
            }
        });
    }

    private void groupXmlAnimator() {
        group_btn = (Button) findViewById(R.id.group_btn);

        Animator animator = AnimatorInflater.loadAnimator(getBaseContext(), R.animator.my_animator);
        animator.setTarget(group_btn);
        animator.start();
    }

    /**
     * 视图动画
     */
    private void normalAnimator() {
        //平移动画  默认是以左上角为中心
        TranslateAnimation translateAnimation = new TranslateAnimation(0, 500, 0, 0);
        translateAnimation.setDuration(2000);
        translation_btn = (Button) findViewById(R.id.translation_btn);
//        translation_btn.setAnimation(translateAnimation);


        //透明度动画
        AlphaAnimation alphaAnimation = new AlphaAnimation(0, 1);
        alphaAnimation.setDuration(2000);
        alphaAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                Toast.makeText(getBaseContext(), "透明度动画结束！", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        translation_btn.setAnimation(alphaAnimation);
    }

    private void initValueAnimator() {
        btn = (Button) findViewById(R.id.btn);
        btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.red:
                if (!flag) {
                    startAnimtor();
                    flag = true;
                } else {
                    closeAnimtor();
                    flag = false;
                }
                break;

            case R.id.btn:
                //(数值发生器)实现计时器的动画效果
                ValueAnimator valueAnimator = ValueAnimator.ofInt(0, 100, 50, 80);
                valueAnimator.setDuration(5000);
                valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                    @Override
                    public void onAnimationUpdate(ValueAnimator valueAnimator) {
                        int i = (int) valueAnimator.getAnimatedValue();

                        btn.setText(i + "");
                    }
                });
                valueAnimator.start();

                //自定义发生器
//                ValueAnimator animator = ValueAnimator.ofObject(new TypeEvaluator<PointF>() {
//                    @Override
//                    public PointF evaluate(float v, PointF pointF, PointF t1) {
//                        return null;
//                    }
//                });

                break;
            default:
                break;
        }
    }

    private void closeAnimtor() {
        //关闭动画
        for (int i = 0; i < list.size(); i++) {
            ObjectAnimator o = ObjectAnimator.ofFloat(list.get(i), "translationY", i * 100, 0);
            o.setInterpolator(new BounceInterpolator());
            o.setDuration(500);
            o.setStartDelay(300);
            o.start();
        }

    }

    //开始动画
    private void startAnimtor() {
        for (int i = 0; i < list.size(); i++) {
            ObjectAnimator o = ObjectAnimator.ofFloat(list.get(i), "translationY", 0, i * 100);
            o.addListener(new Animator.AnimatorListener() {
                @Override
                public void onAnimationStart(Animator animator) {

                }

                @Override
                public void onAnimationEnd(Animator animator) {

                }

                @Override
                public void onAnimationCancel(Animator animator) {

                }

                @Override
                public void onAnimationRepeat(Animator animator) {

                }
            });
            o.setInterpolator(new BounceInterpolator());
            o.setDuration(500);
            o.setStartDelay(300);
            o.start();
        }

    }
}
