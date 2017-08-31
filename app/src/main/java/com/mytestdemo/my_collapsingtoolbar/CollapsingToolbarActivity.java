package com.mytestdemo.my_collapsingtoolbar;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.mytestdemo.R;
import com.mytestdemo.view.MyRecyclerItemDivier;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * M D 实现的折叠工具栏的效果（CollapsingToolbarLayout）
 */

public class CollapsingToolbarActivity extends AppCompatActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.mRecycleView)
    RecyclerView mRecycleView;
    @BindView(R.id.activity_collapsing_toolbar)
    CoordinatorLayout activityCollapsingToolbar;
    @BindView(R.id.mCollapsing)
    CollapsingToolbarLayout mCollapsing;


    private RecyclerAdapter adapter;
    private List<String> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collapsing_toolbar);
        ButterKnife.bind(this);


        //沉浸式状态栏
//        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

//        setSupportActionBar(toolbar);
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mCollapsing.setTitle("我的mCollapsing");
        //设置隐藏图片时候ToolBar的颜色
        mCollapsing.setContentScrimColor(Color.parseColor("#11B7F3"));
        //通过CollapsingToolbarLayout修改字体颜色
        mCollapsing.setExpandedTitleColor(Color.WHITE);//设置还没收缩时状态下字体颜色
        mCollapsing.setCollapsedTitleTextColor(Color.GREEN);//设置收缩后Toolbar上字体的颜色

        initRecycler();
    }

    private void initRecycler() {
        mRecycleView.setLayoutManager(new LinearLayoutManager(this));
        mRecycleView.addItemDecoration(new MyRecyclerItemDivier(this));

        list = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            list.add("" + i);
        }

        adapter = new RecyclerAdapter(list, this);

        mRecycleView.setAdapter(adapter);
    }
}
