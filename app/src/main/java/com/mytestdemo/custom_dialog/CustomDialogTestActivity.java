package com.mytestdemo.custom_dialog;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.mytestdemo.BaseActivity;
import com.mytestdemo.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by cuishuxiang on 2017/10/12.
 */

public class CustomDialogTestActivity extends BaseActivity {
    @BindView(R.id.center_btn)
    Button centerBtn;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_dialog);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.center_btn)
    public void onViewClicked() {
        CustomAlertDialog customAlertDialog =
                new CustomAlertDialog.Builder(this)
                        .setContentView(R.layout.dialog_laout)
                        .setOnClickListener(R.id.ceshi_btn, new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Toast.makeText(getBaseContext(),"测试Btn",Toast.LENGTH_SHORT).show();
                            }
                        })
                        .fullWidth()
                        .showFromBottom(true)
                        .create();

        customAlertDialog.show();
    }
}
