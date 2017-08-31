package com.mytestdemo.my_galleryfinal;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.mytestdemo.R;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by cuishuxiang on 2017/4/13.
 */

public class MyBitmapActivity extends AppCompatActivity {

    /**
     * //方法:
     * 1 生成圆角Bitmap图片
     * 2 生成Bitmap缩量图
     * 3 压缩图片场长宽以及kB
     */
    private ImageView imageView;
    private Bitmap copyRawBitmap1;
    private Bitmap copyRawBitmap2;
    private Bitmap copyRawBitmap3;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bitmap_layout);

        imageView = (ImageView) findViewById(R.id.bitmap_img);

        initBitmap();
    }

    private void initBitmap() {
        //第一种方式:从资源文件中得到图片
        Bitmap rawBitmap = BitmapFactory.decodeResource(getResources(),R.mipmap.ic_launcher);
        copyRawBitmap1=rawBitmap;
        copyRawBitmap2=rawBitmap;
        copyRawBitmap3=rawBitmap;

        //第二种方式:从SD卡中得到图片(方法1)
        String SDCarePath= Environment.getExternalStorageDirectory().toString();
        String filePath=SDCarePath+"/"+"haha.jpg";
        Bitmap rawBitmap1 = BitmapFactory.decodeFile(filePath, null);

        //————>以下为将设置图片的圆角
        Bitmap roundCornerBitmap=this.toRoundCorner(rawBitmap, 40);
        imageView.setImageBitmap(roundCornerBitmap);

        //————>以下为将图片高宽和的大小kB压缩
        // 得到图片原始的高宽
        int rawHeight = rawBitmap.getHeight();
        int rawWidth = rawBitmap.getWidth();
        // 设定图片新的高宽
        int newHeight = 500;
        int newWidth = 500;
        // 计算缩放因子
        float heightScale = ((float) newHeight) / rawHeight;
        float widthScale = ((float) newWidth) / rawWidth;
        // 新建立矩阵
        Matrix matrix = new Matrix();
        matrix.postScale(heightScale, widthScale);
        // 设置图片的旋转角度
        //matrix.postRotate(-30);
        // 设置图片的倾斜
        //matrix.postSkew(0.1f, 0.1f);

        //将图片大小压缩
        //压缩后图片的宽和高以及kB大小均会变化
        Bitmap newBitmap = Bitmap.createBitmap(rawBitmap, 0, 0, rawWidth,rawWidth, matrix, true);

        // 将Bitmap转换为Drawable
        Drawable newBitmapDrawable = new BitmapDrawable(newBitmap);
        imageView.setImageDrawable(newBitmapDrawable);
        //然后将Bitmap保存到SDCard中,方便于原图片的比较
        this.compressAndSaveBitmapToSDCard(newBitmap, "xx100.jpg", 80);
        /**
         * 原图大小为625x690 90.2kB
         * 问题:
         * 如果设置图片500x500 压缩后大小为171kB.即压缩后kB反而变大了.
         * 原因是将:compress(Bitmap.CompressFormat.JPEG, quality, fileOutputStream);
         * 第二个参数quality设置得有些大了(比如100).
         * 常用的是80,刚设100太大了造成的.
         * ————>以上为将图片高宽和的大小kB压缩
         */

    }


    //获取SDCard的目录路径功能
    private String getSDCardPath() {
        String SDCardPath = null;
        // 判断SDCard是否存在
        boolean IsSDcardExist = Environment.getExternalStorageState().equals(android.os.Environment.MEDIA_MOUNTED);
        if (IsSDcardExist) {
            SDCardPath = Environment.getExternalStorageDirectory().toString();
        }
        return SDCardPath;
    }
    //压缩且保存图片到SDCard
    private void compressAndSaveBitmapToSDCard(Bitmap rawBitmap,String fileName,int quality){
        String saveFilePaht=this.getSDCardPath()+ File.separator+fileName;
        File saveFile=new File(saveFilePaht);
        if (!saveFile.exists()) {
            try {
                saveFile.createNewFile();
                FileOutputStream fileOutputStream=new FileOutputStream(saveFile);
                if (fileOutputStream!=null) {
                    //imageBitmap.compress(format, quality, stream);
                    //把位图的压缩信息写入到一个指定的输出流中
                    //第一个参数format为压缩的格式
                    //第二个参数quality为图像压缩比的值,0-100.0 意味着小尺寸压缩,100意味着高质量压缩
                    //第三个参数stream为输出流
                    rawBitmap.compress(Bitmap.CompressFormat.JPEG, quality, fileOutputStream);
                }
                fileOutputStream.flush();
                fileOutputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    //第一个参数:原本Bitmap的options
    //第二个参数:希望生成的缩略图的宽高中的较小的值
    //第三个参数:希望生成的缩量图的总像素
    public static int computeSampleSize(BitmapFactory.Options options,int minSideLength, int maxNumOfPixels) {
        int initialSize = computeInitialSampleSize(options, minSideLength,maxNumOfPixels);
        int roundedSize;
        if (initialSize <= 8) {
            roundedSize = 1;
            while (roundedSize < initialSize) {
                roundedSize <<= 1;
            }
        } else {
            roundedSize = (initialSize + 7) / 8 * 8;
        }
        return roundedSize;
    }
    private static int computeInitialSampleSize(BitmapFactory.Options options,int minSideLength, int maxNumOfPixels) {
        //原始图片的宽
        double w = options.outWidth;
        //原始图片的高
        double h = options.outHeight;
        System.out.println("========== w="+w+",h="+h);
        int lowerBound = (maxNumOfPixels == -1) ? 1 : (int) Math.ceil(Math
                .sqrt(w * h / maxNumOfPixels));
        int upperBound = (minSideLength == -1) ? 128 : (int) Math.min(
                Math.floor(w / minSideLength), Math.floor(h / minSideLength));
        if (upperBound < lowerBound) {
            // return the larger one when there is no overlapping zone.
            return lowerBound;
        }
        if ((maxNumOfPixels == -1) && (minSideLength == -1)) {
            return 1;
        } else if (minSideLength == -1) {
            return lowerBound;
        } else {
            return upperBound;
        }
    }


    /**
     * @param bitmap 需要修改的图片
     * @param pixels 圆角的弧度
     * @return 圆角图片
     */
    public Bitmap toRoundCorner(Bitmap bitmap, int pixels) {
        Bitmap roundCornerBitmap = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(roundCornerBitmap);
        int color = 0xff424242;//int color = 0xff424242;
        Paint paint = new Paint();
        paint.setColor(color);
        //防止锯齿
        paint.setAntiAlias(true);
        Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());
        RectF rectF = new RectF(rect);
        float roundPx = pixels;
        //相当于清屏
        canvas.drawARGB(0, 0, 0, 0);
        //先画了一个带圆角的矩形
        canvas.drawRoundRect(rectF, roundPx, roundPx, paint);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        //再把原来的bitmap画到现在的bitmap！！！注意这个理解
        canvas.drawBitmap(bitmap, rect, rect, paint);
        return roundCornerBitmap;
    }

}
