package com.example.day9_lianxice.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.day9_lianxice.R;

public class CangFragment extends Fragment {
    private WebView mWeb;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.activity_cang, null);
        initView(inflate);
        return inflate;
    }

    private void initView(@NonNull final View itemView) {
        mWeb = (WebView) itemView.findViewById(R.id.web);
        mWeb.loadUrl("https://www.baidu.com/");
        mWeb.setWebViewClient(new WebViewClient());
    }
}
