package com.mytestdemo.circle_menu_group;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.mytestdemo.BaseActivity;
import com.mytestdemo.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @Created by cuishuxiang
 * @date 2018/1/13.
 * <p>
 * 环形的 menu
 */

public class CircleMenuActivity extends BaseActivity {
    private static final String TAG = "CircleMenuActivity";
    @BindView(R.id.id_circle_menu_item_center)
    ImageView idCircleMenuItemCenter;
    @BindView(R.id.circle_menu_layout)
    CircleMenuLayout circleMenuLayout;
    @BindView(R.id.change_btn)
    Button mChangeBtn;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_circle_layout);
        ButterKnife.bind(this);

        int[] draws = new int[]{R.drawable.a, R.drawable.a, R.drawable.a, R.drawable.a, R.drawable.a, R.drawable.a, R.drawable.a};
        String[] strings = new String[]{"这是1", "这是1", "这是1", "这是1", "这是1", "这是1", "这是1"};
        circleMenuLayout.setMenuItemIconsAndTexts(draws, strings);

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

        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        //获取设备的 密度(density = dpi/160)
        //例如：设备dpi为480的密度为  density = 240/160 = 1.5
        float density = displayMetrics.density;
        float dpi = displayMetrics.densityDpi;
        float widthPixels = displayMetrics.widthPixels;
        float heightPixels = displayMetrics.heightPixels;

        Log.d(TAG, "density = " + density);
        Log.d(TAG, "dpi = " + dpi);
        Log.d(TAG, "widthPixels = " + widthPixels);
        Log.d(TAG, "heightPixels = " + heightPixels);


    }

    @OnClick(R.id.change_btn)
    public void onViewClicked() {
        idCircleMenuItemCenter.setImageResource(R.drawable.b);
    }
}
