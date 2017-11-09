package com.mytestdemo.joint_images;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.mytestdemo.R;

/**
 * @author cuishuxiang
 * @date 2017/11/6.
 */

public class JointImagesView extends View {
    private static final String TAG = JointImagesView.class.getSimpleName();

    private Bitmap bitmap;

    public JointImagesView(Context context) {
        this(context,null);
    }

    public JointImagesView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public JointImagesView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

    }


    public void setBitmaps(Bitmap bit1,Bitmap bit2) {

        bitmap = newBitmap(bit1, bit2);

        invalidate();

    }

    /**
     * 拼接图片
     * @param bit1
     * @param bit2
     * @return 返回拼接后的Bitmap
     */
    private Bitmap newBitmap(Bitmap bit1,Bitmap bit2){

        //选取一个宽度比较大的值
        int width = bit1.getWidth() > bit2.getWidth() ? bit1.getWidth() : bit2.getWidth();
        int height = bit1.getHeight() + bit2.getHeight();
        Log.d(TAG, "newBitmap: 宽度 =  " + width + " " + "  高度 = " + height);

        //创建一个空的Bitmap(内存区域),宽度等于第一张图片的宽度，高度等于两张图片高度总和
        Bitmap bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        //将bitmap放置到绘制区域,并将要拼接的图片绘制到指定内存区域
        Canvas canvas = new Canvas(bitmap);
        canvas.drawBitmap(bit1, 0, 0, null);
        canvas.drawBitmap(bit2, 0, bit1.getHeight(), null);
        return bitmap;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (bitmap != null) {
            canvas.drawBitmap(bitmap, 0, 0, null);
            bitmap.recycle();
        }
    }
}

