package com.mytestdemo.my_infinite_img;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.bakerj.infinitecards.InfiniteCardView;
import com.mytestdemo.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * https://github.com/BakerJQ/Android-InfiniteCards/blob/master/README_cn.md
 */
public class InfiniteActivity extends AppCompatActivity {

    @BindView(R.id.infinite_view)
    InfiniteCardView infiniteView;
    @BindView(R.id.back_front)
    Button backFront;
    @BindView(R.id.back_last)
    Button backLast;
    @BindView(R.id.next_front)
    Button nextFront;

    private InfiniteAdapter adapter;

    private int[] imgs = new int[]
            {R.drawable.pic1, R.drawable.pic2, R.drawable.pic3, R.drawable.pic4};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_infinite);
        ButterKnife.bind(this);

        adapter = new InfiniteAdapter(imgs, this);

        infiniteView.setAdapter(adapter);


    }


    @OnClick({R.id.back_front, R.id.back_last, R.id.next_front})
    public void onViewClicked(View view) {

        switch (view.getId()) {
            case R.id.back_front:
                infiniteView.setAnimType(InfiniteCardView.ANIM_TYPE_FRONT);
                infiniteView.setAdapter(adapter);
                break;

            case R.id.next_front:
                infiniteView.setAnimType(InfiniteCardView.ANIM_TYPE_SWITCH);
                infiniteView.setAdapter(adapter);
                break;

            case R.id.back_last:

                infiniteView.setAnimType(InfiniteCardView.ANIM_TYPE_FRONT_TO_LAST);
                infiniteView.setAdapter(adapter);

                break;
        }
    }

}
