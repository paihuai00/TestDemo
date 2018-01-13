package com.mytestdemo.circle_menu_group;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.mytestdemo.BaseActivity;
import com.mytestdemo.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @Created by cuishuxiang
 * @date 2018/1/13.
 * <p>
 * 环形的 menu
 */

public class CircleMenuActivity extends BaseActivity {

    @BindView(R.id.id_circle_menu_item_center)
    ImageView idCircleMenuItemCenter;
    @BindView(R.id.circle_menu_layout)
    CircleMenuLayout circleMenuLayout;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_circle_layout);
        ButterKnife.bind(this);

        int[] draws = new int[]{R.drawable.a, R.drawable.a, R.drawable.a, R.drawable.a, R.drawable.a, R.drawable.a, R.drawable.a};
        String[] strings = new String[]{"这是1", "这是1", "这是1", "这是1", "这是1", "这是1", "这是1"};
        circleMenuLayout.setMenuItemIconsAndTexts(draws,strings);

        circleMenuLayout.setOnMenuItemClickListener(new CircleMenuLayout.OnMenuItemClickListener() {
            @Override
            public void itemClick(View view, int pos) {
                Toast.makeText(getBaseContext(), "点击了 : " + pos, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void itemCenterClick(View view) {
                Toast.makeText(getBaseContext(), "点击了中心", Toast.LENGTH_SHORT).show();
            }
        });

    }
}
