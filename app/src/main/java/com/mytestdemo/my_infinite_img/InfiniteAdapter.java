package com.mytestdemo.my_infinite_img;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.mytestdemo.R;

/**
 * Created by cuishuxiang on 2017/6/16.
 */

public class InfiniteAdapter extends BaseAdapter {
    private int[] imgs;
    private Context context;

    public InfiniteAdapter(int[] imgs, Context context) {
        this.imgs = imgs;
        this.context = context;
    }

    @Override
    public int getCount() {
        return imgs.length;
    }

    @Override
    public Object getItem(int i) {
        return imgs[i];
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout
                    .item_card, parent, false);
        }
        convertView.setBackgroundResource(imgs[position]);
        return convertView;
    }
}
