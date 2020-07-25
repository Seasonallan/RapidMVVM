package com.season.example.ui.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.season.example.R;
import com.season.example.databinding.ItemHomeBinding;
import com.season.example.entry.VideoItem;
import com.season.lib.support.dimen.ScreenUtils;
import com.season.lib.ui.BaseRecycleAdapter;
import com.season.mvp.vm.BindingViewHolder;
import java.util.List;

/**
 * Disc:
 * User: SeasonAllan(451360508@qq.com)
 * Time: 2017-06-10 17:14
 */
public class HomeAdapter extends BaseRecycleAdapter<VideoItem> {

    public HomeAdapter(Context context, List<VideoItem> lists) {
        super(context, lists);
    }

    @Override
    public RecyclerView.ViewHolder onCreateHolder(ViewGroup parent, int viewType) {
        ItemHomeBinding binding = DataBindingUtil.inflate(inflater, R.layout.item_home, parent, false);

        View imageContainerView = binding.videoImageCont;
        LinearLayout.LayoutParams param = (LinearLayout.LayoutParams) imageContainerView.getLayoutParams();
        param.width = (int) (ScreenUtils.getScreenWidth() /3.1f * 1);
        param.height = (int) (ScreenUtils.getScreenWidth()/3.1f * 3/2);
        imageContainerView.requestLayout();
        return new BindingViewHolder(binding);
    }

    @Override
    public void onBindHolder(RecyclerView.ViewHolder holder, int position) {
        BindingViewHolder<ItemHomeBinding> homeHolder = (BindingViewHolder) holder;
        VideoItem item = getItem(position);
        ItemHomeBinding binding = homeHolder.getBinding();
        binding.setItem(item);
        binding.videoContent.recalculate();

    }


}
