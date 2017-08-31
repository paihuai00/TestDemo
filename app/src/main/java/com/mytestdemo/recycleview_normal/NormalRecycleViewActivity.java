package com.mytestdemo.recycleview_normal;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.mytestdemo.R;
import com.mytestdemo.recycleview_diffutil.DiffRecyclerAdapter;
import com.mytestdemo.recycleview_diffutil.RecycleBean;
import com.mytestdemo.view.MyRecyclerItemDivier;
import com.willowtreeapps.spruce.Spruce;
import com.willowtreeapps.spruce.animation.DefaultAnimations;
import com.willowtreeapps.spruce.sort.DefaultSort;
import com.zhy.adapter.recyclerview.wrapper.HeaderAndFooterWrapper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cuishuxiang on 2017/3/31.
 *
 * 正常recyclerview
 */

public class NormalRecycleViewActivity extends AppCompatActivity implements DiffRecyclerAdapter.MyRecycleItemClick{
    private String TAG = NormalRecycleViewActivity.class.getSimpleName();
    private RecyclerView mRecycleView;
    private DiffRecyclerAdapter adapter;

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

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(NormalRecycleViewActivity.this) {
            @Override
            public void onLayoutChildren(RecyclerView.Recycler recycler, RecyclerView.State state) {
                super.onLayoutChildren(recycler, state);
                Animator animator = new Spruce.SpruceBuilder(mRecycleView)
                        .sortWith(new DefaultSort(1000))
                        .animateWith(DefaultAnimations.fadeAwayAnimator(mRecycleView, 500),
                                ObjectAnimator.ofFloat(mRecycleView, "rotation", 0f, 360f).setDuration(800))
                        .start();
            }
        };

//        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this) {
//            @Override
//            public void onLayoutChildren(RecyclerView.Recycler recycler, RecyclerView.State state) {
//                super.onLayoutChildren(recycler, state);
//                Animator animator = new Spruce.SpruceBuilder(mRecycleView)
//                        .sortWith(new DefaultSort(500))
//                        .animateWith(DefaultAnimations.fadeInAnimator(mRecycleView, 5000),
//                                ObjectAnimator.ofFloat(mRecycleView, "translationX", -mRecycleView.getWidth(), 0f).setDuration(800))
//                        .start();
//            }
//        };


        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecycleView.setLayoutManager(linearLayoutManager);
//        mRecycleView.setItemAnimator(new DefaultItemAnimator());
//        mRecycleView.addItemDecoration(new DividerItemDecoration(this,
//                DividerItemDecoration.VERTICAL_LIST));
        mRecycleView.addItemDecoration(new MyRecyclerItemDivier(this));

        /**
         * 添加数据源
         */
        oldList = new ArrayList<>();
        String url_img = "https://ss0.bdstatic.com/5aV1bjqh_Q23odCf/static/superman/img/logo/bd_logo1_31bdc765.png";
        for (int i=0;i<100;i++) {
            oldList.add(new RecycleBean("姓名:"+i,url_img));
        }

        adapter = new DiffRecyclerAdapter(oldList, this);
        adapter.setMyRecycleItemClick(this);


        HeaderAndFooterWrapper headerAndFooterWrapper = new HeaderAndFooterWrapper(adapter);

        TextView textView = new TextView(this);
        textView.setText("---------------head-----------");
        textView.setTextSize(50);
        textView.setGravity(Gravity.CENTER);

        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getBaseContext(), "点击了：HeadView！" , Toast.LENGTH_SHORT).show();


            }
        });
        headerAndFooterWrapper.addHeaderView(textView);

        mRecycleView.setAdapter(headerAndFooterWrapper);







    }



    @Override
    public void mItemClick(View view, int position) {
        Toast.makeText(getBaseContext(), "点击了：" + position, Toast.LENGTH_SHORT).show();
        oldList.remove(position );
        adapter.notifyDataSetChanged();
    }

    @Override
    public void mLongClick(View view, int position) {
        Toast.makeText(getBaseContext(), "长按了：" + position, Toast.LENGTH_SHORT).show();
    }
}
