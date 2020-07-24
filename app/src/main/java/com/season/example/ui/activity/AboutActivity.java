package com.season.example.ui.activity;

import android.os.Bundle;
import android.view.LayoutInflater;

import androidx.databinding.DataBindingUtil;

import com.season.example.R;
import com.season.example.databinding.ActivityAboutBinding;
import com.season.example.vm.AboutViewModel;
import com.season.mvp.ui.BaseTLEActivity;

/**
 * Disc: 关于页面
 * User: SeasonAllan(451360508@qq.com)
 * Time: 2017-06-10 16:00
 */
public class AboutActivity extends BaseTLEActivity {

    private AboutViewModel aboutViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getTitleBar().setTopTile("About");
        getTitleBar().enableLeftButton();

        ActivityAboutBinding binding = DataBindingUtil.inflate(LayoutInflater.from(this), R.layout.activity_about, null, false);
        setContentView(binding.getRoot());
        aboutViewModel = new AboutViewModel(binding);

        aboutViewModel.load();
    }

}
