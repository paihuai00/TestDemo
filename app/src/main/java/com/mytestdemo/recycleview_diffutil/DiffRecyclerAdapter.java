package com.mytestdemo.recycleview_diffutil;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.mytestdemo.R;

import java.util.List;

/**
 * Created by cuishuxiang on 2017/3/31.
 *
 * diff  recyclerView adapter
 */

public class DiffRecyclerAdapter extends RecyclerView.Adapter<DiffRecyclerAdapter.ViewHolder> {
    private List<RecycleBean> list;
    private Context context;

    private MyRecycleItemClick myRecycleItemClick;

    public DiffRecyclerAdapter(List<RecycleBean> list, Context context) {
        this.list = list;
        this.context = context;
    }

    public void setData(List<RecycleBean> list) {
        this.list = list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_diff_recyclerview, null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Glide.with(context)
                .load(list.get(position).getImg_url())
                .error(R.mipmap.ic_launcher)
                .into(holder.item_img);

        holder.item_txt.setText(list.get(position).getName());
    }
    //DiffUtil  需要重写该方法


    @Override
    public void onBindViewHolder(ViewHolder holder, int position, List<Object> payloads) {
        super.onBindViewHolder(holder, position, payloads);
        //判断数据更改是否为空，说明是新增的，直接去绑定数据
        if (payloads == null || payloads.isEmpty()) {
            onBindViewHolder(holder,position);
            Log.i("TAG", "position:" + position + " payloads is empty");
            return;
        }

        if (!(holder instanceof ViewHolder)) {
            return;
        }

        //如果不为空，说明有部分数据发生了更改，那么只要根据数据去更新变更的UI即可
        ViewHolder viewHolder = (ViewHolder) holder;
        Bundle bundle = (Bundle) payloads.get(0);
        String content = bundle.getString("content");
        viewHolder.item_txt.setText(content);
        Log.i("TAG", "position:" + position + " payloads is not empty");
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener,View.OnLongClickListener{
        private TextView item_txt;
        private ImageView item_img;

        public ViewHolder(View itemView) {
            super(itemView);
            item_txt = (TextView) itemView.findViewById(R.id.item_txt);
            item_img = (ImageView) itemView.findViewById(R.id.right_img);

            itemView.setOnClickListener(this);
            itemView.setOnLongClickListener(this);

        }

        @Override
        public void onClick(View view) {
            myRecycleItemClick.mItemClick(view,getAdapterPosition());
        }

        @Override
        public boolean onLongClick(View view) {
            myRecycleItemClick.mLongClick(view, getPosition());
            return true;
        }
    }

    public void setMyRecycleItemClick(MyRecycleItemClick myRecycleItemClick) {
        this.myRecycleItemClick = myRecycleItemClick;
    }

    public interface MyRecycleItemClick{
        public void mItemClick(View view, int position);
        public void mLongClick(View view, int position);
    }
}
