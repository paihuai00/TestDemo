package com.mytestdemo.my_gridview;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.TextView;

import com.mytestdemo.R;

/**
 * Created by Administrator on 2017/4/7.
 */

public class FragmentGrid3 extends Fragment {
    private GridView gridview = null;
    private TextView my_txt;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_gridview_layout, container, false);
        my_txt = (TextView) view.findViewById(R.id.my_txt);
        my_txt.setText("333333333333333");
        gridview = (GridView)view.findViewById(R.id.gridView);
        return view;
    }

}
