package com.season.example.vm;

import com.season.example.entry.BaseEntry;
import com.season.example.entry.Configure;
import com.season.example.entry.VideoItem;
import com.season.example.entry.VideoList;
import com.season.example.model.ModelFactory;
import com.season.lib.ui.BaseRecycleAdapter;
import com.season.mvp.ui.BaseTLEFragment;
import com.season.mvp.vm.BaseListViewModel;

import java.util.ArrayList;

import static com.season.mvp.ui.pulltorefresh.IPull2RefreshView.CREATE;
import static com.season.mvp.ui.pulltorefresh.IPull2RefreshView.MORE;
import static com.season.mvp.ui.pulltorefresh.IPull2RefreshView.REFRESH;


public abstract class HomeViewModel extends BaseListViewModel{

    public HomeViewModel(BaseTLEFragment baseTLEFragment){
        super(baseTLEFragment);
    }

    @Override
    public <T> void onResponse(int type, T result) {
        if (result instanceof BaseEntry) {
            VideoList videoLists = (VideoList) (((BaseEntry) result).data);
            onSuccess(type, videoLists.movies, Configure.PAGE_SIZE);
        }
    }


    @Override
    public void loadList(int callType) {
        if (callType == CREATE){
            getLoadingView().showLoadingView();

            ModelFactory.local().file().commcon().getValue("HomeVideo",
                    new LocalObserver<BaseEntry<VideoList>>() {
                        @Override
                        public void onError(Throwable e) {
                            loadList(REFRESH);
                        }
                    });
            return;
        }

        String maxId = "0";
        int action = 1;

        if (callType == MORE) {
            BaseRecycleAdapter adapter = getAdapter();
            if (adapter != null && adapter.getCount() > 0) {
                action = 2;
                maxId = ((VideoItem)adapter.getRealItem(adapter.getCount() - 1)).pub_id;
            }
        }

        if (true){
            BaseEntry<VideoList> entry = new BaseEntry<>();
            VideoList list = new VideoList();
            ArrayList<VideoItem> videoList = new ArrayList<VideoItem>();
            for (int i = 0; i < Configure.PAGE_SIZE; i++) {

                VideoItem item = new VideoItem();
                item.name = "孤独";
                item.intro = "孤独这两个字拆开来看，有孩童，有瓜果，有小犬，有蚊蝇，足以撑起一个盛夏傍晚间的巷子口，人情味十足。稚儿擎瓜柳棚下，细犬逐蝶窄巷中，人间繁华多笑语，惟我空余两鬓风。";
                item.cover_url = "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1594035526874&di=3fdb235c72b928d264ca24b022f1ad9a&imgtype=0&src=http%3A%2F%2Fdmimg.5054399.com%2Fallimg%2Fpkm%2Fpk%2F13.jpg";

                videoList.add(item);
            }
            list.movies = videoList;
            entry.data = list;
            onResponse2UI(callType, entry);
        }else{
            ModelFactory.net().kuaifang().video().getVideo(Configure.PAGE_SIZE, action, maxId,
                    new HttpCallback<BaseEntry<VideoList>>(callType));
        }

    }


}
