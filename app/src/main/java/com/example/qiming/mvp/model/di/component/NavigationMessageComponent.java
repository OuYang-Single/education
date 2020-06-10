package com.example.qiming.mvp.model.di.component;

import com.example.qiming.mvp.model.di.module.NavigationMessageModule;
import com.example.qiming.mvp.model.mvp.contract.NavigationMessageContract;
import com.example.qiming.mvp.model.mvp.ui.fragment.NavigationMessageFragment;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.di.scope.ActivityScope;
import dagger.BindsInstance;
import dagger.Component;

@ActivityScope
@Component(modules = NavigationMessageModule.class, dependencies = AppComponent.class)
public interface NavigationMessageComponent {
    void inject(NavigationMessageFragment activity);

    @Component.Builder
    interface Builder {
        @BindsInstance
        NavigationMessageComponent.Builder view(NavigationMessageContract.View view);

        NavigationMessageComponent.Builder appComponent(AppComponent appComponent);

        NavigationMessageComponent build();
    }
}
