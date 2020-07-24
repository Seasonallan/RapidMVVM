package com.season.mvp.ui.pulltorefresh;

import java.util.List;

/**
 * Disc: 下拉刷新组件
 * User: SeasonAllan(451360508@qq.com)
 * Time: 2017-06-12 02:14
 */
public interface IPull2RefreshView<T>{

    public static final int CREATE = 0x9;
    public static final int REFRESH = 0x10;
    public static final int MORE = 0x11;
    public static final int GET_KEY = 0x12;

    /**
     * 加载成功
     * @param type
     * @param result
     */
    void onSuccess(int type, List<T> result, int pageSize);

    /**
     * 加载失败
     */
    void onError();
}
