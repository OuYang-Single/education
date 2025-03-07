package com.example.qiming.mvp.model.mvp.contract;

import android.content.Context;
import android.content.Intent;
import com.example.qiming.mvp.model.entity.Request;
import com.example.qiming.mvp.model.entity.User;
import com.jess.arms.mvp.IModel;
import com.jess.arms.mvp.IView;
import io.reactivex.Observable;


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
public interface LogInContract {
    //对于经常使用的关于UI的方法可以定义到IView中,如显示隐藏进度条,和显示文字消息
    interface View extends IView {

        Context getContent();

        void statusService(Intent intent);
    }

    //Model层定义接口,外部只需关心Model返回的数据,无需关心内部细节,即是否使用缓存
    interface Model extends IModel {
        Observable<Request<User>> getUsers(String Neme, String Password, boolean b);

        void seve(Request<User> users);
    }
}
