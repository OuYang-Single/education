package com.example.qiming.mvp.model.di.module;

import com.example.qiming.mvp.model.entity.AdEntity;
import com.example.qiming.mvp.model.entity.BandItem;
import com.example.qiming.mvp.model.entity.CourseEntity;
import com.example.qiming.mvp.model.entity.OptionEntity;
import com.example.qiming.mvp.model.mvp.contract.NavigationHomeContract;
import com.example.qiming.mvp.model.mvp.model.NavigationHomeModel;
import com.example.qiming.mvp.model.mvp.ui.adapter.HomeContentAdapter;
import com.example.qiming.mvp.model.mvp.ui.adapter.HomeItemAdPagerAdapter;
import com.example.qiming.mvp.model.mvp.ui.adapter.HomeItemContextAdapter;
import com.example.qiming.mvp.model.mvp.ui.adapter.HomeItemOptionAdapter;
import com.jess.arms.di.scope.ActivityScope;
import dagger.Binds;
import dagger.Module;
import dagger.Provides;

import java.util.ArrayList;
import java.util.List;

@Module
public abstract class NavigationHomeModule {
    @Binds
    abstract NavigationHomeContract.Model bindMainModel(NavigationHomeModel model);

    @ActivityScope
    @Provides
    public static List<BandItem> getBandItemList() {
        return new ArrayList<>();
    }

    @ActivityScope
    @Provides
    public static HomeContentAdapter getHomeContentAdapter(List<BandItem> infos) {
        return new HomeContentAdapter(infos);
    }

    @ActivityScope
    @Provides
    public static List<AdEntity> getAdEntityList() {
        return new ArrayList<>();
    }

    @ActivityScope
    @Provides
    public static HomeItemAdPagerAdapter getHomeItmeAdPagerAdapter(List<AdEntity> adEntityList, NavigationHomeContract.View view) {
        return new HomeItemAdPagerAdapter(adEntityList,view.getContent(),view);
    }
    @ActivityScope
    @Provides
    public static List<OptionEntity> getOptionEntityList() {
        return new ArrayList<>();
    }

    @ActivityScope
    @Provides
    public static HomeItemOptionAdapter getHomeItemOptionAdapter(List<OptionEntity> optionEntityList, NavigationHomeContract.View view) {
        HomeItemOptionAdapter adapter=       new HomeItemOptionAdapter(optionEntityList);
        adapter.setOnItemClickListener(view);
        return adapter;
    }

    @ActivityScope
    @Provides
    public static List<CourseEntity> getCourseEntityList() {
        return new ArrayList<>();
    }

    @ActivityScope
    @Provides
    public static HomeItemContextAdapter getHomeItemContextAdapter(List<CourseEntity> courseEntityList, NavigationHomeContract.View view) {
        HomeItemContextAdapter adapter=       new HomeItemContextAdapter(courseEntityList);
        return adapter;
    }
}
