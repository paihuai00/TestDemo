package com.mytestdemo.my_side_bar;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.mytestdemo.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by cuishuxiang on 2017/8/21.
 */

public class MySideBarActivity extends AppCompatActivity {

    @BindView(R.id.mSideBar)
    SideBarView mSideBar;
    @BindView(R.id.show_text)
    TextView showText;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_sidebar);
        ButterKnife.bind(this);

        mSideBar.setLetterTouchListener(new SideBarView.LetterTouchListener() {
            @Override
            public void letterTouchListener(String letter, boolean isPress) {
                if (isPress) {
                    showText.setVisibility(View.VISIBLE);
                    showText.setText(letter);
                }else {
                    showText.setVisibility(View.GONE);
                }
            }
        });
    }
}
