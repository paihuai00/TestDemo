package com.mytestdemo.my_shadow_img;

import android.animation.Animator;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.mytestdemo.R;
import com.willowtreeapps.spruce.Spruce;
import com.willowtreeapps.spruce.animation.DefaultAnimations;
import com.willowtreeapps.spruce.sort.LinearSort;
import com.yinglan.shadowimageview.ShadowImageView;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * https://github.com/yingLanNull/ShadowImageView
 */
public class ShadowActivity extends AppCompatActivity {

    @BindView(R.id.shadow_img)
    ShadowImageView shadowImg;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shadow);
        ButterKnife.bind(this);


//        Animator spruceAnimator = new Spruce.SpruceBuilder(shadowImg)
//                .sortWith(new DefaultSort(5000))
//                .animateWith(DefaultAnimations.shrinkAnimator(shadowImg, 5000))
//                .start();

        Animator linear = new Spruce.SpruceBuilder(shadowImg)
                .sortWith(new LinearSort(5000,false, LinearSort.Direction.RIGHT_TO_LEFT))
//                .animateWith(DefaultAnimations.shrinkAnimator(shadowImg, 5000))
                .animateWith(DefaultAnimations.fadeInAnimator(shadowImg,5000))
                .start();


//        Animator spruceAnimator = new Spruce.SpruceBuilder(shadowImg)
//                .sortWith(new DefaultSort(5000))
//                .animateWith(DefaultAnimations.shrinkAnimator(shadowImg, 5000))
//                .start();
//
//        Animator spruceAnimator = new Spruce.SpruceBuilder(shadowImg)
//                .sortWith(new DefaultSort(5000))
//                .animateWith(DefaultAnimations.shrinkAnimator(shadowImg, 5000))
//                .start();
//
//
//        Animator spruceAnimator = new Spruce.SpruceBuilder(shadowImg)
//                .sortWith(new DefaultSort(5000))
//                .animateWith(DefaultAnimations.shrinkAnimator(shadowImg, 5000))
//                .start();
//
//
//        Animator spruceAnimator = new Spruce.SpruceBuilder(shadowImg)
//                .sortWith(new DefaultSort(5000))
//                .animateWith(DefaultAnimations.shrinkAnimator(shadowImg, 5000))
//                .start();
//
//
//        Animator spruceAnimator = new Spruce.SpruceBuilder(shadowImg)
//                .sortWith(new DefaultSort(5000))
//                .animateWith(DefaultAnimations.shrinkAnimator(shadowImg, 5000))
//                .start();



    }
}
