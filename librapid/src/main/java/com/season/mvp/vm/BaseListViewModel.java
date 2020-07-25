package com.season.mvp.vm;

import com.season.mvp.ui.BaseTLEActivity;
import com.season.mvp.ui.BaseTLEFragment;
import com.season.mvp.ui.pulltorefresh.IPull2RefreshAction;
import com.season.mvp.ui.pulltorefresh.IPull2RefreshView;
import com.season.mvp.ui.pulltorefresh.Pull2RefreshImpl;

import java.util.ArrayList;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public abstract class BaseListViewModel extends BaseViewModel implements IPull2RefreshAction {

    public BaseListViewModel(BaseTLEActivity baseTLEActivity) {
        super(baseTLEActivity);
    }

    public BaseListViewModel(BaseTLEActivity baseTLEActivity, boolean showTitle) {
        super(baseTLEActivity, showTitle);
    }

    public BaseListViewModel(BaseTLEFragment baseTLEActivity) {
        super(baseTLEActivity);
    }

    public BaseListViewModel(BaseTLEFragment baseTLEActivity, boolean showTitle) {
        super(baseTLEActivity, showTitle);
    }

    IPull2RefreshView mPull2RefreshView;
    @Override
    protected void init() {
        mPull2RefreshView = new Pull2RefreshImpl(this) {

            @Override
            public void onRefresh() {
                loadList(REFRESH);
            }

            @Override
            public void onLoadingMore() {
                loadList(MORE);
            }
        };
    }

    protected void onError(int type, String errorMessage) {
        mPull2RefreshView.onError();
    }

    protected <T> void onSuccess(int type, ArrayList<T> list, int pageSize) {
        mPull2RefreshView.onSuccess(type, list, pageSize);
    }


    public abstract void loadList(int callType);


    protected void onError2UI(String errorMessage) {
        onError2UI(-1, errorMessage);
    }

    protected void onError2UI(int type, String errorMessage) {
        getLoadingView().dismissLoadingView();
        onError(type, errorMessage);
    }

    protected abstract <T> void onResponse(int type, T result) ;

    protected <T> void onResponse2UI(int type, T result) {
        getLoadingView().dismissLoadingView();
        onResponse(type, result);
    }


    protected <T> void onResponse2UI(T result) {
        onResponse2UI(-1, result);
    }


    public class LocalObserver<T> implements Observer<T> {

        public LocalObserver(){

        }

        @Override
        public void onSubscribe(Disposable d) {
        }

        @Override
        public void onNext(T t) {
            onResponse2UI(t);
        }

        @Override
        public void onError(Throwable e) {
            onError2UI(e.getMessage());
        }

        @Override
        public void onComplete() {
        }
    }

    public class HttpCallback<T> implements Callback<T> {
        int type = -1;
        public HttpCallback(int type) {
            this.type = type;
        }

        @Override
        public void onResponse(Call<T> call, Response<T> response) {
            if (response.isSuccessful()) {
                T result = response.body();
                if (result != null) {
                    afterResponse(result);
                    onResponse2UI(type, result);
                    return;
                }
            }
            onError2UI(type, "数据错误");
        }

        protected void afterResponse(T result) {

        }

        @Override
        public void onFailure(Call<T> call, Throwable t) {
            onError2UI(type, "连接失败");
        }
    }
}
