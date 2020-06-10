package com.example.qiming.mvp.model.di.module;

import com.example.qiming.mvp.model.mvp.contract.NavigationMineContract;
import com.example.qiming.mvp.model.mvp.model.NavigationMineModel;
import dagger.Binds;
import dagger.Module;

@Module
public  abstract class  NavigationMineModule {
    @Binds
    abstract NavigationMineContract.Model bindMainModel(NavigationMineModel model);


}
