package com.example.qiming.mvp.model.mvp.model;

import android.app.Application;
import com.example.qiming.mvp.model.mvp.contract.NavigationMineContract;
import com.google.gson.Gson;
import com.jess.arms.di.scope.ActivityScope;
import com.jess.arms.integration.IRepositoryManager;
import com.jess.arms.mvp.BaseModel;

import javax.inject.Inject;
@ActivityScope
public class NavigationMineModel extends BaseModel implements NavigationMineContract.Model {
    @Inject
    Gson mGson;
    @Inject
    Application mApplication;

    @Inject
    public NavigationMineModel(IRepositoryManager repositoryManager) {
        super(repositoryManager);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.mGson = null;
        this.mApplication = null;
    }
}