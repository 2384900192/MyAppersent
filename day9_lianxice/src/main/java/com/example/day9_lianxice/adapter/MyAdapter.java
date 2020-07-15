package com.example.day9_lianxice.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.example.day9_lianxice.R;
import com.example.day9_lianxice.bean.MyBean;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter {
    Context context;
    List<MyBean.DataBean.DatasBean> list;


    public MyAdapter(Context context, List<MyBean.DataBean.DatasBean> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.activity_item, viewGroup, false);
        return new ViewHolder1(inflate);
    }
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, final int i) {
        MyBean.DataBean.DatasBean datasBean = list.get(i);
        ViewHolder1 viewHolder1 = (ViewHolder1) viewHolder;
        viewHolder1.mTitleTv.setText(datasBean.getTitle());
        RoundedCorners roundedCorners = new RoundedCorners(20);
        RequestOptions override = RequestOptions.bitmapTransform(roundedCorners).override(120, 60);
        Glide.with(context).load(datasBean.getEnvelopePic()).apply(override).into(viewHolder1.mImgIv);
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (modelerten!=null){
                    modelerten.post(i);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHolder1 extends RecyclerView.ViewHolder {
         ImageView mImgIv;
         TextView mTitleTv;
        public ViewHolder1(@NonNull View itemView) {
            super(itemView);
            mImgIv = (ImageView) itemView.findViewById(R.id.iv_img);
            mTitleTv = (TextView) itemView.findViewById(R.id.tv_title);
        }
    }
    Modelerten modelerten;

    public void setModelerten(Modelerten modelerten) {
        this.modelerten = modelerten;
    }

    public interface Modelerten{
        void post(int i);
    }
}
