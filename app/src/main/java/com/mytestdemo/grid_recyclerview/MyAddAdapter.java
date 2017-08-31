package com.mytestdemo.grid_recyclerview;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mytestdemo.R;

import java.util.List;

/**
 * Created by TJXF-JY-WEIDINGQIANG on 2017/4/3.
 */

public class MyAddAdapter extends RecyclerView.Adapter<MyAddAdapter.MyViewHolder> {
    private Context context;
    private List<String> list;

    public MyAddAdapter(Context context, List<String> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_add_recycler, parent, false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.bottom_txt.setText(list.get(position));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    class MyViewHolder extends RecyclerView.ViewHolder{
        private TextView bottom_txt;
        public MyViewHolder(View itemView) {
            super(itemView);
            bottom_txt = (TextView) itemView.findViewById(R.id.bottom_txt);
        }
    }
}
