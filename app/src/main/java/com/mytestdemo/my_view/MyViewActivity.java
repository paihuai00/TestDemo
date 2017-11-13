package com.mytestdemo.my_view;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.view.animation.ScaleAnimation;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.mytestdemo.R;
import com.mytestdemo.view.MyCounterView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by cuishuxiang on 2017/3/31.
 */

public class MyViewActivity extends AppCompatActivity {
    private final String TAG = MyViewActivity.class.getSimpleName();
    @BindView(R.id.spread_view)
    CheckBox spreadView;
    private TextView mTextView;
    private Button mLeftButton;
    private Button mRightButon;

    private MyCounterView my_countview;

    private ValueAnimator valueAnimator;
    private ScaleAnimation scaleAnimation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_layout);
        ButterKnife.bind(this);
        init();
    }

    private void init() {
        mTextView = (TextView) findViewById(R.id.textView);
        mLeftButton = (Button) findViewById(R.id.leftButton);
        mLeftButton.setOnClickListener(new ClickListenerImpl());
        mRightButon = (Button) findViewById(R.id.rightButton);
        mRightButon.setOnClickListener(new ClickListenerImpl());


        /**
         * 自己绘制了一个，点击 自增的view
         */
        my_countview = (MyCounterView) findViewById(R.id.my_countview);
        spreadView = (CheckBox) findViewById(R.id.spread_view);
        Log.d(TAG, "spreadView: " + spreadView.isChecked()+"  "+spreadView.getHeight());
    }

    @OnClick(R.id.spread_view)
    public void onViewClicked() {
        scaleAnimation = new ScaleAnimation(1f, 1.5f, 1f, 1.5f,
                spreadView.getWidth() / 2, spreadView.getHeight() / 2);
        scaleAnimation.setDuration(2000);

        Log.d(TAG, "onViewClicked: " + spreadView.isChecked()+"  "+spreadView.getHeight());
        if (spreadView.isChecked()){
            spreadView.startAnimation(scaleAnimation);
        }

    }

    /**
     * 示例说明:
     * 1 每次点击leftButton的时候
     * 1.1 调用scrollBy()让mTextView的内容(即文字)在原本偏移的基础上往左移30
     * 1.2 调用scrollBy()让mLeftButton的内容(即文字)在原本偏移的基础上也往左移30
     * 2 每次点击rightButton的时候
     * 2.1 调用scrollTo()让mTextView的内容(即文字)直接往右偏移30,而不管以前的基础(即 mScrollX和mScrollY)
     * 3 连续几次点击leftButton会看到mTextView的内容(即文字)每点一次都会往左移动30,
     * 然后再点击一次rightButton会看到mTextView的内容(即文字)直接一次性到了往右30的位置,而
     * 不是慢慢移动过去.
     * 这么操作
     * 1 很好的体现了这两个方法的区别.
     * 2 直观地看了scrollTo()方法的效用,它是不管以前的偏移量的.
     * 4 在该例中也可以看到调用这两个方法时,View的背景是没有移动.移动的是内容.
     */
    private class ClickListenerImpl implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.leftButton:
                    //让mTextView的内容往左移
                    mTextView.scrollBy(30, 0);
                    //让mLeftButton的内容也往左移
                    mLeftButton.scrollBy(20, 0);
                    break;
                case R.id.rightButton:
                    //让mTextView的内容往右移直接到-30的位置
                    mTextView.scrollTo(-30, 0);
                    break;
                default:
                    break;
            }
        }

    }

}
