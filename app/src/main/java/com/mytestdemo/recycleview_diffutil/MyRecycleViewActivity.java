package com.mytestdemo.recycleview_diffutil;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.mytestdemo.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cuishuxiang on 2017/3/31.
 *
 * Android DiffUtil新工具类(测试 RecyclerView刷新)
 */

public class MyRecycleViewActivity extends AppCompatActivity implements DiffRecyclerAdapter.MyRecycleItemClick{
    private String TAG = MyRecycleViewActivity.class.getSimpleName();
    private RecyclerView mRecycleView;
    private DiffRecyclerAdapter adapter;

    private List<RecycleBean> newlist;
    private List<RecycleBean> oldList;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Toast.makeText(getBaseContext(), TAG, Toast.LENGTH_SHORT).show();
        setContentView(R.layout.activity_reclcleview_layout);

        initView();
    }

    private void initView() {
        mRecycleView = (RecyclerView) findViewById(R.id.mRecycleView);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecycleView.setLayoutManager(linearLayoutManager);
        mRecycleView.setItemAnimator(new DefaultItemAnimator());
        /**
         * 添加数据源
         */
        oldList = new ArrayList<>();
        newlist = new ArrayList<>();
        String url_img = "https://ss0.bdstatic.com/5aV1bjqh_Q23odCf/static/superman/img/logo/bd_logo1_31bdc765.png";
        for (int i=0;i<100;i++) {
            oldList.add(new RecycleBean("姓名:"+i,url_img));
            newlist.add(new RecycleBean("姓名:"+i,url_img));
        }

        adapter = new DiffRecyclerAdapter(oldList, this);
        adapter.setMyRecycleItemClick(this);
        mRecycleView.setAdapter(adapter);

    }

    @Override
    public void mItemClick(View view, int position) {
        Toast.makeText(getBaseContext(), "点击了：" + position, Toast.LENGTH_SHORT).show();
        newlist.remove(position);
//        newlist.remove(position + 1);
//        newlist.add(position + 1, new RecycleBean("改变", ""));

        DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(new MyDiffUtilCallBack(oldList, newlist),true);
        adapter.setData(newlist);
        diffResult.dispatchUpdatesTo(adapter);

        oldList.clear();
        oldList.addAll(newlist);
//        adapter.notifyDataSetChanged();
    }

    @Override
    public void mLongClick(View view, int position) {
        Toast.makeText(getBaseContext(), "长按了：" + position, Toast.LENGTH_SHORT).show();
    }
}
