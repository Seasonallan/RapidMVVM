package com.season.example.ui.fragment;

import android.view.View;

import com.season.example.R;
import com.season.example.databinding.FragmentUserBinding;
import com.season.example.ui.activity.AboutActivity;
import com.season.mvp.ui.BaseTLEFragment;
import com.season.mvp.vm.BaseViewModel;

/**
 * Disc:
 * User: SeasonAllan(451360508@qq.com)
 * Time: 2017-06-10 15:27
 */
public class UserFragment extends BaseTLEFragment<FragmentUserBinding> {

    @Override
    protected void bindViewModel(FragmentUserBinding binding) {
        UserViewModel userViewModel = new UserViewModel(this);
        binding.setUser(userViewModel);
        userViewModel.getTitleBar().setTopTile("用户中心");
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_user;
    }

    public class UserViewModel extends BaseViewModel{

        public String desc = "跳转到关于";
        public UserViewModel(BaseTLEFragment baseTLEFragment) {
            super(baseTLEFragment);
        }

        public void onBtnClick(View view){
            startActivity(AboutActivity.class);
        }
    }

}
