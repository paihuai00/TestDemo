package com.mytestdemo.tree_list_view.tree_utils;

import android.content.Context;
import android.content.res.Resources;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;

import com.bumptech.glide.load.engine.Resource;
import com.mytestdemo.tree_list_view.Node;
import com.mytestdemo.tree_list_view.tree_utils.TreeHelper;

import java.util.List;

/**
 * Created by cuishuxiang on 2017/10/24.
 *
 * 此处设置抽象类，上面的方法都是公用的  只需要  getView 该方法
 */

public abstract class TreeListAdapter<T> extends BaseAdapter{
    private static final String TAG = "TreeListAdapter";
    protected Context mContext;
    protected List<Node> mAllNodes;
    protected List<Node> mVisibleNodes;

    protected LayoutInflater mLayoutInflater;

    protected ListView mTreeListView;

    protected OnTreeClickListener onTreeClickListener;

    /**
     * 设置点击回调
     * @param onTreeClickListener
     */
    public void setOnTreeClickListener(OnTreeClickListener onTreeClickListener) {
        this.onTreeClickListener = onTreeClickListener;
    }

    public TreeListAdapter(Context context, ListView mTree, List<T> datas, int defaultLevel) throws IllegalAccessException {
        mContext = context;
        mAllNodes = TreeHelper.getSortNodes(datas);
        mVisibleNodes = TreeHelper.filterVisibleNodes(mAllNodes);
        mLayoutInflater = LayoutInflater.from(context);
        mTreeListView = mTree;

        mTreeListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                expendOrCollapse(position);

                if (onTreeClickListener != null) {
                    onTreeClickListener.onClick(mVisibleNodes.get(position), position);
                }
            }
        });
    }

    /**
     * 点击收缩 或者 展开
     * @param position
     */
    private void expendOrCollapse(int position) {
        Node node = mVisibleNodes.get(position);

        if (node != null) {
            if (node.isLeaf()) {
                return;
            }

            node.setExpend(!node.isExpend());

            mVisibleNodes = TreeHelper.filterVisibleNodes(mAllNodes);

            notifyDataSetChanged();
        }

    }

    @Override
    public int getCount() {
        Log.d(TAG, "getCount: mVisibleNodes.size()= " + mVisibleNodes.size());
        return mVisibleNodes.size();
    }

    @Override
    public Object getItem(int position) {
        return mVisibleNodes.get(position);
    }

    @Override
    public long getItemId(int position) {
        return mVisibleNodes.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Node node = mVisibleNodes.get(position);

        convertView = getConvertView(node, position, convertView, parent);

        //暂时设定 30
        convertView.setPadding(dp2px(node.getLevel() * 30, mContext), 3, 3, 3);

        return convertView;
    }



    /**
     * 定义抽象方法，让getView 调用，用户只需要 重写该getView
     * @param node
     * @param position
     * @param convertView
     * @param parent
     * @return
     */
    public abstract View getConvertView(Node node, int position,
                                        View convertView, ViewGroup parent);

    public interface OnTreeClickListener {
        void onClick(Node node, int position);
    }

    public int dp2px(float dp,Context context) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp,
                context.getResources().getDisplayMetrics());
    }
}
