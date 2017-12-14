package com.mytestdemo.choose_num;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.mytestdemo.R;

import java.util.List;

/**
 * @author cuishuxiang
 * @date 2017/12/14.
 */

public class ChooseNumAdapter extends BaseAdapter {
    private Context context;
    private List<Integer> intLists;

    public ChooseNumAdapter(Context context, List<Integer> intLists) {
        this.context = context;
        this.intLists = intLists;
    }
    @Override
    public int getCount() {
        return intLists.size();
    }

    @Override
    public Object getItem(int position) {
        return intLists.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_choose_num,
                    parent, false);

            viewHolder = new ViewHolder();
            viewHolder.num_txt = (TextView) convertView.findViewById(R.id.num_txt);
            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.num_txt.setText("" + intLists.get(position));

        return convertView;
    }

    class ViewHolder{
        TextView num_txt;
    }
}
