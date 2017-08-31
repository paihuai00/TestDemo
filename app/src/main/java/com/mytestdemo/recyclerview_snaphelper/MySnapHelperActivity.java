package com.mytestdemo.recyclerview_snaphelper;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.mytestdemo.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by TJXF-JY-WEIDINGQIANG on 2017/4/5.
 *
 * http://blog.csdn.net/whitley_gong/article/details/52421215
 */

public class MySnapHelperActivity extends AppCompatActivity {
    private RecyclerView snap_recyclerview;
    private LinearLayoutManager linearLayoutManager;

    private SnapRecyclerAdapter adapter;
    private List<String> list;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_snaphelper_layout);

        snap_recyclerview = (RecyclerView) findViewById(R.id.snap_recyclerview);
        linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        snap_recyclerview.setLayoutManager(linearLayoutManager);

        list = new ArrayList<>();
        for (int i=0;i<20;i++) {
            list.add("第："+i);
        }
        adapter = new SnapRecyclerAdapter(list, this);

        snap_recyclerview.setAdapter(adapter);

        /**
         * SnapHelper
         */

        MySnapHelper mySnapHelper = new MySnapHelper();
        mySnapHelper.attachToRecyclerView(snap_recyclerview);

    }
}
