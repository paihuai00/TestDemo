package com.mytestdemo.my_navigation;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.mytestdemo.R;

import java.util.List;

/**
 * Created by cuishuxiang on 2017/4/26.
 */

public class MyNaviAdapter extends BaseAdapter {
    private Context context;
    private List<ContentModel> contentModels;

    public MyNaviAdapter(Context context, List<ContentModel> contentModels) {
        this.context = context;
        this.contentModels = contentModels;
    }

    @Override
    public int getCount() {
        return contentModels.size();
    }

    @Override
    public Object getItem(int i) {
        return contentModels.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder = null;
        if (holder == null) {
            holder = new ViewHolder();
            view= LayoutInflater.from(context).inflate(R.layout.item_navigation, null);
            holder.navigation_img = (ImageView) view.findViewById(R.id.navigation_img);
            holder.navigation_txt = (TextView) view.findViewById(R.id.navigation_txt);
            view.setTag(holder);
        }else {
            holder = (ViewHolder) view.getTag();

        }
        holder.navigation_img.setImageResource(contentModels.get(i).getResId());
        holder.navigation_txt.setText(contentModels.get(i).getName());
        return view;
    }
    class ViewHolder{
        ImageView navigation_img;
        TextView navigation_txt;
    }
}
