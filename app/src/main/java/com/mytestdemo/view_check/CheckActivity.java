package com.mytestdemo.view_check;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.Button;
import android.widget.Toast;

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

    private boolean isChecked = false;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check);
        ButterKnife.bind(this);

        checkView.setOnCheckChangListener(new CheckView.OnCheckChangListener() {
            @Override
            public void onCheckChangedListenter(CheckView checkView, boolean isChecked) {
                Toast.makeText(CheckActivity.this, " " + isChecked, Toast.LENGTH_SHORT).show();
            }
        });
    }

    @OnClick(R.id.strat_btn)
    public void onViewClicked() {
        isChecked = !isChecked;
        checkView.setChecked(isChecked);
    }
}
