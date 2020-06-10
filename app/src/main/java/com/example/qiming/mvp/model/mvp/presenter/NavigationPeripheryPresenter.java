package com.example.qiming.mvp.model.mvp.presenter;

import android.app.Application;
import com.example.qiming.mvp.model.mvp.contract.NavigationPeripheryContract;
import com.jess.arms.di.scope.ActivityScope;
import com.jess.arms.http.imageloader.ImageLoader;
import com.jess.arms.integration.AppManager;
import com.jess.arms.mvp.BasePresenter;
import me.jessyan.rxerrorhandler.core.RxErrorHandler;

import javax.inject.Inject;
@ActivityScope
public class NavigationPeripheryPresenter extends BasePresenter<NavigationPeripheryContract.Model, NavigationPeripheryContract.View> {
    @Inject
    RxErrorHandler mErrorHandler;
    @Inject
    Application mApplication;
    @Inject
    ImageLoader mImageLoader;
    @Inject
    AppManager mAppManager;

    @Inject
    public NavigationPeripheryPresenter(NavigationPeripheryContract.Model model, NavigationPeripheryContract.View rootView) {
        super(model, rootView);
    }

    @Override
    public void onStart() {
        super.onStart();
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
