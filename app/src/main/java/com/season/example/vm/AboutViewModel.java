package com.season.example.vm;

import android.os.Handler;
import android.view.View;

import com.season.lib.util.LogUtil;
import com.season.lib.util.ToastUtil;
import com.season.mvp.ui.BaseTLEActivity;
import com.season.mvp.vm.BaseViewModel;


public class AboutViewModel extends BaseViewModel {

    public String desc = "cd";

    public AboutViewModel(final BaseTLEActivity baseTLEActivity){
        super(baseTLEActivity);
    }

    public void load(){
        getLoadingView().showLoadingView();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                getLoadingView().dismissLoadingView();
                getEmptyView().showEmptyView();
                getEmptyView().setOnEmptyClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ToastUtil.showToast("10086");
                        getEmptyView().dismissEmptyView();
                        desc = "fill";
                        notifyChange();
                    }
                });
                desc = "";
                notifyChange();
            }
        }, 5921);
    }

}
