package com.season.example.model.net.kuaifang;

import com.season.example.entry.BaseEntry;
import com.season.example.entry.ClientKey;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Disc:
 * User: SeasonAllan(451360508@qq.com)
 * Time: 2017-06-10 21:19
 */
public interface INetRequestKey {

    /**
     * 获取网络通讯密钥
     * @return Call<BaseEntry<ClientKey>>
     */
    @GET("wskey/get")
    Call<BaseEntry<ClientKey>> getClientKey(@Query("imei") String imei, @Query("name") String name, @Query("app_version_name") String app_version_name);



}
