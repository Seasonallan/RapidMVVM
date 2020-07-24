package com.season.example;

import android.app.Application;

import com.season.lib.BaseContext;

/**
 * Disc:
 * User: SeasonAllan(451360508@qq.com)
 * Time: 2017-06-11 13:12
 */
public class ExampleApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        BaseContext.init(this);

    }
}
