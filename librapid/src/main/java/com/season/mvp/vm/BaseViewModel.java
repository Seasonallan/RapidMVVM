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

import java.lang.ref.WeakReference;

public class BaseViewModel extends BaseObservable {

    private ITitleBar mTitleBar;
    private ILoadingView mLoadingView;
    private IEmptyView mEmptyView;

    public void release(){
        baseTleBinding.unbind();
        baseTleBinding = null;
    }

    public BaseViewModel(final BaseTLEActivity baseTLEActivity){
        this(baseTLEActivity, true);
    }

    private BaseTleBindingImpl baseTleBinding;
    public BaseViewModel(BaseTLEActivity baseTLEActivity, boolean showTitle){
        final WeakReference<BaseTLEActivity> activityWeakReference = new WeakReference<>(baseTLEActivity);
        baseTleBinding = baseTLEActivity.baseTleBinding;
        mEmptyView = new EmptyImpl(baseTleBinding);
        mLoadingView = new LoadingImpl(baseTleBinding);
        if (showTitle){
            mTitleBar = new TitleBarImpl(baseTleBinding);
            mTitleBar.setOnTopLeftClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (activityWeakReference.get() != null)
                        activityWeakReference.get().finish();
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
        final WeakReference<BaseTLEFragment> activityWeakReference = new WeakReference<>(baseTLEActivity);
        baseTleBinding = baseTLEActivity.baseTleBinding;
        mEmptyView = new EmptyImpl(baseTleBinding);
        mLoadingView = new LoadingImpl(baseTleBinding);
        if (showTitle){
            mTitleBar = new TitleBarImpl(baseTleBinding);
            mTitleBar.setOnTopLeftClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (activityWeakReference.get() != null)
                        activityWeakReference.get().getActivity().finish();
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
