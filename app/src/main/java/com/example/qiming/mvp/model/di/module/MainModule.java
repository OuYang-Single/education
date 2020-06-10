package com.example.qiming.mvp.model.di.module;

import androidx.fragment.app.Fragment;
import com.example.qiming.greendao.ManagerFactory;
import com.example.qiming.mvp.model.mvp.contract.MainContract;
import com.example.qiming.mvp.model.mvp.model.MainModel;
import com.example.qiming.mvp.model.mvp.ui.adapter.ViewPagerContentAdapter;
import com.jess.arms.di.scope.ActivityScope;
import dagger.Binds;
import dagger.Module;
import dagger.Provides;

import java.util.ArrayList;
import java.util.List;

@Module
public abstract class MainModule {

    @Binds
    abstract MainContract.Model bindMainModel(MainModel model);
    @Provides
    public static ManagerFactory getManagerFactory() {
        return ManagerFactory.getInstance();
    }
    @ActivityScope
    @Provides
    public static List<Fragment> getFragmentList(){
        return new ArrayList<>();
    }
    @ActivityScope
    @Provides
    public static ViewPagerContentAdapter getViewPagerContentAdapter(MainContract.View mView,List<Fragment> fragments){
        return new ViewPagerContentAdapter(mView.getFragmentManagers(),fragments);
    }

}
