package com.example.day9_lianxice.presenter;

import com.example.day9_lianxice.base.BasePresenter;
import com.example.day9_lianxice.bean.MyBean;
import com.example.day9_lianxice.callback.MyCallBack;
import com.example.day9_lianxice.model.MyModel;
import com.example.day9_lianxice.view.MyView;

public class MyPresenter extends BasePresenter<MyView> implements MyCallBack {

    private MyModel myModel;

    @Override
    protected void initModel() {
        myModel = new MyModel();
        addModel(myModel);
    }

    public void setString(){
        myModel.setData(this);
    }
    @Override
    public void onSuner(MyBean myBean) {
        mView.onData(myBean);
    }

    @Override
    public void onFail(String str) {
        mView.showToast(str);
    }
}
