package com.mytestdemo.recycleview_normal;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.mytestdemo.R;
import com.mytestdemo.recycleview_diffutil.RecycleBean;

import java.util.List;

/**
 * Created by cuishuxiang on 2017/3/31.
 *
 * diff  recyclerView adapter
 */

public class NormalRecyclerAdapter extends RecyclerView.Adapter<NormalRecyclerAdapter.ViewHolder> {
    private List<RecycleBean> list;
    private Context context;

    private MyRecycleItemClick myRecycleItemClick;

    public NormalRecyclerAdapter(List<RecycleBean> list, Context context) {
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
            myRecycleItemClick.mItemClick(view,getPosition());
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
