package com.example.qiming.mvp.model.mvp.model;

import android.app.Application;
import com.example.qiming.greendao.ManagerFactory;
import com.example.qiming.mvp.model.mvp.contract.LiveContract;
import com.google.gson.Gson;
import com.jess.arms.di.scope.ActivityScope;
import com.jess.arms.integration.IRepositoryManager;
import com.jess.arms.mvp.BaseModel;

import javax.inject.Inject;
@ActivityScope
public class LiveModel extends BaseModel implements LiveContract.Model {
    @Inject
    Gson mGson;
    @Inject
    Application mApplication;

    @Inject
    public LiveModel(IRepositoryManager repositoryManager) {
        super(repositoryManager);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.mGson = null;
        this.mApplication = null;
    }
}