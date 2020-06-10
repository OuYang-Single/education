package com.example.qiming.mvp.model.di.component;

import com.example.qiming.mvp.model.di.module.MainModule;
import com.example.qiming.mvp.model.mvp.contract.MainContract;
import com.example.qiming.mvp.model.mvp.ui.activity.MainActivity;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.di.scope.ActivityScope;
import dagger.BindsInstance;
import dagger.Component;
@ActivityScope
@Component(modules = MainModule.class, dependencies = AppComponent.class)
public interface MainComponent {
    void inject(MainActivity activity);

    @Component.Builder
    interface Builder {
        @BindsInstance
        MainComponent.Builder view( MainContract.View view);

        MainComponent.Builder appComponent(AppComponent appComponent);

        MainComponent build();
    }
}
