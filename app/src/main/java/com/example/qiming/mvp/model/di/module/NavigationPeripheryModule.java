package com.example.qiming.mvp.model.di.module;


import com.example.qiming.mvp.model.mvp.contract.NavigationPeripheryContract;
import com.example.qiming.mvp.model.mvp.model.NavigationPeripheryModel;
import dagger.Binds;
import dagger.Module;

@Module
public abstract class NavigationPeripheryModule {
    @Binds
    abstract NavigationPeripheryContract.Model bindMainModel(NavigationPeripheryModel model);


}
