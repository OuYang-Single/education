package com.example.qiming.mvp.model.mvp.presenter;

import android.app.Application;
import android.content.Intent;
import com.example.qiming.R;
import com.example.qiming.mvp.model.entity.Request;
import com.example.qiming.mvp.model.entity.User;
import com.example.qiming.mvp.model.mvp.contract.LogInContract;

import com.example.qiming.mvp.model.mvp.ui.activity.MainActivity;
import com.jess.arms.di.scope.ActivityScope;
import com.jess.arms.http.imageloader.ImageLoader;
import com.jess.arms.integration.AppManager;
import com.jess.arms.mvp.BasePresenter;
import com.jess.arms.utils.RxLifecycleUtils;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import me.jessyan.rxerrorhandler.core.RxErrorHandler;
import me.jessyan.rxerrorhandler.handler.ErrorHandleSubscriber;
import me.jessyan.rxerrorhandler.handler.RetryWithDelay;

import javax.inject.Inject;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


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
public class LogInPresenter extends BasePresenter<LogInContract.Model, LogInContract.View> {
    @Inject
    RxErrorHandler mErrorHandler;
    @Inject
    Application mApplication;
    @Inject
    ImageLoader mImageLoader;
    @Inject
    AppManager mAppManager;

    @Inject
    public LogInPresenter(LogInContract.Model model, LogInContract.View rootView) {
        super(model, rootView);
    }

    @Override
    public void onStart() {
        super.onStart();
      /*  Intent intent=new Intent(mRootView.getContent(), DownloadFileService.class);
        mRootView.statusService(intent);*/
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.mErrorHandler = null;
        this.mAppManager = null;
        this.mImageLoader = null;
        this.mApplication = null;
    }

    public void login(String Name, String Password) {
        if (Verification(Name,Password)) {
            return;
        }
        mModel.getUsers(Name,Password,false) .subscribeOn(Schedulers.io())
                .retryWhen(new RetryWithDelay(0, 2))//遇到错误时重试,第一个参数为重试几次,第二个参数为重试的间隔
                .subscribeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(disposable -> {
                    mRootView.showLoading();//显示下拉刷新的进度条
                })
                .observeOn(AndroidSchedulers.mainThread())
                .doFinally(() -> {
                    mRootView.hideLoading();//隐藏下拉刷新的进度条
                })
                .compose(RxLifecycleUtils.bindToLifecycle(mRootView))//使用 Rxlifecycle,使 Disposable 和 Activity 一起销毁
                .subscribe(new ErrorHandleSubscriber<Request<User>>(mErrorHandler) {
                    @Override
                    public void onNext(Request<User> users) {
                        mModel.seve(users);
                        mRootView.launchActivity(new Intent(mRootView.getContent(), MainActivity.class));
                       // mRootView.showMessage("Name" +users.getUser().getUsername());
                    }

                    @Override
                    public void onError(Throwable t) {
                        super.onError(t);

                    }
                });
    }

    public boolean Verification(String Neme, String Password){
        if (isNull(Neme)) {
            mRootView.showMessage(mRootView.getContent().getString(R.string.log_in_account_null));
            return true;
        }
        if (isNull(Password)) {
            mRootView.showMessage(mRootView.getContent().getString(R.string.log_in_password_null));
            return true;
        }
        if (!(isMobile(Neme)||isPhone(Neme))){
            mRootView.showMessage(mRootView.getContent().getString(R.string.log_in_no_phone));
            return true;
        }
        return false;
    }

    public boolean isNull(String string) {
        boolean isNull = false;
        if (string != null && "".equals(string)) {
            isNull = true;
        }
        return isNull;
    }

    /**
     * 手机号验证
     *
     * @param str
     * @return 验证通过返回true
     */
    public static boolean isMobile(final String str) {
        Pattern p = null;
        Matcher m = null;
        boolean b = false;
        p = Pattern.compile("^[1][3,4,5,7,8][0-9]{9}$"); // 验证手机号
        m = p.matcher(str);
        b = m.matches();
        return b;
    }

    /**
     * 电话号码验证
     *
     * @param str
     * @return 验证通过返回true
     */
    public static boolean isPhone(final String str) {
        Pattern p1 = null, p2 = null;
        Matcher m = null;
        boolean b = false;
        p1 = Pattern.compile("^[0][1-9]{2,3}-[0-9]{5,10}$"); // 验证带区号的
        p2 = Pattern.compile("^[1-9]{1}[0-9]{5,8}$");     // 验证没有区号的
        if (str.length() > 9) {
            m = p1.matcher(str);
            b = m.matches();
        } else {
            m = p2.matcher(str);
            b = m.matches();
        }
        return b;
    }
}
