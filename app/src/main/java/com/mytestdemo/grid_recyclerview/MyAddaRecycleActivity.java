package com.mytestdemo.grid_recyclerview;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.mytestdemo.R;
import com.zhy.adapter.recyclerview.wrapper.HeaderAndFooterWrapper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by TJXF-JY-WEIDINGQIANG on 2017/4/3.
 */

public class MyAddaRecycleActivity extends AppCompatActivity {
    private RecyclerView mRecycleView;
    private GridLayoutManager gridLayoutManager;
    private MyAddAdapter adapter;

    private List<String> list;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_add_recycler_layout);

        mRecycleView = (RecyclerView) findViewById(R.id.mRecycleView);
        gridLayoutManager = new GridLayoutManager(this, 2, GridLayoutManager.HORIZONTAL,false);
        mRecycleView.setLayoutManager(gridLayoutManager);
        mRecycleView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.HORIZONTAL));


        list = new ArrayList<>();
        for (int i=0;i<100;i++) {
            list.add("字符" + i);
        }
        adapter = new MyAddAdapter(this, list);



        HeaderAndFooterWrapper headerAndFooterWrapper = new HeaderAndFooterWrapper(adapter);

        TextView textView = new TextView(this);
        textView.setText("---------------head-----------");
        headerAndFooterWrapper.addHeaderView(textView);

        mRecycleView.setAdapter(headerAndFooterWrapper);

    }
}
