package com.example.qiming.mvp.model.mvp.ui.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.example.qiming.R;
import com.example.qiming.mvp.model.di.component.DaggerNavigationPeripheryComponent;
import com.example.qiming.mvp.model.mvp.contract.NavigationPeripheryContract;
import com.example.qiming.mvp.model.mvp.presenter.NavigationPeripheryPresenter;
import com.jess.arms.base.BaseFragment;
import com.jess.arms.di.component.AppComponent;

public class NavigationPeripheryFragment  extends BaseFragment<NavigationPeripheryPresenter> implements NavigationPeripheryContract.View {


    @Override
    public void setupFragmentComponent(@NonNull AppComponent appComponent) {
        DaggerNavigationPeripheryComponent //如找不到该类,请编译一下项目
                .builder()
                .appComponent(appComponent)
                .view(this)
                .build()
                .inject(this);
    }

    @Override
    public View initView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return getLayoutInflater().inflate(R.layout.navigation_periphery_fragment, null, false);
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {

    }

    @Override
    public void setData(@Nullable Object data) {

    }

    @Override
    public void showMessage(@NonNull String message) {

    }

    @Override
    public Context getContent() {
        return getContext();
    }
}
