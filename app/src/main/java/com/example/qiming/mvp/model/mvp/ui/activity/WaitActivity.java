package com.example.qiming.mvp.model.mvp.ui.activity;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import butterknife.BindView;
import butterknife.OnClick;
import com.example.qiming.R;
import com.example.qiming.mvp.model.di.component.DaggerWaitComponent;
import com.example.qiming.mvp.model.mvp.contract.WaitContract;
import com.example.qiming.mvp.model.mvp.presenter.WaitPresenter;
import com.example.qiming.mvp.model.mvp.ui.CustomizeView.FullScreenVideoView;
import com.jess.arms.base.BaseActivity;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.utils.ArmsUtils;
import kr.co.namee.permissiongen.PermissionFail;
import kr.co.namee.permissiongen.PermissionGen;
import kr.co.namee.permissiongen.PermissionSuccess;

import java.io.File;

import static com.example.qiming.app.utils.RxUtils.setFullscreen;
import static com.example.qiming.mvp.model.mvp.ui.Service.DownloadFileService.SD_HOME_DIR;
import static com.jess.arms.utils.Preconditions.checkNotNull;

// setContentView(R.layout.activity_wait);
public class WaitActivity extends BaseActivity<WaitPresenter> implements WaitContract.View {
    @BindView(R.id.wait_ad_video)
    FullScreenVideoView mWaitAdVideo;
    @BindView(R.id.wait_time_jump_txt)
    TextView mWaitTimeJumpTxt;
    String URL = "";

    @Override
    public void setupActivityComponent(@NonNull AppComponent appComponent) {
        DaggerWaitComponent //如找不到该类,请编译一下项目
                .builder()
                .appComponent(appComponent)
                .view(this)
                .build()
                .inject(this);
    }

    @Override
    public int initView(@Nullable Bundle savedInstanceState) {
        setFullscreen(this);
        return R.layout.activity_wait;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        PermissionGen.with(this)
                .addRequestCode(100)
                .permissions(
                        Manifest.permission.READ_EXTERNAL_STORAGE,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE
                ).request();

    }

    @PermissionSuccess(requestCode = 100)
    public void doSomething() {
        mPresenter.WaitingTime();
        if (new File(SD_HOME_DIR + "download.mp4").exists()){
            mWaitAdVideo.setVideoURI(Uri.parse(SD_HOME_DIR + "download.mp4"));
        }else {
            //  mWaitAdVideo.setVideoPath("http://192.168.1.102:8000/mda-imjphgh9c4u871x1.mp4");
           mWaitAdVideo.setVideoURI(Uri.parse("android.resource://"+getPackageName()+File.separator+R.raw.wait_video));
        }
        // mWaitAdVideo.setVideoPath("http://192.168.1.101:8000/mda-imjphgh9c4u871x1.mp4");
        mWaitAdVideo.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mediaPlayer) {
                mediaPlayer.start();
            }
        });
        mWaitAdVideo.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                mediaPlayer.start();
            }
        });
    }

    @PermissionFail(requestCode = 100)
    public void doFailSomething() {
    }

    @OnClick({R.id.wait_time_jump_txt})
    public void OnClick(View mView) {
        switch (mView.getId()) {
            case R.id.wait_time_jump_txt:
                mPresenter.Jump();
                break;
        }
    }

    @Override
    public Context getContent() {
        return this;
    }

    @Override
    public void setEventSwitch(boolean isSwitch) {
        //     mWaitTimeJumpTxt.setClickable(isSwitch);
    }

    @Override
    public void setTimeJumpTxt(@NonNull String isSwitch) {
        try {
            if (mWaitTimeJumpTxt!=null&&isSwitch!=null){
                mWaitTimeJumpTxt.setText(isSwitch);
            }
        }catch (Exception e){

        }

    }

    @Override
    public void statusService(Intent intent) {

    }

    @Override
    public void showMessage(@NonNull String message) {

    }

    @Override
    public void launchActivity(@io.reactivex.annotations.NonNull Intent intent) {
        checkNotNull(intent);
        ArmsUtils.startActivity(intent);
        killMyself();
    }

    @Override
    public void killMyself() {
        finish();
    }
}
