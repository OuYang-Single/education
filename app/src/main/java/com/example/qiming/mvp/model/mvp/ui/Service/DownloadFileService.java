package com.example.qiming.mvp.model.mvp.ui.Service;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.NetworkInfo;
import android.net.wifi.WifiManager;
import android.os.Environment;
import android.os.Handler;
import android.util.Log;
import com.example.qiming.mvp.model.api.Api;
import com.example.qiming.mvp.model.di.component.DaggerDownloadFileComponent;
import com.jess.arms.base.BaseService;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;
import retrofit2.Retrofit;

import javax.inject.Inject;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

//BaseActivity
public class DownloadFileService extends BaseService {
    public static final String SD_HOME_DIR = Environment.getExternalStorageDirectory().getPath() + "/Joe/";

    public DownloadFileService() {
        DaggerDownloadFileComponent //如找不到该类,请编译一下项目
                .builder()
                .build()
                .inject(this);
    }

    Runnable mRunnable;
    @Inject
    Handler mHandler;
    @Inject
    Retrofit mRetrofit;
    BroadcastReceiver mBroadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (WifiManager.NETWORK_STATE_CHANGED_ACTION.equals(intent.getAction())) {

                NetworkInfo info = intent.getParcelableExtra(WifiManager.EXTRA_NETWORK_INFO);
                if (info.getState().equals(NetworkInfo.State.DISCONNECTED)) {

                } else if (info.getState().equals(NetworkInfo.State.CONNECTED)) {
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            download(0, -1, "", new File(SD_HOME_DIR, "download.mp4"), mObserver).subscribe(mObservers);
                        }
                    });
                }

            }
        }
    };

    private void monitorWifi() {
        IntentFilter filter = new IntentFilter();
        filter.addAction(WifiManager.WIFI_STATE_CHANGED_ACTION);
        registerReceiver(mBroadcastReceiver, filter);


    }

    Observer mObserver = new Observer() {
        @Override
        public void onSubscribe(Disposable d) {

        }

        @Override
        public void onNext(Object o) {

        }

        @Override
        public void onError(Throwable e) {

        }

        @Override
        public void onComplete() {

        }
    };
    Observer mObservers = new Observer() {
        @Override
        public void onSubscribe(Disposable d) {
            // Toast.makeText(mContext, "", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onNext(Object o) {
        }

        @Override
        public void onError(Throwable e) {
        }

        @Override
        public void onComplete() {
        }
    };

    public Observable download(@NonNull final long start, @NonNull final long end, @NonNull final String url, final File file, final Observer subscriber) {
        Log.w("", "");
        String str = "";
        if (end == -1) {
            str = "";
        } else {
            str = end + "";
        }
        return mRetrofit.create(Api.class).download("video", "mda-imjphgh9c4u871x1.mp4").subscribeOn(Schedulers.io()).unsubscribeOn(Schedulers.io()).map(new Function<ResponseBody, ResponseBody>() {
            @Override
            public ResponseBody apply(ResponseBody responseBody) throws Exception {
                return responseBody;
            }
        }).observeOn(Schedulers.io()).doOnNext(new Consumer<ResponseBody>() {
            @Override
            public void accept(ResponseBody responseBody) throws Exception {
                if (end == -1) {
                    try {
                        RandomAccessFile randomFile = new RandomAccessFile(file, "rw");
                        randomFile.setLength(responseBody.contentLength());
                        long one = responseBody.contentLength() / 3;
                        download(0, one, url, file, subscriber)
                                .mergeWith(download(one, one * 2, url, file, subscriber))
                                .mergeWith(download(one* 2, one * 3, url, file, subscriber))

                                .subscribe((Observer) subscriber);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else {
                    Log.w("", "");
                    FileUtils fileUtils = new FileUtils();
                    fileUtils.writeFile(start, end, responseBody.byteStream(), file);
                }
            }
        }).subscribeOn(AndroidSchedulers.mainThread());
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        unregisterReceiver(mBroadcastReceiver);
    }

    @Override
    public void init() {
        monitorWifi();
    }


}
