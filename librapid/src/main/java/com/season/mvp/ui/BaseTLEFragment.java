package com.season.mvp.ui;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.season.lib.ui.BaseRecycleAdapter;
import com.season.lib.util.ToastUtil;
import com.season.mvp.R;
import com.season.mvp.databinding.BaseTleBindingImpl;
import com.season.mvp.ui.empty.EmptyImpl;
import com.season.mvp.ui.empty.IEmptyAction;
import com.season.mvp.ui.empty.IEmptyView;
import com.season.mvp.ui.loading.ILoadingView;
import com.season.mvp.ui.loading.LoadingImpl;
import com.season.mvp.ui.titlebar.ITitleBar;
import com.season.mvp.ui.titlebar.ITitleBarAction;
import com.season.mvp.ui.titlebar.TitleBarImpl;

import static com.season.lib.BaseContext.showToast;

/**
 * Disc:Activity 基类
 * User: SeasonAllan(451360508@qq.com)
 * Time: 2017-06-10 14:37
 */
public abstract class BaseTLEFragment extends Fragment implements ITitleBarAction, IEmptyAction, IView {

    private ITitleBar mTitleBar;
    private ILoadingView mLoadingView;
    private IEmptyView mEmptyView;

    boolean viewSet = false;

    private View mView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        BaseTleBindingImpl baseTleBinding = DataBindingUtil.inflate(inflater, R.layout.base_tle, container, false);
        viewSet = true;
        mView = baseTleBinding.getRoot();
        mEmptyView = new EmptyImpl(this, baseTleBinding);
        mLoadingView = new LoadingImpl(baseTleBinding);

        if (isTopTileEnable()){
            mTitleBar = new TitleBarImpl(this , baseTleBinding);
        }
        ViewGroup contentView = mView.findViewById(R.id.main_view);
        View mainView = inflater.inflate(getLayoutId(), container, false);
        contentView.addView(mainView);

        onViewCreated();
        return mView;
    }

    protected void startActivity(Class cls){
        Intent intent = new Intent();
        intent.setClass(getContext(), cls);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    public <T extends View> T findViewById(int id) {
        return mView.findViewById(id);
    }

    @Override
    public void finish() {
        getActivity().onBackPressed();
    }

    /**
     * 选择布局
     * @return
     */
    protected abstract int getLayoutId();

    /**
     * View的创建和事件
     */
    protected abstract void onViewCreated();


    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    /**
     * 顶部标题栏是否显示
     * @return
     */
    protected boolean isTopTileEnable(){
        return true;
    }

    /**
     * 顶部标题控制栏
     * @return
     */
    public ITitleBar getTitleBar(){
        return mTitleBar;
    }

    @Override
    public IEmptyView getEmptyView() {
        return mEmptyView;
    }

    @Override
    public void onEmptyViewClick() {
        ToastUtil.showToast("click");
    }

    @Override
    public <T> void onResponse(int type, T result) {

    }

    @Override
    public BaseRecycleAdapter getAdapter() {
        return null;
    }

    @Override
    public void onError(int type, String errorMessage) {

    }

    /**
     * 控制加载中的显示与消失
     * @return
     */
    public ILoadingView getLoadingView(){
        return mLoadingView;
    }


}
