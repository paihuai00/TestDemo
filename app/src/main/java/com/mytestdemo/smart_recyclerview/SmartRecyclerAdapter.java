package com.mytestdemo.smart_recyclerview;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.mytestdemo.R;

import java.util.List;

/**
 * Created by cuishuxiang on 2017/3/31.
 *
 * diff  recyclerView adapter
 */

public class SmartRecyclerAdapter extends RecyclerView.Adapter<SmartRecyclerAdapter.ViewHolder> {
    private List<String> list;
    private Context context;

    public SmartRecyclerAdapter(List<String> list, Context context) {
        this.list = list;
        this.context = context;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_diff_recyclerview, null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        holder.item_txt.setText(list.get(position));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        private TextView item_txt;
        private ImageView item_img;

        public ViewHolder(View itemView) {
            super(itemView);
            item_txt = (TextView) itemView.findViewById(R.id.item_txt);
            item_img = (ImageView) itemView.findViewById(R.id.right_img);

        }

    }

}
