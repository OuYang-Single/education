package com.example.qiming.mvp.model.mvp.model;

import android.annotation.SuppressLint;
import android.app.Application;
import android.util.Log;
import androidx.fragment.app.Fragment;
import com.example.qiming.greendao.ManagerFactory;
import com.example.qiming.mvp.model.mvp.contract.MainContract;
import com.example.qiming.mvp.model.mvp.ui.adapter.ViewPagerContentAdapter;
import com.example.qiming.mvp.model.mvp.ui.fragment.NavigationHomeFragment;
import com.example.qiming.mvp.model.mvp.ui.fragment.NavigationMessageFragment;
import com.example.qiming.mvp.model.mvp.ui.fragment.NavigationMineFragment;
import com.example.qiming.mvp.model.mvp.ui.fragment.NavigationPeripheryFragment;
import com.google.android.material.bottomnavigation.BottomNavigationItemView;
import com.google.android.material.bottomnavigation.BottomNavigationMenuView;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.gson.Gson;
import com.jess.arms.di.scope.ActivityScope;
import com.jess.arms.integration.IRepositoryManager;
import com.jess.arms.mvp.BaseModel;

import javax.inject.Inject;
import java.lang.reflect.Field;
import java.util.List;

import static com.jess.arms.utils.ArmsUtils.dip2px;

@ActivityScope
public class MainModel extends BaseModel implements MainContract.Model {
    @Inject
    Gson mGson;
    @Inject
    Application mApplication;
    @Inject
    ManagerFactory mManagerFactory;
    @Inject
    public MainModel(IRepositoryManager repositoryManager) {
        super(repositoryManager);
    }
    @Inject
    ViewPagerContentAdapter mViewPagerContentAdapterl;
    @Inject
    List<Fragment> fragmentList;
    @Override
    public void onStart() {
        fragmentList.add(new NavigationHomeFragment());
        fragmentList.add(new NavigationPeripheryFragment());
        fragmentList.add(new NavigationMessageFragment());
        fragmentList.add(new NavigationMineFragment());
        mViewPagerContentAdapterl.notifyDataSetChanged();
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        this.mGson = null;
        this.mApplication = null;
    }


    @Override
    @SuppressLint("RestrictedApi")
    public void setBottomNavigationItem( BottomNavigationView mNavView,int imgLen) {
        Class barClass = mNavView.getClass();
        Field[] fields = barClass.getDeclaredFields();
        for (int i = 0; i < fields.length; i++) {
            Field field = fields[i];
            field.setAccessible(true);

            if (field.getName().equals("menuView")) {
                try { //反射得到 mTabContainer
                    BottomNavigationMenuView barClasss =(BottomNavigationMenuView) field.get(mNavView);
                    Class barClassss = barClasss.getClass();
                    Field[] fieldss = barClassss.getDeclaredFields();
                    for (int j = 0; j < fieldss.length; j++) {
                        Field fields1 = fieldss[i];
                        fields1.setAccessible(true);
                        Log.w("setBottomNavigationItem",fields1.getName());
                        if (fields1.getName().equals("buttons")){
                            BottomNavigationItemView[]  bottomNavigationItemViewPool =(BottomNavigationItemView[] ) fields1.get(barClasss);
                            for (int a=0;a<bottomNavigationItemViewPool.length;a++){
                                if (a==2){
                                    BottomNavigationItemView mBottomNavigationItemView=bottomNavigationItemViewPool[a];
                                    mBottomNavigationItemView.setIconSize((int) dip2px(mApplication, imgLen));
                                }
                            }
                        }
                    }
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }}


}
