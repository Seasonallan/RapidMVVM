package com.season.mvp.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.fragment.app.Fragment;
import com.season.mvp.R;
import com.season.mvp.databinding.BaseTleBindingImpl;
/**
 * Disc:Activity 基类
 * User: SeasonAllan(451360508@qq.com)
 * Time: 2017-06-10 14:37
 */
public abstract class BaseTLEFragment<T extends ViewDataBinding> extends Fragment {

    boolean viewSet = false;

    private View mView;
    public BaseTleBindingImpl baseTleBinding;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        baseTleBinding = DataBindingUtil.inflate(inflater, R.layout.base_tle, container, false);
        viewSet = true;
        mView = baseTleBinding.getRoot();

        ViewGroup contentView = mView.findViewById(R.id.main_view);
        T binding = inflate(getLayoutId());
        contentView.addView(binding.getRoot());

        bindViewModel(binding);
        return mView;
    }

    protected T inflate(int layoutId) {
        return DataBindingUtil.inflate(LayoutInflater.from(getContext()), layoutId, null, false);
    }


    /**
     * 绑定VM
     *
     * @param binding
     */
    protected abstract void bindViewModel(T binding);

    /**
     * 选择布局
     * @return
     */
    protected abstract int getLayoutId();




    protected void startActivity(Class cls){
        Intent intent = new Intent();
        intent.setClass(getContext(), cls);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    public View findViewById(int id) {
        return mView.findViewById(id);
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
    }


}
