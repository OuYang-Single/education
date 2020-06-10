
package com.example.qiming.mvp.model.di.component;

import com.example.qiming.mvp.model.di.module.DownloadFileModule;
import com.example.qiming.mvp.model.mvp.ui.Service.DownloadFileService;
import com.jess.arms.di.scope.ActivityScope;
import dagger.Component;


/**
 * ================================================
 * Description:
 * <p>
 * Created by MVPArmsTemplate on 09/06/2019 20:50
 * <a href="mailto:jess.yan.effort@gmail.com">Contact me</a>
 * <a href="https://github.com/JessYanCoding">Follow me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms">Star me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms/wiki">See me</a>
 * <a href="https://github.com/JessYanCoding/MVPArmsTemplate">模版请保持更新</a>
 * ================================================
 */
@ActivityScope
@Component(modules = DownloadFileModule.class)
public interface DownloadFileComponent {
    void inject(DownloadFileService activity);


}