package com.example.qiming.mvp.model.di.module;

import com.example.qiming.mvp.model.mvp.contract.LiveContract;
import com.example.qiming.mvp.model.mvp.model.LiveModel;
import dagger.Binds;
import dagger.Module;

@Module
public abstract class LiveModule  {
    @Binds
    abstract LiveContract.Model bindMainModel(LiveModel model);
}