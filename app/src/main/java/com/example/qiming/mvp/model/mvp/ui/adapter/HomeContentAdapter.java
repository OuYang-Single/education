package com.example.qiming.mvp.model.mvp.ui.adapter;

import android.view.View;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;
import butterknife.BindView;
import com.example.qiming.R;
import com.example.qiming.mvp.model.entity.BandItem;
import com.example.qiming.mvp.model.entity.TransType;
import com.example.qiming.mvp.model.wigth.MyTransformer;
import com.jess.arms.base.BaseHolder;
import com.jess.arms.base.DefaultAdapter;

import java.util.List;

import static com.example.qiming.mvp.model.entity.BandItem.*;

public class HomeContentAdapter extends DefaultAdapter<BandItem> {

    public HomeContentAdapter(List<BandItem> infos) {
        super(infos);
    }

    @Override
    public int getItemViewType(int position) {
        return getItem(position).getmType();
    }

    @NonNull
    @Override
    public BaseHolder<BandItem> getHolder(@NonNull View v, int viewType) {
        BaseHolder<BandItem> bandItemBaseHolder = null;
        switch (viewType) {
            case HEAD:
                bandItemBaseHolder = new HomeItemHeadBaseHolder(v);
                break;
            case OPTION:
                bandItemBaseHolder = new HomeItemOptionBaseHolder(v);
                break;
            case CONTENT:
                bandItemBaseHolder = new HomeItemContentBaseHolder(v);
                break;
        }
        return bandItemBaseHolder;
    }

    @Override
    public int getLayoutId(int viewType) {
        int i = BandItem.HEAD;
        switch (viewType) {
            case BandItem.HEAD:
                i = R.layout.home_item_head;
                break;
            case OPTION:
                i = R.layout.home_item_option;
                break;
            case CONTENT:
                i = R.layout.home_item_content;
                break;
        }
        return i;
    }

    public class HomeItemHeadBaseHolder extends BaseHolder<BandItem> {
        @BindView(R.id.home_item_vp_ad)
        ViewPager mViewPager;
        public HomeItemHeadBaseHolder(View itemView) {
            super(itemView);
        }

        @Override
        public void setData(@NonNull BandItem data, int position) {
            mViewPager.setPageTransformer(true, MyTransformer.getMyTransformer(TransType.OVERLAP));
            mViewPager.setAdapter((HomeItemAdPagerAdapter) data.getAdapter());
        }
    }

    public class HomeItemOptionBaseHolder extends BaseHolder<BandItem> {

        @BindView(R.id.home_item_option_recycler_view)
        RecyclerView mHomeItemOptionRecyclerView;
        public HomeItemOptionBaseHolder(View itemView) {
            super(itemView);
        }

        @Override
        public void setData(@NonNull BandItem data, int position) {
            HomeItemOptionAdapter homeItemOptionAdapter = (HomeItemOptionAdapter) data.getAdapter();
            mHomeItemOptionRecyclerView.setLayoutManager(homeItemOptionAdapter.getLayoutManager(mHomeItemOptionRecyclerView.getContext()));
            mHomeItemOptionRecyclerView.setAdapter(homeItemOptionAdapter);
        }
    }

    public class HomeItemContentBaseHolder extends BaseHolder<BandItem> {

        @BindView(R.id.home_item_context_recycler_view)
        RecyclerView mHomeItemContextRecyclerView;
        public HomeItemContentBaseHolder(View itemView) {
            super(itemView);
        }

        @Override
        public void setData(@NonNull BandItem data, int position) {
            HomeItemContextAdapter homeItemContextAdapter = (HomeItemContextAdapter) data.getAdapter();
            mHomeItemContextRecyclerView.setLayoutManager(homeItemContextAdapter.getLayoutManager(mHomeItemContextRecyclerView.getContext()));
            mHomeItemContextRecyclerView.addItemDecoration(new HomeItemContextAdapter.GridSpacingItemDecoration(2,25,true));
            mHomeItemContextRecyclerView.setAdapter(homeItemContextAdapter);
           /* */
        }
    }
}
