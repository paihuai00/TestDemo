package com.mytestdemo.scroll_hide_toolbar;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.util.Log;
import android.widget.Button;

import com.mytestdemo.BaseActivity;
import com.mytestdemo.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @Created by cuishuxiang
 * @date 2018/1/8.
 * <p>
 * rv 滑动，隐藏ToolBar
 */

public class ScrollHideToolbarActivity extends BaseActivity {
    private static final String TAG = "ScrollHideToolbarActivi";
    @BindView(R.id.btn)
    Button btn;
    @BindView(R.id.hide_toolbar_rv)
    RecyclerView hideToolbarRv;
    private List<String> stringList;

    private HideToolbarRvAdapter adapter;

    private float alpha = 1;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scroll_hide_toolbar);
        ButterKnife.bind(this);



        stringList = new ArrayList<>();
        for (int i = 0; i < 15; i++) {
            stringList.add("  item：" + i);
        }

        adapter = new HideToolbarRvAdapter(R.layout.item_diff_recyclerview, stringList);

        hideToolbarRv.setLayoutManager(new LinearLayoutManager(this));

        hideToolbarRv.setAdapter(adapter);

        hideToolbarRv.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                //上滑动        dy -
                // 下滑 (隐藏)  dy +
                //只做简单处理

                alpha = dy > 0 ? 0: 1;

                Log.d(TAG, "onScrolled: " + " dy = " + dy + "  alpha = " + alpha);

                btn.setAlpha(alpha);
            }
        });


    }
}
