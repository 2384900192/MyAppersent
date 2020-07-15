package com.example.day9_lianxice.base;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class BaseFragment<P extends BasePresenter> extends Fragment implements BaseView {
    public P mPresenter;
    private Unbinder bind;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View inflate = inflater.inflate(setLayout(), null);
        bind = ButterKnife.bind(this, inflate);
        initPresenter();
        if (mPresenter!=null){
            mPresenter.bindView(this);
        }
        initView();
        initData();
        initListener();
        return inflate;
    }

    protected abstract void initView();

    protected abstract void initData();

    protected abstract void initListener();

    protected abstract void initPresenter();

    protected abstract int setLayout();

    @Override
    public void onDestroy() {
        super.onDestroy();
        bind.unbind();
        mPresenter.destroy();
        mPresenter=null;
    }
}
