package com.season.mvp.ui;

import android.view.View;
import android.widget.ImageView;

import androidx.databinding.BaseObservable;
import androidx.databinding.BindingAdapter;

import com.season.mvp.databinding.BaseTleBindingImpl;

public class BaseImpl extends BaseObservable {

    public int visible = View.VISIBLE;
    private BaseTleBindingImpl baseTleBinding;
    public BaseImpl(BaseTleBindingImpl baseTleBinding) {
        this.baseTleBinding = baseTleBinding;
    }

    @BindingAdapter({"android:src"})
    public static void setImageViewResource(ImageView imageView, int resource) {
        imageView.setImageResource(resource);
    }



    protected void checkLoadingNull() {
        if (baseTleBinding.commonLoading.isInflated()){

        }else{
            baseTleBinding.commonLoading.getViewStub().inflate();
        }

    }

    protected void checkTitleNull() {
        if (baseTleBinding.commonTop.isInflated()){

        }else{
            baseTleBinding.commonTop.getViewStub().inflate();
        }

    }

    protected void checkCommonNull(){
        if (baseTleBinding.commonEmpty.isInflated()){

        }else{
            baseTleBinding.commonEmpty.getViewStub().inflate();
        }
    }

}
