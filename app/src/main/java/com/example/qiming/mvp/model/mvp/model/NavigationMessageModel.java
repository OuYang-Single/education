package com.example.qiming.mvp.model.mvp.model;

import android.app.Application;
import com.example.qiming.mvp.model.mvp.contract.NavigationMessageContract;
import com.google.gson.Gson;
import com.jess.arms.di.scope.ActivityScope;
import com.jess.arms.integration.IRepositoryManager;
import com.jess.arms.mvp.BaseModel;

import javax.inject.Inject;
@ActivityScope
public class NavigationMessageModel extends BaseModel implements NavigationMessageContract.Model {
    @Inject
    Gson mGson;
    @Inject
    Application mApplication;

    @Inject
    public NavigationMessageModel(IRepositoryManager repositoryManager) {
        super(repositoryManager);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.mGson = null;
        this.mApplication = null;
    }
}