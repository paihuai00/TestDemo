package com.mytestdemo.drawable_test;

import android.app.Activity;
import android.graphics.drawable.ClipDrawable;
import android.graphics.drawable.TransitionDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.mytestdemo.R;

/**
 * Created by cuishuxiang on 2017/4/1.
 */

public class MyDrawableActivity extends Activity {
    private Button select_btn,tran_btn;
    private EditText select_edit;
    private EditText select_edit2;
    private EditText edit_normal;

    //clip
    private ImageView img_clip;
    private int num = 1;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawable_layout);

        initView();

        //渐变
        initTranBtn();

        initClip();
    }

    private void initClip() {
        img_clip = (ImageView) findViewById(R.id.img_clip);
        final ClipDrawable clipDrawable = (ClipDrawable) img_clip.getDrawable();
        clipDrawable.setLevel(0);

        //level的范围为[0,10000]，所以这里需要*100
        tran_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (num*100 < 10000) {
                    clipDrawable.setLevel(num * 100);
                    num++;
                }

            }
        });
    }

    private void initTranBtn() {
        tran_btn = (Button) findViewById(R.id.tran_btn);

        TransitionDrawable transitionDrawable = (TransitionDrawable) tran_btn.getBackground();
        transitionDrawable.startTransition(2000);//渐变持续的时间 A->B

        transitionDrawable.reverseTransition(3000);//B->A


    }

    private void initView() {
        select_btn = (Button) findViewById(R.id.select_btn);
        select_edit = (EditText) findViewById(R.id.select_edit);
        select_edit2 = (EditText) findViewById(R.id.select_edit2);
        edit_normal = (EditText) findViewById(R.id.edit_normal);
    }
}
