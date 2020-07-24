package com.season.mvp.model.base;

import android.content.Context;

import com.season.lib.BaseContext;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import retrofit2.Converter;
import retrofit2.Retrofit;

/**
 * Disc: 网络数据Model
 * User: SeasonAllan(451360508@qq.com)
 * Time: 2017-06-10 21:15
 */
public abstract class BaseNetModel {

    protected Context mContext;
    private Retrofit mRetrofit;

    public BaseNetModel() {
        mContext = BaseContext.sContext;
        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(getInterceptor())
                .retryOnConnectionFailure(true)
                .build();
        mRetrofit = new Retrofit.Builder()
                .client(client)
                .addConverterFactory(getConverterFactory())//解析方法
                        //.addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .baseUrl(getBaseUrl())//主机地址
                .build();
    }

    /**
     * 获取服务器请求
     * @return
     */
    protected Retrofit getHttpClient(){
        return mRetrofit;
    }

    /**
     * api的base URL.
     *
     * @return
     */
    protected abstract String getBaseUrl();

    /**
     * 请求拦截器，用于对参数进行加密或添加公用参数
     *
     * @return
     */
    protected abstract Interceptor getInterceptor();

    /**
     * 返回数据解析器，用于解密返回数据
     *
     * @return
     */
    protected abstract Converter.Factory getConverterFactory();


}
