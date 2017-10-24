package com.mytestdemo.mvp_demo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.View;

/**
 * Created by cuishuxiang on 2017/10/19.
 *
 * 用Fragment来展示View层，Activity也是类似这样写
 */

public abstract class BaseMvpFragment<P extends IBasePresenter> extends Fragment
        implements IBaseView{

    protected P presenter;

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        presenter=createPresenter();

        presenter.attach(this);
    }

    /**
     * s
     * @return
     */
    abstract P createPresenter();

    @Override
    public void onDestroy() {
        presenter.detach();
        super.onDestroy();
    }
}
