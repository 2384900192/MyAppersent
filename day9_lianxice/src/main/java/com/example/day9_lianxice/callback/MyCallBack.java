package com.example.day9_lianxice.callback;

import com.example.day9_lianxice.bean.MyBean;

public interface MyCallBack {
    void onSuner(MyBean myBean);
    void onFail(String str);
}
