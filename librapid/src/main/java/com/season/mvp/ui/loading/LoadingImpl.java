package com.season.mvp.ui.loading;

import android.view.View;
import com.season.mvp.databinding.BaseTleBindingImpl;
import com.season.mvp.ui.BaseImpl;

/**
 * Disc: 加载中状态组件
 * User: SeasonAllan(451360508@qq.com)
 * Time: 2017-06-11 00:00
 */
public class LoadingImpl extends BaseImpl implements ILoadingView {

    public String desc = "加载中...";

    public LoadingImpl(BaseTleBindingImpl baseTleBinding) {
        super(baseTleBinding);
        baseTleBinding.setLoading(this);
    }

    @Override
    public void showLoadingView() {
        checkLoadingNull();
        visible = View.VISIBLE;
        notifyChange();
    }


    @Override
    public void showLoadingView(String txt) {
        desc = txt;
        showLoadingView();
    }

    @Override
    public void showLoadingView(int color) {
        showLoadingView();
    }

    @Override
    public void dismissLoadingView() {
        checkLoadingNull();
        visible = View.GONE;
        notifyChange();
    }
}
