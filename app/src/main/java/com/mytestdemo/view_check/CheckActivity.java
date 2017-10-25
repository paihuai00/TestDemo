package com.mytestdemo.view_check;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.Button;

import com.mytestdemo.BaseActivity;
import com.mytestdemo.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by cuishuxiang on 2017/10/24.
 */

public class CheckActivity extends BaseActivity {

    @BindView(R.id.check_view)
    CheckView checkView;
    @BindView(R.id.strat_btn)
    Button stratBtn;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.strat_btn)
    public void onViewClicked() {
        checkView.clickCheckView();
    }
}
