package com.example.qiming.mvp.model.di.module;

import com.example.qiming.mvp.model.mvp.contract.NavigationMessageContract;
import com.example.qiming.mvp.model.mvp.model.NavigationMessageModel;
import dagger.Binds;
import dagger.Module;

@Module
public  abstract class  NavigationMessageModule {
    @Binds
    abstract NavigationMessageContract.Model bindMainModel(NavigationMessageModel model);


}
