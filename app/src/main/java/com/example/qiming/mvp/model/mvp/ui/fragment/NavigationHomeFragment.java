package com.example.qiming.mvp.model.mvp.ui.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import com.example.qiming.R;
import com.example.qiming.mvp.model.di.component.DaggerNavigationHomeComponent;
import com.example.qiming.mvp.model.entity.AdEntity;
import com.example.qiming.mvp.model.entity.OptionEntity;
import com.example.qiming.mvp.model.mvp.contract.NavigationHomeContract;
import com.example.qiming.mvp.model.mvp.presenter.NavigationHomePresenter;
import com.example.qiming.mvp.model.mvp.ui.adapter.HomeContentAdapter;
import com.jess.arms.base.BaseFragment;
import com.jess.arms.di.component.AppComponent;

import javax.inject.Inject;

public class NavigationHomeFragment  extends BaseFragment<NavigationHomePresenter> implements NavigationHomeContract.View {
    @BindView(R.id.rv_home_content)
    RecyclerView mHomeContentRv;
    @Inject
    HomeContentAdapter mHomeContentAdapter;
    @Override
    public void setupFragmentComponent(@NonNull AppComponent appComponent) {
        DaggerNavigationHomeComponent //如找不到该类,请编译一下项目
                .builder()
                .appComponent(appComponent)
                .view(this)
                .build()
                .inject(this);
    }

    @Override
    public View initView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return getLayoutInflater().inflate(R.layout.navigation_home_fragment, null, false);
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        mHomeContentRv.setLayoutManager(new LinearLayoutManager(getContext()));
        mHomeContentRv.setAdapter(mHomeContentAdapter);

    }

    @Override
    public void setData(@Nullable Object data) {

    }

    @Override
    public void showMessage(@NonNull String message) {
        Toast.makeText(mContext, ""+message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public Context getContent() {
        return getContext();
    }

    @Override
    public void onClick(View view, AdEntity adEntity, int position) {
        showMessage(adEntity.getJumpType()+"");
    }

    @Override
    public void onItemClick(@NonNull View view, int viewType, @NonNull Object data, int position) {

        showMessage(((OptionEntity)data).getImageId()+"");
    }
}
