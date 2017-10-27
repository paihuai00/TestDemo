package com.mytestdemo.constraint_test;

import android.content.ContentResolver;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.mytestdemo.BaseActivity;
import com.mytestdemo.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by cuishuxiang on 2017/9/20.
 */

public class ConstraintActivity extends BaseActivity {
    private static final String TAG = "ConstraintActivity";
    @BindView(R.id.tab3)
    ImageView tab3;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_constraint);
        ButterKnife.bind(this);

        /**
         * Drawable 转  uri
         */
        Uri uri = Uri.parse(ContentResolver.SCHEME_ANDROID_RESOURCE + "://"
                + getResources().getResourcePackageName(R.drawable.big_img) + "/"
                + getResources().getResourceTypeName(R.drawable.big_img) + "/"
                + getResources().getResourceEntryName(R.drawable.big_img));

        tab3.setImageURI(uri);

        Glide.with(this)
                .load(uri)
                .into(tab3);

        Log.d(TAG, "onCreate: " + uri.getPath());
    }
}
