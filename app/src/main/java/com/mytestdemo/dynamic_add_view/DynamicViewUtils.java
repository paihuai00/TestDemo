package com.mytestdemo.dynamic_add_view;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Point;
import android.os.Build;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.mytestdemo.R;

/**
 * @Created by cuishuxiang
 * @date 2018/1/24.
 *
 * 获取 TextView  ImageView  ，
 * 具体位置由当前View占屏幕百分比确定
 */

public class DynamicViewUtils {
    private static final String TAG = "DynamicViewUtils";

    public static ImageView getImageView(final Context context, int width, int height, int marginLeft, int marginTop,int rotateAngle) {
        ImageView imageView = new ImageView(context);
        imageView.setImageResource(R.mipmap.ic_launcher);
        imageView.setRotation(rotateAngle);
        //设置参数 设置margin
        ViewGroup.MarginLayoutParams marginLayoutParams = new ViewGroup.MarginLayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        marginLayoutParams.setMargins(marginLeft, marginTop, 0, 0);
        marginLayoutParams.height = height;
        marginLayoutParams.width = width;
        imageView.setLayoutParams(marginLayoutParams);

//        imageView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(context, "点击了图片", Toast.LENGTH_SHORT).show();
//            }
//        });

        return imageView;
    }

    /**
     * 内容，默认居中
     *
     * @param context
     * @param marginLeft    左边距
     * @param marginTop     顶边距
     * @param contentString 内容
     * @param stringSize    文字size  单位 px
     * @param textColor     文字颜色  例如：#000000
     * @return
     */
    public static TextView getTextView(Context context, int marginLeft, int marginTop, String contentString, int stringSize, String textColor, int rotateAngle) {
        TextView textView = new TextView(context);
        textView.setText(contentString);
        textView.setGravity(Gravity.CENTER);
        textView.setRotation(rotateAngle);
        textView.setTextSize(TypedValue.COMPLEX_UNIT_PX, stringSize);
        textView.setTextColor(Color.parseColor(textColor));
        //设置位置参数
        ViewGroup.MarginLayoutParams marginLayoutParams = new ViewGroup.MarginLayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        marginLayoutParams.setMargins(marginLeft, marginTop, 0, 0);
        textView.setLayoutParams(marginLayoutParams);

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
     * @return
     */
    public static float[] getViewRealCoordinate(Context context, int leftPercent, int topPercent, int widthPercent, int heightPercent) {
        int screenWidth = getScreenWidth(context);
        int screenHeight = getScreenHeight(context);
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

    public static EditText getEditView(Context context,
                                       int marginLeft,
                                       int marginTop,
                                       int width,
                                       int height,
                                       int stringSize,
                                       String textColor,
                                       int gravity,
                                       int rotateAngle) {
        EditText editText = new EditText(context);
        editText.setTextColor(Color.BLACK);
        editText.setBackgroundColor(Color.WHITE);
        editText.setBackground(null);
        editText.setMaxHeight(height);
//        editText.setGravity(gravity);
//        editText.setTextSize(height);
        editText.setRotation(rotateAngle);
//        textView.setTextSize(TypedValue.COMPLEX_UNIT_PX, stringSize);
//        textView.setTextColor(Color.parseColor(textColor));
        //设置位置参数
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(width, ViewGroup.LayoutParams.WRAP_CONTENT);

        ViewGroup.MarginLayoutParams marginLayoutParams = new ViewGroup.MarginLayoutParams(width, ViewGroup.LayoutParams.WRAP_CONTENT);
        marginLayoutParams.setMargins(marginLeft, marginTop, 0, 0);

        editText.setLayoutParams(layoutParams);

        return editText;
    }

    /**
     * 获取屏幕宽
     * @param context
     * @return
     */
    private static int getScreenWidth(Context context) {

        WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);

        if (windowManager == null) {
            Log.d(TAG, "getScreenWidth: " + context.getResources().getDisplayMetrics().widthPixels);
            return context.getResources().getDisplayMetrics().widthPixels;
        }
        Point point = new Point();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            windowManager.getDefaultDisplay().getRealSize(point);
        } else {
            windowManager.getDefaultDisplay().getSize(point);
        }
        Log.d(TAG, "getScreenWidth: " + point.x);
        return point.x;
    }

    /**
     * 获取屏幕的高度（单位：px）
     *
     * @return 屏幕高
     */
    private static int getScreenHeight(Context context) {

        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        if (wm == null) {
            return context.getResources().getDisplayMetrics().heightPixels;
        }
        Point point = new Point();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            wm.getDefaultDisplay().getRealSize(point);
        } else {
            wm.getDefaultDisplay().getSize(point);
        }
        return point.y;
    }
}
