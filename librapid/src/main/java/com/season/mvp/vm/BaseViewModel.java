package com.season.mvp.vm;

import android.view.View;

import androidx.databinding.BaseObservable;
import com.season.mvp.databinding.BaseTleBindingImpl;
import com.season.mvp.ui.BaseTLEActivity;
import com.season.mvp.ui.BaseTLEFragment;
import com.season.mvp.ui.empty.EmptyImpl;
import com.season.mvp.ui.empty.IEmptyView;
import com.season.mvp.ui.loading.ILoadingView;
import com.season.mvp.ui.loading.LoadingImpl;
import com.season.mvp.ui.titlebar.ITitleBar;
import com.season.mvp.ui.titlebar.TitleBarImpl;

public class BaseViewModel extends BaseObservable {

    private ITitleBar mTitleBar;
    private ILoadingView mLoadingView;
    private IEmptyView mEmptyView;

    public BaseViewModel(final BaseTLEActivity baseTLEActivity){
        this(baseTLEActivity, true);
    }

    private BaseTleBindingImpl baseTleBinding;
    public BaseViewModel(final BaseTLEActivity baseTLEActivity, boolean showTitle){
        baseTleBinding = baseTLEActivity.baseTleBinding;
        mEmptyView = new EmptyImpl(baseTleBinding);
        mLoadingView = new LoadingImpl(baseTleBinding);
        if (showTitle){
            mTitleBar = new TitleBarImpl(baseTleBinding);
            mTitleBar.setOnTopLeftClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    baseTLEActivity.finish();
                }
            });
        }
        init();
    }

    protected void init(){

    }

    public View findViewById(int id) {
        return baseTleBinding.mainView.findViewById(id);
    }

    public BaseViewModel(final BaseTLEFragment baseTLEActivity){
        this(baseTLEActivity, true);
    }

    public BaseViewModel(final BaseTLEFragment baseTLEActivity, boolean showTitle){
        baseTleBinding = baseTLEActivity.baseTleBinding;
        mEmptyView = new EmptyImpl(baseTleBinding);
        mLoadingView = new LoadingImpl(baseTleBinding);
        if (showTitle){
            mTitleBar = new TitleBarImpl(baseTleBinding);
            mTitleBar.setOnTopLeftClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    baseTLEActivity.getActivity().finish();
                }
            });
        }
        init();
    }



    /**
     * 顶部标题控制栏
     * @return
     */
    public ITitleBar getTitleBar(){
        return mTitleBar;
    }

    public IEmptyView getEmptyView() {
        return mEmptyView;
    }

    /**
     * 控制加载中的显示与消失
     * @return
     */
    public ILoadingView getLoadingView(){
        return mLoadingView;
    }


}
