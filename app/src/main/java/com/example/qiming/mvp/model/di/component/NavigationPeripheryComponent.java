package com.example.qiming.mvp.model.di.component;

import com.example.qiming.mvp.model.di.module.NavigationPeripheryModule;
import com.example.qiming.mvp.model.mvp.contract.NavigationPeripheryContract;
import com.example.qiming.mvp.model.mvp.ui.fragment.NavigationPeripheryFragment;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.di.scope.ActivityScope;
import dagger.BindsInstance;
import dagger.Component;
@ActivityScope
@Component(modules = NavigationPeripheryModule.class, dependencies = AppComponent.class)
public interface NavigationPeripheryComponent {
    void inject(NavigationPeripheryFragment activity);

    @Component.Builder
    interface Builder {
        @BindsInstance
        NavigationPeripheryComponent.Builder view(NavigationPeripheryContract.View view);

        NavigationPeripheryComponent.Builder appComponent(AppComponent appComponent);

        NavigationPeripheryComponent build();
    }
}
