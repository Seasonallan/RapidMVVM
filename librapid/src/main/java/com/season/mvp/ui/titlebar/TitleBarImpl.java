package com.season.mvp.ui.titlebar;

import android.view.View;
import com.season.mvp.R;
import com.season.mvp.databinding.BaseTleBindingImpl;
import com.season.mvp.ui.BaseImpl;

/**
 * Disc: 通用标题栏组件
 * User: SeasonAllan(451360508@qq.com)
 * Time: 2017-06-10 16:19
 */
public class TitleBarImpl extends BaseImpl implements ITitleBar {

    public TitleBarImpl(BaseTleBindingImpl baseTleBinding) {
        super(baseTleBinding);
        baseTleBinding.setTitlebar(this);
        checkTitleNull();
    }


    @Override
    public void setTopTile(String title) {
        setTopTile(title, null);
    }

    @Override
    public void setTopTile(String title, View.OnClickListener listener) {
        this.title = title;
        if (listener != null)
            titleClickListener = listener;
        notifyChange();
    }

    public String title = "";
    private View.OnClickListener titleClickListener;
    public void onTitleClick(){
        if (titleClickListener != null){
            titleClickListener.onClick(null);
        }
    }

    public void onTopLeftClick(){
        if (topLeftClickListener != null){
            topLeftClickListener.onClick(null);
        }
    }

    @Override
    public void setOnTopLeftClickListener(View.OnClickListener listener){
        this.topLeftClickListener = listener;
    }
    private View.OnClickListener topLeftClickListener;

    public int topLeftVisible = View.GONE;
    public int topLeftResource = R.mipmap.back_white;
    @Override
    public void enableLeftButton() {
        topLeftVisible = View.VISIBLE;
        notifyChange();
    }

    @Override
    public void disableLeftView() {
        topLeftVisible = View.GONE;
        notifyChange();
    }


    private View.OnClickListener topRightClickListener;
    public int topRightVisible = View.GONE;
    public String topRightText = "";
    @Override
    public void disableRightView() {
        topRightVisible = View.GONE;
        notifyChange();
    }


    public void onTopRightClick(){
        if (topRightClickListener != null){
            topRightClickListener.onClick(null);
        }
    }

    @Override
    public void setTopRightView(String text, View.OnClickListener listener) {
        topRightVisible = View.VISIBLE;
        topRightText = text;
        if (listener != null) {
            topRightClickListener = listener;
        }
        notifyChange();
    }


    private View.OnClickListener topRightImageClickListener;
    public int topRightImageVisible = View.GONE;
    public int topRightImageResource;


    public void onTopRightImageViewClick(){
        if (topRightImageClickListener != null){
            topRightImageClickListener.onClick(null);
        }
    }
    @Override
    public void enableRightImageView(int drawableId, View.OnClickListener listener) {
        topRightImageVisible = View.VISIBLE;
        topRightImageResource = drawableId;
        if (listener != null) {
            topRightImageClickListener = listener;
        }
    }

    @Override
    public void disableRightImageView() {
        topRightImageVisible = View.GONE;
        notifyChange();
    }

}
