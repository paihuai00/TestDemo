package com.mytestdemo.tree_list_view;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.mytestdemo.BaseActivity;
import com.mytestdemo.R;

/**
 * Created by cuishuxiang on 2017/10/22.
 *
 * 利用 listview 为不同层级，设置 leftPadding 从而实现不同节点
 *
 *  1，将用户数据，转换为我们需要的 node 数据
 *     使用 反射  +  注解
 *
 *  2，
 */

public class TreeListActivity extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_treelist);
    }
}
