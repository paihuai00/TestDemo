package com.mytestdemo.lazy_fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mytestdemo.R;


/**
 * Created by cuishuxiang on 2017/3/30.
 */

public class LazyTestFragment4 extends BaseLazyFragment {

    private TextView text;
    private View view;


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Log.e("Fragment-4", "onAttach");
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e("Fragment-4", "onCreate");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_lazy_layout, container, false);
        Log.e("Fragment4", "onCreateView");
        isPrepared = true;
        loadData();
        return view;
    }

    @Override
    protected void loadData() {
        if (view == null) {
            return;
        }
        text = (TextView) view.findViewById(R.id.text);
        text.setText("--------------------4---------------------");

    }
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        Log.e("Fragment-4", "setUserVisibleHint-3"+isVisibleToUser);
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.e("Fragment-4", "onStart");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.e("Fragment-4", "onResume");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.e("Fragment-4", "onPause");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.e("Fragment-4", "onStop");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.e("Fragment-4", "onDestroyView");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.e("Fragment-4", "onDestroy");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.e("Fragment-4", "onDetach");
    }

}
