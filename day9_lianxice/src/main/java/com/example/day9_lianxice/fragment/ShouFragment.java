package com.example.day9_lianxice.fragment;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.day9_lianxice.R;
import com.example.day9_lianxice.activity.DownloadActivity;
import com.example.day9_lianxice.adapter.MyAdapter;
import com.example.day9_lianxice.base.BaseFragment;
import com.example.day9_lianxice.bean.MyBean;
import com.example.day9_lianxice.presenter.MyPresenter;
import com.example.day9_lianxice.view.MyView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class ShouFragment extends BaseFragment<MyPresenter> implements MyView {
    @BindView(R.id.recycler)
    RecyclerView mRecycler;
    private List<MyBean.DataBean.DatasBean> list;
    private MyAdapter myAdapter;

    @Override
    protected void initView() {
        mRecycler.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecycler.addItemDecoration(new DividerItemDecoration(getActivity(), LinearLayout.VERTICAL));

        list = new ArrayList<>();
        myAdapter = new MyAdapter(getActivity(), list);
        mRecycler.setAdapter(myAdapter);

        myAdapter.setModelerten(new MyAdapter.Modelerten() {
            @Override
            public void post(int i) {
                Intent intent = new Intent(getActivity(), DownloadActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void initData() {
        mPresenter.setString();
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initPresenter() {
        mPresenter=new MyPresenter();
    }

    @Override
    protected int setLayout() {
        return R.layout.activity_shou;
    }

    @Override
    public void onData(MyBean myBean) {
        List<MyBean.DataBean.DatasBean> datas = myBean.getData().getDatas();
        list.addAll(datas);
        myAdapter.notifyDataSetChanged();
    }

    @Override
    public void showToast(String string) {
        Toast.makeText(getActivity(), string, Toast.LENGTH_SHORT).show();
    }
}
