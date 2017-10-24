package com.mytestdemo.tree_list_view;

import android.content.Context;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.mytestdemo.R;
import com.mytestdemo.tree_list_view.tree_utils.TreeHelper;
import com.mytestdemo.tree_list_view.tree_utils.TreeListAdapter;

import java.util.List;

import static android.content.ContentValues.TAG;
import static android.media.CamcorderProfile.get;

/**
 * Created by cuishuxiang on 2017/10/24.
 */

public class TreeSimpleAdapter<T> extends TreeListAdapter<T>{
    private static final String TAG = "TreeSimpleAdapter";
    public TreeSimpleAdapter(Context context, ListView mTree, List datas, int defaultLevel) throws IllegalAccessException {
        super(context, mTree, datas, defaultLevel);
    }

    @Override
    public View getConvertView(Node node, int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            convertView = mLayoutInflater.inflate(R.layout.item_tree, parent, false);

            holder = new ViewHolder();
            holder.left_img = (ImageView) convertView.findViewById(R.id.left_img);
            holder.left_txt = (TextView) convertView.findViewById(R.id.left_txt);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        if (node.getIcon() == -1) {
            holder.left_img.setVisibility(View.INVISIBLE);
        } else {
            holder.left_img.setVisibility(View.VISIBLE);
            holder.left_img.setImageResource(node.getIcon());
        }

        holder.left_txt.setText(node.getName());

        Log.d(TAG, "getConvertView: "+node.getName());
        return convertView;
    }

    /**
     * 动态插入节点
     *
     * @param position
     * @param extraString
     */
    public void addExtraNode(int position, String extraString) {
        Node node = mVisibleNodes.get(position);

        int indexof = mAllNodes.indexOf(node);

        Node extraNode = new Node(-1, node.getpId(), extraString);
        extraNode.setParent(node);
        node.getChildList().add(extraNode);
        mAllNodes.add(indexof+1,extraNode);

        mVisibleNodes = TreeHelper.filterVisibleNodes(mAllNodes);

        notifyDataSetChanged();
    }

    private class ViewHolder {
        ImageView left_img;
        TextView left_txt;
    }

}
