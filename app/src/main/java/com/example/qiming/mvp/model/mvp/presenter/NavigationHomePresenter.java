package com.example.qiming.mvp.model.mvp.presenter;

import android.app.Application;
import com.example.qiming.mvp.model.mvp.contract.NavigationHomeContract;
import com.jess.arms.di.scope.ActivityScope;
import com.jess.arms.http.imageloader.ImageLoader;
import com.jess.arms.integration.AppManager;
import com.jess.arms.mvp.BasePresenter;
import me.jessyan.rxerrorhandler.core.RxErrorHandler;

import javax.inject.Inject;

@ActivityScope
public class NavigationHomePresenter extends BasePresenter<NavigationHomeContract.Model, NavigationHomeContract.View> {
    @Inject
    RxErrorHandler mErrorHandler;
    @Inject
    Application mApplication;
    @Inject
    ImageLoader mImageLoader;
    @Inject
    AppManager mAppManager;

    @Inject
    public NavigationHomePresenter(NavigationHomeContract.Model model, NavigationHomeContract.View rootView) {
        super(model, rootView);
    }

    @Override
    public void onStart() {
        super.onStart();
        mModel.initData();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.mErrorHandler = null;
        this.mAppManager = null;
        this.mImageLoader = null;
        this.mApplication = null;
    }
}
