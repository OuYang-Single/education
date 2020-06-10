package com.example.qiming.mvp.model.mvp.ui.activity;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import butterknife.BindView;
import com.example.qiming.R;
import com.example.qiming.mvp.model.di.component.DaggerLiveComponent;
import com.example.qiming.mvp.model.mvp.contract.LiveContract;
import com.example.qiming.mvp.model.mvp.presenter.LivePresenter;
import com.jess.arms.base.BaseActivity;
import com.jess.arms.base.delegate.IActivity;
import com.jess.arms.di.component.AppComponent;
import com.library.live.Player;
import com.library.live.stream.UdpRecive;
import com.library.live.vd.VDDecoder;
import com.library.live.view.PlayerView;

public class LiveActivity extends BaseActivity<LivePresenter> implements LiveContract.View, IActivity {
    @BindView(R.id.player_view_live)
    PlayerView mLivePlayerView;
    Player  player;
    @Override
    public void setupActivityComponent(@NonNull AppComponent appComponent) {
        DaggerLiveComponent //如找不到该类,请编译一下项目
                .builder()
                .appComponent(appComponent)
                .view(this)
                .build()
                .inject(this);
    }

    @Override
    public int initView(@Nullable Bundle savedInstanceState) {
        return R.layout.live_activity;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        player  = new Player.Buider(mLivePlayerView)
                .setPullMode(new UdpRecive(8765))
                .setVideoCode(VDDecoder.H264)//设置解码方式
                .setMultiple(1)//音频调节，倍数限制为1-8倍。1为原声,放大后可能导致爆音。
                .build();
    }

    @Override
    public void showMessage(@NonNull String message) {

    }
}
