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

public class LazyTestFragment3 extends BaseLazyFragment {

    private TextView text;
    private View view;


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Log.e("Fragment-3", "onAttach");
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e("Fragment-3", "onCreate");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_lazy_layout, container, false);
        Log.e("Fragment-3", "onCreateView");
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
        text.setText("--------------------3----------------------");

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.d("Fragment-3", "onViewCreated: ");
    }
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        Log.e("Fragment-3", "setUserVisibleHint-3"+isVisibleToUser);
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.e("Fragment-3", "onStart");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.e("Fragment-3", "onResume");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.e("Fragment-3", "onPause");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.e("Fragment-3", "onStop");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.e("Fragment-3", "onDestroyView");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.e("Fragment-3", "onDestroy");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.e("Fragment-3", "onDetach");
    }

}
