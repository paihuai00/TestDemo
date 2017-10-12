package com.mytestdemo.recyclerview_brvah;

import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.mytestdemo.R;

import java.util.List;

/**
 * Created by cuishuxiang on 2017/9/25.
 */

public class BrathAdapter extends BaseQuickAdapter<String,BaseViewHolder> {
    public BrathAdapter(@LayoutRes int layoutResId, @Nullable List<String> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
        helper.setText(R.id.item_txt, item);
    }
}
