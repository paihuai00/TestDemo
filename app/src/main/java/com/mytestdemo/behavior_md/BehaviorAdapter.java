package com.mytestdemo.behavior_md;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mytestdemo.R;

import java.util.List;

/**
 * Created by cuishuxiang on 2017/4/3.
 */

public class BehaviorAdapter extends RecyclerView.Adapter<BehaviorAdapter.MyViewHolder> {
    private Context context;
    private List<String> list;

    public BehaviorAdapter(Context context, List<String> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_simple_string, parent, false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.middle_txt.setText(list.get(position));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    class MyViewHolder extends RecyclerView.ViewHolder{
        private TextView middle_txt;
        public MyViewHolder(View itemView) {
            super(itemView);
            middle_txt = (TextView) itemView.findViewById(R.id.middle_txt);
        }
    }
}
