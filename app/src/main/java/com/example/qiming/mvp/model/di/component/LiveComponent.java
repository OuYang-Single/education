package com.example.qiming.mvp.model.di.component;

import com.example.qiming.mvp.model.di.module.LiveModule;
import com.example.qiming.mvp.model.mvp.contract.LiveContract;
import com.example.qiming.mvp.model.mvp.ui.activity.LiveActivity;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.di.scope.ActivityScope;
import dagger.BindsInstance;
import dagger.Component;


@ActivityScope
@Component(modules = LiveModule.class, dependencies = AppComponent.class)
public interface LiveComponent {
    void inject( LiveActivity activity);

    @Component.Builder
    interface Builder {
        @BindsInstance
        LiveComponent.Builder view( LiveContract.View view);

        LiveComponent.Builder appComponent(AppComponent appComponent);

        LiveComponent build();
    }
}