package com.example.qiming.mvp.model.mvp.ui.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.example.qiming.R;
import com.example.qiming.mvp.model.di.component.DaggerNavigationMineComponent;
import com.example.qiming.mvp.model.mvp.contract.NavigationMineContract;
import com.example.qiming.mvp.model.mvp.presenter.NavigationMinePresenter;
import com.jess.arms.base.BaseFragment;
import com.jess.arms.di.component.AppComponent;

public class NavigationMineFragment extends BaseFragment<NavigationMinePresenter> implements NavigationMineContract.View {


    @Override
    public void setupFragmentComponent(@NonNull AppComponent appComponent) {
        DaggerNavigationMineComponent //如找不到该类,请编译一下项目
                .builder()
                .appComponent(appComponent)
                .view(this)
                .build()
                .inject(this);
    }

    @Override
    public View initView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return getLayoutInflater().inflate(R.layout.navigation_mine_fragment, null, false);
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
