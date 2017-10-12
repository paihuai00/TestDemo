package com.mytestdemo.recyclerview_brvah;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.mytestdemo.BaseActivity;
import com.mytestdemo.R;
import com.mytestdemo.smart_recyclerview.SmartRecyclerAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by cuishuxiang on 2017/9/25.
 * <p>
 * BRVAH是一个强大的RecyclerAdapter框架
 * <p>
 * http://www.jianshu.com/p/b343fcff51b0
 */

public class BravhActivity extends BaseActivity {

    @BindView(R.id.bravh_recycler)
    RecyclerView bravhRecycler;
    private List<String> stringList;

    private BrathAdapter adapter;

    private SmartRecyclerAdapter smartRecyclerAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bravh);
        ButterKnife.bind(this);

        initAdapter();
    }

    private void initAdapter() {
        stringList = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            stringList.add("Brath item：" + i);
        }
        //测试
        smartRecyclerAdapter = new SmartRecyclerAdapter(stringList, this);

        adapter = new BrathAdapter(R.layout.item_diff_recyclerview, stringList);

        bravhRecycler.setLayoutManager(new LinearLayoutManager(this));

        bravhRecycler.addItemDecoration(new TimeLineItemDecoration2(this, 20));

        bravhRecycler.setAdapter(smartRecyclerAdapter);
    }

}
