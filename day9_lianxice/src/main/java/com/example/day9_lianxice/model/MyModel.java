package com.example.day9_lianxice.model;

import com.example.day9_lianxice.base.BaseModel;
import com.example.day9_lianxice.bean.MyBean;
import com.example.day9_lianxice.callback.ApiServer;
import com.example.day9_lianxice.callback.MyCallBack;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class MyModel extends BaseModel {

    public void setData(final MyCallBack myCallBack){
        Retrofit build = new Retrofit.Builder()
                .baseUrl(ApiServer.utl)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        ApiServer apiServer = build.create(ApiServer.class);
        Observable<MyBean> data = apiServer.getData();
        data.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<MyBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        addDisposable(d);
                    }

                    @Override
                    public void onNext(MyBean myBean) {
                        myCallBack.onSuner(myBean);
                    }

                    @Override
                    public void onError(Throwable e) {
                        myCallBack.onFail(e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
