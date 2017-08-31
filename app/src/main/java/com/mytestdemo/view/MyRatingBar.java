package com.mytestdemo.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.mytestdemo.R;

/**
 * Created by cuishuxiang on 2017/4/24.
 */

public class MyRatingBar extends LinearLayout implements View.OnClickListener{
    private Drawable starDrawable;
    private Drawable unStarDrawable;
    private float width;
    private float height;
    private boolean clickable;
    private float imgPadding;

    private int starCount;
    private int starNum;

    public MyRatingBar(Context context, AttributeSet attrs) {
        super(context, attrs);

        setOrientation(LinearLayout.HORIZONTAL);
        setGravity(Gravity.CENTER);

        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.MyRatingBar);
        starDrawable = typedArray.getDrawable(R.styleable.MyRatingBar_star_img);
        unStarDrawable = typedArray.getDrawable(R.styleable.MyRatingBar_unstar_img);
        width = typedArray.getDimension(R.styleable.MyRatingBar_img_width, dip2px(context, 36));
        height = typedArray.getDimension(R.styleable.MyRatingBar_img_height, dip2px(context, 36));
        imgPadding = typedArray.getDimension(R.styleable.MyRatingBar_img_padding, dip2px(context, 5));
        clickable = typedArray.getBoolean(R.styleable.MyRatingBar_clickable, true);

        starCount = typedArray.getInt(R.styleable.MyRatingBar_star_count, 5);//默认5
        starNum = typedArray.getInt(R.styleable.MyRatingBar_star_number, 0);

        typedArray.recycle();

        /**
         * 在初始化中，调用下面2个函数
         */
        for (int i = 0; i < starCount; i++) {
            ImageView view = getImagView(context, width, height);
            //设置ImageView的下标
            view.setTag(i);
            addView(view);
            //可以点击评分
            if (clickable)
                view.setOnClickListener(this);
        }
        if (starNum != 0) {
            if (starNum <= starCount) {
                //填充图片
                fillingImage(starNum - 1);
            } else {
                throw new RuntimeException("star填充数量不能大于总数star_count!");
            }
        }
    }

    /**
     * 创建 默认的ImageView
     * @param context
     * @param width
     * @param height
     * @return
     */
    private ImageView getImagView(Context context, float width, float height) {
        ImageView imageView = new ImageView(context);
        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(Math.round(width), Math.round(height));
        imageView.setLayoutParams(params);
        imageView.setPadding(dip2px(context, imgPadding), 0, 0, 0);

        if (unStarDrawable == null) {
            Toast.makeText(context, "请先设置默认的图片资源", Toast.LENGTH_SHORT).show();
            throw new NullPointerException("请先设置默认的图片资源");
        }else {
            imageView.setImageDrawable(unStarDrawable);
        }
        return imageView;
    }

    /**
     * 填充图片
     *
     * @param i   点击图片的下标
     */
    private void fillingImage(int i) {
        //首先将所有的图片，都设置为默认图
        for (int j = 0; j < starCount; j++) {
            ImageView view = (ImageView) getChildAt(j);
            if (unStarDrawable == null) {
                throw new NullPointerException("请先设置默认的图片资源");
            }else {
                view.setImageDrawable(unStarDrawable);
            }
        }

        //填充选中的等级
        for (int j = 0; j <= i; j++) {
            ImageView view = (ImageView) getChildAt(j);

            if (starDrawable == null) {
                throw new NullPointerException("请先设置默认的图片资源");
            } else {
                view.setImageDrawable(starDrawable);
            }
        }

    }

    /**
     * 获得当前评分数
     *
     * @return 当前星级
     */
    public int getStar() {
        return starNum;
    }

    private int dip2px(Context context, float dpValue) {
        float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    /**
     * 图片的点击事件
     */
    @Override
    public void onClick(View view) {
        fillingImage((Integer) view.getTag());
    }
}
