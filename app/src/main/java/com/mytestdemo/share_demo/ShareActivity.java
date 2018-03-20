package com.mytestdemo.share_demo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.Button;

import com.mytestdemo.BaseActivity;
import com.mytestdemo.R;


import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @Created by cuishuxiang
 * @date 2018/1/23.
 */

public class ShareActivity extends BaseActivity {

    @BindView(R.id.share_btn)
    Button mShareBtn;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.share_btn)
    public void onViewClicked() {

//        ShareDialog shareDialog = new ShareDialog(this)
//                .shareImageRes(R.drawable.icon_image_select)
//                .shareImageUrl("https://www.baidu.com/");
//        shareDialog.show();

    }
}
