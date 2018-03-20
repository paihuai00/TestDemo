package com.mytestdemo.dynamic_add_view;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.mytestdemo.BaseActivity;
import com.mytestdemo.DisplayUtil;
import com.mytestdemo.R;
import com.zhy.autolayout.AutoRelativeLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @Created by cuishuxiang
 * @date 2018/1/24.
 */

public class DynamicViewActivity extends BaseActivity {
    private static final String TAG = "DynamicViewActivity";
    @BindView(R.id.add_img)
    Button mAddImg;
    @BindView(R.id.add_txt)
    Button mAddTxt;
    @BindView(R.id.rootView)
    AutoRelativeLayout mRootView;
    @BindView(R.id.xml_tv)
    TextView mXmlTv;
    @BindView(R.id.add_edit)
    Button mAddEdit;
    @BindView(R.id.my_progress)
    ProgressBar mMyProgress;

    private int screenWidth;
    private int screenHeight;

    //-------------------图片参数 %-------------------------------
    private int leftPercent = 20;
    private int topPercent = 20;
    private int widthPercent = 20;
    private int heightPercet = 10;

    private int rotateView = 0;//旋转角度为 0

    //图片实际位置
    private float left, top, viewWidth, viewHeight;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dynamic_view);
        ButterKnife.bind(this);

        initView();

        mMyProgress.setProgress(10);
    }

    private void initView() {
        //获取屏幕参数
        screenWidth = DisplayUtil.getScreenWidth(this);
        screenHeight = DisplayUtil.getScreenHeight(this);
        Log.d(TAG, "initView:单位px screenWidth = " + screenWidth + "  screenHeight = " + screenHeight);

        float[] viewParams = getViewRealCoordinate(leftPercent, topPercent, widthPercent, heightPercet, screenWidth, screenHeight);

        left = viewParams[0];
        top = viewParams[1];
        viewWidth = viewParams[2];
        viewHeight = viewParams[3];
        Log.d(TAG, "initView: 得到view实际的位置 left = " + left + "   top = " + top + "  viewWidth = " + viewWidth + "  viewHeight = " + viewHeight);
    }

    @OnClick({R.id.add_img, R.id.add_txt, R.id.add_edit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.add_img:
                ImageView imageView = DynamicViewUtils.getImageView(getBaseContext(), (int) viewWidth, (int) viewHeight, (int) left, (int) top, 0);

                imageView.setBackgroundColor(Color.BLACK);
                mRootView.addView(imageView);

                break;
            case R.id.add_txt:

                TextView textView = DynamicViewUtils.getTextView(getBaseContext(), 300, 300, "这是内容！", 35, "#000000", 0);


                textView.setBackgroundColor(Color.RED);
                textView.setRotation(30);
                mRootView.addView(textView);

                Log.d(TAG, "onViewClicked: px2dp(this, 35) = " + px2dp(this, 35));
                Toast.makeText(this, "动态添加的TextView size =  " + textView.getTextSize() + " xml里面的size = " + mXmlTv.getTextSize(), Toast.LENGTH_SHORT).show();
                break;

            case R.id.add_edit:
//                EditText editText = DynamicViewUtils.getEditView(getBaseContext(),
//                        300,
//                        300,
//                        500,
//                        200,
//                        35,
//                        "#fff",
//                        Gravity.CENTER,
//                        0);
//                editText.setBackgroundColor(Color.RED);

                EditText editText = new EditText(this);
//                editText.setMaxHeight(300);
                editText.setTextSize(50);
//                editText.setBackground(null);
//                editText.setWidth(500);
                editText.setText("ces");
                editText.setBackground(null);
                editText.setBackgroundColor(Color.GRAY);
                editText.setPadding(0, 0, 0, 0);


                RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                layoutParams.setMargins(0, 500, 0, 0);
                editText.setLayoutParams(layoutParams);

                mRootView.addView(editText);
                break;
        }
    }

    public ImageView getImageView(Context context, int width, int height, int marginLeft, int marginTop) {
        ImageView imageView = new ImageView(context);
        imageView.setImageResource(R.mipmap.ic_launcher);
        imageView.setRotation(rotateView);
        //设置参数
        ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutParams.height = height;
        layoutParams.width = width;
        //设置margin
        ViewGroup.MarginLayoutParams marginLayoutParams = new ViewGroup.MarginLayoutParams(layoutParams);
        marginLayoutParams.setMargins(marginLeft, marginTop, 0, 0);

        imageView.setLayoutParams(marginLayoutParams);

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getBaseContext(), "点击了图片", Toast.LENGTH_SHORT).show();
            }
        });

        return imageView;
    }

    /**
     * 内容，默认居中
     *
     * @param context
     * @param marginLeft    左边距
     * @param marginTop     顶边距
     * @param contentString 内容
     * @param stringSize    文字size
     * @return
     */
    public TextView getTextView(Context context, int marginLeft, int marginTop, String contentString, int stringSize) {
        TextView textView = new TextView(context);
        //设置位置参数
        ViewGroup.MarginLayoutParams marginLayoutParams = new ViewGroup.MarginLayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        marginLayoutParams.setMargins(marginLeft, marginTop, 0, 0);
//        marginLayoutParams.width = width;
//        marginLayoutParams.height = height;
        textView.setLayoutParams(marginLayoutParams);

        textView.setTextSize(TypedValue.COMPLEX_UNIT_PX, stringSize);
        textView.setText(contentString);
        textView.setGravity(Gravity.CENTER);

        return textView;
    }

    /**
     * 得到View 在屏幕中的位置  单位：px
     * float[0] : left边距
     * float[1] : top边距
     * float[2] : view 的宽度
     * float[3] : view 的高度
     *
     * @param leftPercent   left距离 占整个屏幕宽的百分比 0~100%
     * @param topPercent    top距离 占整个屏幕高的百分比 0~100%
     * @param widthPercent  view宽度 ，占整个屏幕宽的百分比 0~100%
     * @param heightPercent view高度 ，占整个屏幕高的百分比 0~100%
     * @param screenWidth   手机屏幕宽度
     * @param screenHeight  手机屏幕高度
     * @return
     */
    public float[] getViewRealCoordinate(int leftPercent, int topPercent, int widthPercent, int heightPercent, int screenWidth, int screenHeight) {
        float[] viewCoordinate = new float[4];
        //限制 百分比0~100
        if (leftPercent < 0) leftPercent = 0;
        if (leftPercent > 100) leftPercent = 100;
        if (topPercent < 0) topPercent = 0;
        if (topPercent > 100) topPercent = 100;
        if (widthPercent < 0) widthPercent = 0;
        if (widthPercent > 100) widthPercent = 100;
        if (heightPercent < 0) heightPercent = 0;
        if (heightPercent > 100) heightPercent = 100;

        Log.d(TAG, "getViewRealCoordinate:百分比 leftPercent =  " + leftPercent + "  " +
                "topPercent = " + topPercent + "  widthPercent = "
                + widthPercent + "  heightPercent = " + heightPercent + " 屏幕 screenWidth 为：" + screenWidth + " 屏幕 screenHeight 为：" + screenHeight);

        //左边距
        viewCoordinate[0] = screenWidth * leftPercent / 100;
        //顶部距离
        viewCoordinate[1] = screenHeight * topPercent / 100;
        //view 宽度
        viewCoordinate[2] = screenWidth * widthPercent / 100;
        //view 高度
        viewCoordinate[3] = screenHeight * heightPercent / 100;

        Log.d(TAG, "getViewRealCoordinate: 左边距 = " + viewCoordinate[0] + " 顶部距离 = "
                + viewCoordinate[1] + " view 宽度 = " + viewCoordinate[2] + " view 高度 = " + viewCoordinate[3]);


        return viewCoordinate;
    }


    public static int dp2px(Context context, int dp) {

        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, context.getResources().getDisplayMetrics());
    }

    public static int px2dp(Context context, int px) {

        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_PX, px, context.getResources().getDisplayMetrics());
    }

    public static int sp2px(Context context, int sp) {

        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, sp, context.getResources().getDisplayMetrics());
    }

    //转换px为dip

    public static int convertPxOrDip(Context context, int px) {

        float scale = context.getResources().getDisplayMetrics().density;

        return (int) (px / scale + 0.5f * (px >= 0 ? 1 : -1));

    }
}
