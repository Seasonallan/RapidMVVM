package com.season.example.vm;

import android.os.Handler;

import androidx.databinding.BaseObservable;

import com.season.example.databinding.ActivityAboutBinding;


public class AboutViewModel extends BaseObservable {

    public String desc = "cd";
    ActivityAboutBinding aboutBinding;
    public AboutViewModel(ActivityAboutBinding aboutBinding){
        this.aboutBinding = aboutBinding;
        aboutBinding.setAbout(this);
    }

    public void load(){

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                desc = "after";
                notifyChange();
            }
        }, 2921);
    }

}
