package com.mytestdemo.tree_list_view;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.mytestdemo.BaseActivity;
import com.mytestdemo.R;
import com.mytestdemo.tree_list_view.tree_utils.TreeListAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by cuishuxiang on 2017/10/22.
 * <p>
 * 利用 listview 为不同层级，设置 leftPadding 从而实现不同节点
 * <p>
 * 1，将用户数据，转换为我们需要的 node 数据
 * 使用 反射  +  注解
 * <p>
 * 2，
 */

public class TreeListActivity extends BaseActivity {

    @BindView(R.id.treeListView)
    ListView treeListView;

    private List<FileBean> fileBeanList;

    private TreeSimpleAdapter<FileBean> simpleAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_treelist);
        ButterKnife.bind(this);

        initDatas();

        try {
            simpleAdapter = new TreeSimpleAdapter<>(this, treeListView,
                    fileBeanList, 1);

            treeListView.setAdapter(simpleAdapter);

        } catch (Exception e) {
             e.printStackTrace();
        }

        initClick();
    }

    private void initClick() {
        simpleAdapter.setOnTreeClickListener(new TreeListAdapter.OnTreeClickListener() {
            @Override
            public void onClick(Node node, int position) {
                Toast.makeText(TreeListActivity.this, node.getName(), Toast.LENGTH_SHORT).show();
            }
        });

        treeListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
                final EditText editText = new EditText(TreeListActivity.this);
                new AlertDialog.Builder(TreeListActivity.this)
                        .setTitle("Add Node:")
                        .setView(editText)
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                if (TextUtils.isEmpty(editText.getText().toString())) {
                                    Toast.makeText(TreeListActivity.this, "请输入内容", Toast.LENGTH_SHORT).show();
                                    return;
                                }
                                //动态添加节点  位置，内容
                                simpleAdapter.addExtraNode(position,
                                        editText.getText().toString());
                            }
                        })
                        .setNegativeButton("取消", null).create().show();

                return true;
            }
        });

    }

    private void initDatas() {
        fileBeanList = new ArrayList<>();

        FileBean fileBean = new FileBean(1, 0, "根目录1");
        fileBeanList.add(fileBean);
        FileBean fileBean1_1 = new FileBean(2, 1, "根目录1-1");
        fileBeanList.add(fileBean1_1);
        FileBean fileBean1_2 = new FileBean(3, 1, "根目录1-2");
        fileBeanList.add(fileBean1_2);

        FileBean fileBean2 = new FileBean(4, 0, "根目录2");
        fileBeanList.add(fileBean2);
        FileBean fileBean2_1 = new FileBean(5, 4, "根目录2-1");
        fileBeanList.add(fileBean2_1);
        FileBean fileBean2_2 = new FileBean(6, 4, "根目录2-2");
        fileBeanList.add(fileBean2_2);
        FileBean fileBean2_3 = new FileBean(7, 4, "根目录2-3");
        fileBeanList.add(fileBean2_3);

        FileBean fileBean3 = new FileBean(8, 0, "根目录3");
        fileBeanList.add(fileBean3);

    }
}
