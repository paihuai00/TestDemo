package com.mytestdemo.bottom_navigation;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.mytestdemo.R;
import com.mytestdemo.view_digital_loading.GlideApp;
import com.unnamed.b.atv.model.TreeNode;

/**
 * @Created by cuishuxiang
 * @date 2018/1/16.
 */

public class TreeViewHolder extends TreeNode.BaseNodeViewHolder<TreeViewHolder.IconTreeItem>{
    private static final String TAG = "TreeViewHolder";
    private ImageView left_img;
    private TextView textView;


    public TreeViewHolder(Context context) {
        super(context);
    }

    @Override
    public View createNodeView(TreeNode node, IconTreeItem value) {
        View view = null;
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.item_tree_view, null, false);
            textView = (TextView) view.findViewById(R.id.textView);
            left_img = (ImageView) view.findViewById(R.id.left_img);
        }

//        GlideApp.with(context)
//                .load(value.icon)
//                .error(R.mipmap.ic_launcher)
//                .into(left_img);

        textView.setText(value.text);

        return view;
    }

    @Override
    public void toggle(boolean active) {
        super.toggle(active);
        Log.d(TAG, "toggle: active " + active);
    }

    public static class IconTreeItem {
        public int icon;
        public String text;

        public IconTreeItem(int icon, String text) {
            this.icon = icon;
            this.text = text;
        }
    }
}
