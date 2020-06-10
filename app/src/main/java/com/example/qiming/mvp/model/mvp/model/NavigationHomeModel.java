package com.example.qiming.mvp.model.mvp.model;

import android.app.Application;
import com.example.qiming.R;
import com.example.qiming.mvp.model.entity.AdEntity;
import com.example.qiming.mvp.model.entity.BandItem;
import com.example.qiming.mvp.model.entity.CourseEntity;
import com.example.qiming.mvp.model.entity.OptionEntity;
import com.example.qiming.mvp.model.mvp.contract.NavigationHomeContract;
import com.example.qiming.mvp.model.mvp.ui.adapter.HomeContentAdapter;
import com.example.qiming.mvp.model.mvp.ui.adapter.HomeItemAdPagerAdapter;
import com.example.qiming.mvp.model.mvp.ui.adapter.HomeItemContextAdapter;
import com.example.qiming.mvp.model.mvp.ui.adapter.HomeItemOptionAdapter;
import com.google.gson.Gson;
import com.jess.arms.di.scope.ActivityScope;
import com.jess.arms.integration.IRepositoryManager;
import com.jess.arms.mvp.BaseModel;

import javax.inject.Inject;
import java.util.List;

import static com.example.qiming.mvp.model.entity.BandItem.CONTENT;
import static com.example.qiming.mvp.model.entity.BandItem.OPTION;

@ActivityScope
public class NavigationHomeModel extends BaseModel implements NavigationHomeContract.Model {
    @Inject
    Gson mGson;
    @Inject
    Application mApplication;
    @Inject
    HomeContentAdapter mHomeContentAdapter;
    @Inject
    List<BandItem> mBandItemList;
    @Inject
    HomeItemAdPagerAdapter mHomeItmeAdPagerAdapter;
    @Inject
    List<AdEntity> mAdEntityList;
    @Inject
    HomeItemOptionAdapter mHomeItemOptionAdapter;
    @Inject
    List<OptionEntity>mOptionEntityList;
    @Inject
    HomeItemContextAdapter mHomeItemContextAdapter;
    @Inject
    List<CourseEntity>mCourseEntity;
    @Inject
    public NavigationHomeModel(IRepositoryManager repositoryManager) {
        super(repositoryManager);
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        this.mGson = null;
        this.mApplication = null;
    }

    @Override
    public void initData() {
        BandItem<List<AdEntity>, HomeItemAdPagerAdapter> bandItem = new BandItem();
        AdEntity mAdEntity=new AdEntity();
        mAdEntity.setJumpType(R.mipmap.ic_carousel);
        mAdEntityList.add(mAdEntity);
        bandItem.setAdapter(mHomeItmeAdPagerAdapter);
        bandItem.setData(mAdEntityList);
        mBandItemList.add(bandItem);

        BandItem<List<OptionEntity>, HomeItemOptionAdapter> bandItem1 = new BandItem();
        mOptionEntityList.add(new OptionEntity(R.mipmap.ic_beauty_anchor,null,R.string.home_itme_option_beauty));
        mOptionEntityList.add(new OptionEntity(R.mipmap.ic_star_anchor,null,R.string.home_itme_option_star));
        mOptionEntityList.add(new OptionEntity(R.mipmap.ic_game_anchor, null, R.string.home_itme_option_game));
        mOptionEntityList.add(new OptionEntity(R.mipmap.ic_stand_alone_game,null,R.string.home_itme_option_stand_alone_game));
        mOptionEntityList.add(new OptionEntity(R.mipmap.ic_more_categories,null,R.string.home_itme_option_more_categories));
        bandItem1.setAdapter(mHomeItemOptionAdapter);
        bandItem1.setData(mOptionEntityList);
        bandItem1.setmType(OPTION);
        mBandItemList.add(bandItem1);
        BandItem<List<CourseEntity>, HomeItemContextAdapter> bandItem2 = new BandItem();
        mCourseEntity.add(new CourseEntity("美丽的小包子1",R.mipmap.ic_bg));
        mCourseEntity.add(new CourseEntity("母与子1",R.mipmap.ic_bg));
        mCourseEntity.add(new CourseEntity("美丽的小包子2",R.mipmap.ic_bg));
        mCourseEntity.add(new CourseEntity("母与子2",R.mipmap.ic_bg));
        mCourseEntity.add(new CourseEntity("美丽的小包子3",R.mipmap.ic_bg));
        mCourseEntity.add(new CourseEntity("母与子3",R.mipmap.ic_bg));
        mCourseEntity.add(new CourseEntity("美丽的小包子4",R.mipmap.ic_bg));
        mCourseEntity.add(new CourseEntity("母与子4",R.mipmap.ic_bg));
        bandItem2.setAdapter(mHomeItemContextAdapter);
        bandItem2.setData(mCourseEntity);
        bandItem2.setmType(CONTENT);
        mBandItemList.add(bandItem2);
    }
}