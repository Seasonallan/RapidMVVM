package com.season.mvp.ui.empty;

import android.view.View;
import com.season.mvp.R;
import com.season.mvp.databinding.BaseTleBindingImpl;
import com.season.mvp.ui.BaseImpl;

/**
 * Disc: 加载失败或为空组件
 * User: SeasonAllan(451360508@qq.com)
 * Time: 2017-06-11 00:00
 */
public class EmptyImpl extends BaseImpl implements IEmptyView {

    public String desc = "点击屏幕，重新加载";
    public int resourceId = R.mipmap.error_touch;

    public void onClick(){
        if (listener != null){
            listener.onClick(null);
        }
    }

    public EmptyImpl(BaseTleBindingImpl baseTleBinding) {
        super(baseTleBinding);
        baseTleBinding.setEmpty(this);
    }

    private View.OnClickListener listener;
    @Override
    public void setOnEmptyClickListener(View.OnClickListener listener){
        this.listener = listener;
    }

    @Override
    public void showEmptyView() {
        checkCommonNull();
        visible = View.VISIBLE;
        notifyChange();
        //notifyChange();
    }


    @Override
    public void dismissEmptyView() {
        checkCommonNull();
        visible = View.GONE;
        notifyChange();
    }

    @Override
    public void showEmptyView(String txt) {
        showEmptyView(-1, txt);
    }

    @Override
    public void showEmptyView(int id, String txt) {
        showEmptyView();
        if (id > 0) {
            resourceId = id;
        }
        if (txt != null) {
            desc = txt;
        }
        notifyChange();
    }

}
