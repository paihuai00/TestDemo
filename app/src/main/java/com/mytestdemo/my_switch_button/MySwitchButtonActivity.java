package com.mytestdemo.my_switch_button;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.CompoundButton;
import android.widget.Toast;

import com.kyleduo.switchbutton.SwitchButton;
import com.mytestdemo.R;

/**
 * Created by TJXF-JY-WEIDINGQIANG on 2017/4/6.
 */

public class MySwitchButtonActivity extends AppCompatActivity {
    private SwitchButton mSwitch;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_switch_btn);

        mSwitch = (SwitchButton) findViewById(R.id.mSwitch);
        mSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                Toast.makeText(getBaseContext(), "--:" + isChecked, Toast.LENGTH_LONG).show();
            }
        });
    }
}
