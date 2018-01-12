package com.mytestdemo.my_zh_photo_choose;

import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.mytestdemo.R;
import com.zhihu.matisse.Matisse;
import com.zhihu.matisse.MimeType;
import com.zhihu.matisse.engine.impl.GlideEngine;
import com.zhihu.matisse.internal.entity.CaptureStrategy;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by cuishuxiang on 2017/8/4.
 * 知乎，图片选择器
 */

public class ZHPhotoActivity extends AppCompatActivity {
    private static final String TAG = "ZHPhotoActivity";
    @BindView(R.id.choose_btn)
    Button chooseBtn;
    @BindView(R.id.show_photo)
    ImageView showPhoto;

    List<Uri> selectedList;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zh_photo);
        ButterKnife.bind(this);

        selectedList = new ArrayList<>();

    }

    @OnClick(R.id.choose_btn)
    public void onViewClicked() {
        Matisse.from(this)
                .choose(MimeType.of(MimeType.PNG,MimeType.JPEG))//选择mime类型,
                .countable(true)
                .capture(true)//开启拍照
                .captureStrategy(
                        new CaptureStrategy(true, "com.mytestdemo.fileprovider"))
                .maxSelectable(5)//最多选择5张图片
                .theme(R.style.Matisse_Zhihu)
                .thumbnailScale(1f)//缩略图比例
                .imageEngine(new Glide4Engine())//图片加载引擎  注：使用Glide4需要使用自定义的engine
                .forResult(1);//作为标记的 请求码

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (1 == requestCode && resultCode == RESULT_OK) {

//            if (null == data)
//                return;
//            photoPathList = data.getStringArrayListExtra(MatisseActivity.EXTRA_RESULT_SELECTION_PATH);
            selectedList = Matisse.obtainResult(data);
            Log.d(TAG, "onActivityResult: 选择图片数量" + selectedList.size());
//            showPhoto.setImageURI(selectedList.get(0));
            Glide.with(this)
                    .load(selectedList.get(0))
                    .into(showPhoto);
        }
    }



    /**
     * Try to return the absolute file path from the given Uri
     *
     * @param context
     * @param uri
     * @return the file path or null
     */
    public static String getRealFilePath(final Context context, final Uri uri ) {
        if ( null == uri ) return null;
        final String scheme = uri.getScheme();
        String data = null;
        if ( scheme == null )
            data = uri.getPath();
        else if ( ContentResolver.SCHEME_FILE.equals( scheme ) ) {
            data = uri.getPath();
        } else if ( ContentResolver.SCHEME_CONTENT.equals( scheme ) ) {
            Cursor cursor = context.getContentResolver().query( uri, new String[] { MediaStore.Images.ImageColumns.DATA }, null, null, null );
            if ( null != cursor ) {
                if ( cursor.moveToFirst() ) {
                    int index = cursor.getColumnIndex( MediaStore.Images.ImageColumns.DATA );
                    if ( index > -1 ) {
                        data = cursor.getString( index );
                    }
                }
                cursor.close();
            }
        }
        return data;
    }
}
