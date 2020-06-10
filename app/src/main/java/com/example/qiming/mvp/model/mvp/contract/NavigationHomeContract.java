package com.example.qiming.mvp.model.mvp.contract;

import android.content.Context;
import androidx.annotation.NonNull;
import com.example.qiming.mvp.model.entity.AdEntity;
import com.example.qiming.mvp.model.mvp.ui.adapter.HomeItemAdPagerAdapter;
import com.jess.arms.base.DefaultAdapter;
import com.jess.arms.mvp.IModel;
import com.jess.arms.mvp.IView;

public interface NavigationHomeContract {

    //对于经常使用的关于UI的方法可以定义到IView中,如显示隐藏进度条,和显示文字消息
    interface View extends IView, HomeItemAdPagerAdapter.onClickHomeItmeAdListener, DefaultAdapter.OnRecyclerViewItemClickListener {
        Context getContent();

        @Override
        void onClick(android.view.View view, AdEntity adEntity, int position);

        @Override
        void onItemClick(@NonNull android.view.View view, int viewType, @NonNull Object data, int position);

        @Override
        void showMessage(@NonNull String message);
    }

    //Model层定义接口,外部只需关心Model返回的数据,无需关心内部细节,即是否使用缓存
    interface Model extends IModel {


        void initData();
    }
}
