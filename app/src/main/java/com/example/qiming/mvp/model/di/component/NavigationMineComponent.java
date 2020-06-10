package com.example.qiming.mvp.model.di.component;

import com.example.qiming.mvp.model.di.module.NavigationMineModule;
import com.example.qiming.mvp.model.mvp.contract.NavigationMineContract;
import com.example.qiming.mvp.model.mvp.ui.fragment.NavigationMineFragment;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.di.scope.ActivityScope;
import dagger.BindsInstance;
import dagger.Component;

@ActivityScope
@Component(modules = NavigationMineModule.class, dependencies = AppComponent.class)
public interface NavigationMineComponent {
    void inject(NavigationMineFragment activity);

    @Component.Builder
    interface Builder {
        @BindsInstance
        NavigationMineComponent.Builder view(NavigationMineContract.View view);

        NavigationMineComponent.Builder appComponent(AppComponent appComponent);

        NavigationMineComponent build();
    }
}