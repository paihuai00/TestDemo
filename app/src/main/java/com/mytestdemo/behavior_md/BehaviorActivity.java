package com.mytestdemo.behavior_md;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.mytestdemo.BaseActivity;
import com.mytestdemo.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author cuishuxiang
 * @date 2017/11/14.
 */

public class BehaviorActivity extends BaseActivity {
    @BindView(R.id.listview)
    RecyclerView recyclerView;
    private BehaviorAdapter adapter;

    private List<String> stringList;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_behavior);
        ButterKnife.bind(this);

        stringList = new ArrayList<>();

        for (int i = 0; i < 50; i++) {
            stringList.add("这是 ： " + i);
        }
        adapter = new BehaviorAdapter(this, stringList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        recyclerView.setHasFixedSize(true);
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.setAdapter(adapter);

    }
}
