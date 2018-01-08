package com.mytestdemo.scroll_hide_toolbar;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.mytestdemo.R;

import java.util.List;

/**
 * @Created by cuishuxiang
 * @date 2018/1/8.
 */

public class HideToolbarRvAdapter extends BaseQuickAdapter<String,BaseViewHolder> {

    public HideToolbarRvAdapter(int layoutResId, @Nullable List<String> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
        helper.setText(R.id.item_txt, item);
    }
}
