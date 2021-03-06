package com.season.example.ui.fragment;

import android.view.ViewGroup;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;

import com.season.example.R;
import com.season.example.databinding.FragmentHomeBinding;
import com.season.example.databinding.ItemCommentBinding;
import com.season.example.entry.VideoItem;
import com.season.example.ui.activity.AboutActivity;
import com.season.example.ui.adapter.HomeAdapter;
import com.season.example.vm.HomeViewModel;
import com.season.lib.BaseContext;
import com.season.lib.ui.BaseRecycleAdapter;
import com.season.lib.ui.view.refreshview.HeadFootView;
import com.season.mvp.ui.BaseTLEFragment;
import com.season.mvp.vm.BindingViewHolder;

import java.util.List;

import static com.season.mvp.ui.pulltorefresh.IPull2RefreshView.CREATE;


/**
 * Disc:
 * User: SeasonAllan(451360508@qq.com)
 * Time: 2017-06-10 15:27
 */
public class HomeFragment extends BaseTLEFragment<FragmentHomeBinding>{

    HomeViewModel homeViewModel;
    HomeAdapter mHomeAdapter;
    @Override
    protected void bindViewModel(FragmentHomeBinding binding) {
        homeViewModel = new HomeViewModel(this){

            @Override
            public BaseRecycleAdapter initAdapter(List result) {
                mHomeAdapter = new HomeAdapter(BaseContext.sContext, result) {

                    public void onItemClick(VideoItem item) {
                        startActivity(AboutActivity.class);
                    }
                };
                mHomeAdapter.setHasStableIds(true);
                return mHomeAdapter;
            }

            @Override
            public BaseRecycleAdapter getAdapter() {
                return mHomeAdapter;
            }

        };

        homeViewModel.getTitleBar().setTopTile("商品管理");
        homeViewModel.loadList(CREATE);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_home;
    }

}
