package com.example.qiming.mvp.model.mvp.contract;

import android.content.Context;
import android.content.Intent;
import androidx.fragment.app.FragmentManager;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.jess.arms.mvp.IModel;
import com.jess.arms.mvp.IView;

public interface MainContract {
    //对于经常使用的关于UI的方法可以定义到IView中,如显示隐藏进度条,和显示文字消息
    interface View extends IView {

        Context getContent();

        void statusService(Intent intent);

        void RestoreSelected(int image);

        FragmentManager getFragmentManagers();
    }

    //Model层定义接口,外部只需关心Model返回的数据,无需关心内部细节,即是否使用缓存
    interface Model extends IModel {

        void setBottomNavigationItem(BottomNavigationView mNavView, int i);

        void onStart();
    }
}
