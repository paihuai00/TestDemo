package com.mytestdemo.my_test_duiqi;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.mytestdemo.R;

import me.codeboy.android.aligntextview.AlignTextView;

/**
 * Created by cuishuxiang on 2017/4/12.
 */

public class MyTestActivity extends AppCompatActivity {
    private AlignTextView align_txt;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mytest_layout);
        align_txt = (AlignTextView) findViewById(R.id.align_txt);

    }
}
