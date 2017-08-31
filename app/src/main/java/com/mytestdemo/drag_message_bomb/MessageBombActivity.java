package com.mytestdemo.drag_message_bomb;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.mytestdemo.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by cuishuxiang on 2017/8/9.
 */

public class MessageBombActivity extends AppCompatActivity {


    @BindView(R.id.num_txt)
    TextView numTxt;
    @BindView(R.id.bomb_btn)
    Button bombBtn;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_msg_bomb);
        ButterKnife.bind(this);

        MessageBubbleBombView.addMoveView(numTxt, new MoveTouchListener.DismissCallBack() {
            @Override
            public void dismiss() {
                //view爆炸消失，回调函数
                Toast.makeText(MessageBombActivity.this, "文本，消失", Toast.LENGTH_LONG).show();
            }

        });

        MessageBubbleBombView.addMoveView(bombBtn, new MoveTouchListener.DismissCallBack() {
            @Override
            public void dismiss() {
                Toast.makeText(MessageBombActivity.this, "按钮，消失", Toast.LENGTH_LONG).show();
            }
        });


    }

    @OnClick({R.id.num_txt, R.id.bomb_btn})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.num_txt:
                Toast.makeText(MessageBombActivity.this, "点击文本", Toast.LENGTH_LONG).show();
                break;
            case R.id.bomb_btn:
                Toast.makeText(MessageBombActivity.this, "按钮", Toast.LENGTH_LONG).show();
                break;
        }
    }
}
