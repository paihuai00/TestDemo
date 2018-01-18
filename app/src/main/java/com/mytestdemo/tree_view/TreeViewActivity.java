package com.mytestdemo.tree_view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.mytestdemo.BaseActivity;
import com.mytestdemo.R;
import com.mytestdemo.bottom_navigation.TreeViewHolder;
import com.unnamed.b.atv.model.TreeNode;
import com.unnamed.b.atv.view.AndroidTreeView;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @Created by cuishuxiang
 * @date 2018/1/16.
 * <p>
 * https://github.com/bmelnychuk/AndroidTreeView
 */

public class TreeViewActivity extends BaseActivity {
    private static final String TAG = "TreeViewActivity";
    AndroidTreeView mAndroidTreeView;
    TreeNode root;
    @BindView(R.id.root_view)
    RelativeLayout mRootView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tree_view);
        ButterKnife.bind(this);
        root = TreeNode.root();

        buildTree();


        mAndroidTreeView = new AndroidTreeView(this, root);
        mAndroidTreeView.setDefaultAnimation(true);
        mAndroidTreeView.setDefaultContainerStyle(R.style.TreeNodeStyle);
//        mAndroidTreeView.setDefaultContainerStyle(R.style.TreeNodeStyleDivided, true);
        View view = mAndroidTreeView.getView();
        view.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));

        mRootView.addView(view);

        mAndroidTreeView.expandAll();

        mAndroidTreeView.setDefaultNodeClickListener(new TreeNode.TreeNodeClickListener() {
            @Override
            public void onClick(TreeNode node, Object value) {
                Toast.makeText(getBaseContext(), "点击了 " + node.getId(), Toast.LENGTH_SHORT).show();

                Log.d(TAG, "onClick: "+node.getId());
            }
        });
    }

    private void buildTree() {
        for (int i = 0; i < 15; i++) {
            TreeNode child0_1 = new TreeNode(
                    new TreeViewHolder.IconTreeItem(R.drawable.ic_arrow_right, "child 0" + i + " _ " + i))
                    .setViewHolder(new TreeViewHolder(this));
            for (int j = 0; j < 3; j++) {
                TreeNode child1_1 = new TreeNode(
                        new TreeViewHolder.IconTreeItem(R.drawable.ic_arrow_right, "child 1" + i + " _ " + i))
                        .setViewHolder(new TreeViewHolder(this));

                for (int k = 0; k < 2; k++) {
                    TreeNode child2_1 = new TreeNode(
                            new TreeViewHolder.IconTreeItem(R.drawable.ic_arrow_right, "child 2" + i + " _ " + i))
                            .setViewHolder(new TreeViewHolder(this));
                    child1_1.addChild(child2_1);
                }
                child0_1.addChild(child1_1);
            }
            root.addChild(child0_1);
        }
    }


}
