package com.mytestdemo.my_fab_button;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.internal.NavigationMenu;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.mytestdemo.R;
import com.mytestdemo.grid_recyclerview.MyAddAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.github.yavski.fabspeeddial.FabSpeedDial;

/**
 * Created by cuishuxiang on 2017/4/22.
 */

public class MyFloatActionButtonActivity extends AppCompatActivity {
    @BindView(R.id.mToolbar)
    Toolbar mToolbar;
    @BindView(R.id.mRecycleView)
    RecyclerView mRecycleView;
    @BindView(R.id.fab_btn)
    FabSpeedDial fabBtn;
    private FabSpeedDial fab_btn;

    private MyAddAdapter adpter;
    private List<String> list;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fab_layout);
        ButterKnife.bind(this);


        initView();

    }

    private void initView() {
        fab_btn = (FabSpeedDial) findViewById(R.id.fab_btn);
        fab_btn.setMenuListener(new FabSpeedDial.MenuListener() {
            @Override
            public boolean onPrepareMenu(NavigationMenu navigationMenu) {
                return true;
            }

            @Override
            public boolean onMenuItemSelected(MenuItem menuItem) {
                return false;
            }

        });
        list = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            list.add("这是：" + i);
        }
        adpter = new MyAddAdapter(this, list);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);

        mRecycleView.setLayoutManager(linearLayoutManager);

        mRecycleView.setAdapter(adpter);

        mToolbar.setLogo(R.drawable.icon_star);
        mToolbar.setTitle("自定义Behaivor");
        mToolbar.setTitleTextColor(Color.parseColor("#ffffff"));
    }

}
