package com.season.example.ui.activity;

import android.os.Bundle;
import com.season.example.R;
import com.season.example.databinding.ActivityAboutBindingImpl;
import com.season.example.vm.AboutViewModel;
import com.season.mvp.ui.BaseTLEActivity;

/**
 * Disc: 关于页面
 * User: SeasonAllan(451360508@qq.com)
 * Time: 2017-06-10 16:00
 */
public class AboutActivity extends BaseTLEActivity<ActivityAboutBindingImpl> {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
    }

    @Override
    protected void bindViewModel(ActivityAboutBindingImpl binding){
        AboutViewModel aboutViewModel = new AboutViewModel(this);
        binding.setAbout(aboutViewModel);

        aboutViewModel.getTitleBar().setTopTile("About");
        aboutViewModel.getTitleBar().enableLeftButton();
        aboutViewModel.load();
    }

}
