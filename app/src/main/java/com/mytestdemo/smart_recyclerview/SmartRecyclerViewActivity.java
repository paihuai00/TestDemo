package com.mytestdemo.smart_recyclerview;

import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.mytestdemo.R;
import com.scwang.smartrefresh.header.MaterialHeader;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.header.BezierRadarHeader;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 各种样式的header footer 下拉刷新
 *  https://github.com/scwang90/SmartRefreshLayout
 */
public class SmartRecyclerViewActivity extends AppCompatActivity {
    @BindView(R.id.i_recycler)
    RecyclerView iRecycler;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    @BindView(R.id.header)
    BezierRadarHeader header;
    ClassicsHeader classicsHeader;

    private List<String> list = new ArrayList<>();
    private SmartRecyclerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_svg);
        ButterKnife.bind(this);

        iRecycler.setLayoutManager(new LinearLayoutManager(this));
        refreshLayout.setEnableLoadmore(true);
//        refreshLayout.setEnableRefresh(true);

        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                Toast.makeText(getBaseContext(), "Smart OnRefreshListener", Toast.LENGTH_SHORT).show();
                refreshlayout.finishRefresh(2000);
            }
        });

        refreshLayout.setOnLoadmoreListener(new OnLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
                Toast.makeText(getBaseContext(), "Smart OnLoadmoreListener", Toast.LENGTH_SHORT).show();
                refreshlayout.finishLoadmore(2000);
            }
        });

        refreshLayout.setRefreshHeader(new MaterialHeader(this).setShowBezierWave(true));

        for (int i = 0; i < 20; i++) {
            list.add("item:" + i);
        }
        adapter = new SmartRecyclerAdapter(list, this);
        iRecycler.setAdapter(adapter);


        AlertDialog alertDialog = new AlertDialog.Builder(this)
                .setIcon(R.id.id_tag_autolayout_padding)
                .create();

        alertDialog.show();

    }

}
