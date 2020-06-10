package com.example.qiming.mvp.model.di.component;

import com.example.qiming.mvp.model.di.module.NavigationHomeModule;
import com.example.qiming.mvp.model.mvp.contract.NavigationHomeContract;
import com.example.qiming.mvp.model.mvp.ui.fragment.NavigationHomeFragment;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.di.scope.ActivityScope;
import dagger.BindsInstance;
import dagger.Component;
@ActivityScope
@Component(modules = NavigationHomeModule.class, dependencies = AppComponent.class)
public interface NavigationHomeComponent {
    void inject(NavigationHomeFragment activity);

    @Component.Builder
    interface Builder {
        @BindsInstance
        NavigationHomeComponent.Builder view(NavigationHomeContract.View view);

        NavigationHomeComponent.Builder appComponent(AppComponent appComponent);

        NavigationHomeComponent build();
    }
}
