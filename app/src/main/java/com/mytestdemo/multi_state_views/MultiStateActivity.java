package com.mytestdemo.multi_state_views;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;

import com.classic.common.MultipleStatusView;
import com.mytestdemo.BaseActivity;
import com.mytestdemo.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @Created by cuishuxiang
 * @date 2018/1/13.
 * <p>
 * 多状态
 */

public class MultiStateActivity extends BaseActivity {
    private static final String TAG = "MultiStateActivity";
    @BindView(R.id.multi_state_view)
    MultipleStatusView multiStateView;
    @BindView(R.id.loading_btn)
    Button loadingBtn;
    @BindView(R.id.error_btn)
    Button errorBtn;
    @BindView(R.id.empty_btn)
    Button emptyBtn;
    @BindView(R.id.success_btn)
    Button successBtn;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multi_state);
        ButterKnife.bind(this);


        multiStateView.showContent();

    }

    @OnClick({R.id.loading_btn, R.id.error_btn, R.id.empty_btn, R.id.success_btn})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.loading_btn:
                multiStateView.showLoading();
                break;
            case R.id.error_btn:
                multiStateView.showError();
                break;
            case R.id.empty_btn:
                multiStateView.showEmpty();
                break;
            case R.id.success_btn:
                multiStateView.showContent();
                break;
        }
    }
}
