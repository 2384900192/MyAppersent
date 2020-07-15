package com.example.day9_lianxice.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.day9_lianxice.MainActivity;
import com.example.day9_lianxice.R;
import com.example.day9_lianxice.callback.ApkInstallUtil;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class DownloadActivity extends AppCompatActivity implements View.OnClickListener {

    private Toolbar mToolbar;
    private ProgressBar mPb1;
    private TextView mHx1Tv;
    private Button mXia;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_download);
        initView();
    }
    private void initView() {
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mPb1 = (ProgressBar) findViewById(R.id.pb1);
        mHx1Tv = (TextView) findViewById(R.id.tv_hx1);
        mXia = (Button) findViewById(R.id.xia);
        mXia.setOnClickListener(this);

        mToolbar.setTitle("下载");
        setSupportActionBar(mToolbar);
        TextView childAt = (TextView) mToolbar.getChildAt(0);
        childAt.getLayoutParams().width= LinearLayout.LayoutParams.MATCH_PARENT;
        childAt.setGravity(Gravity.CENTER);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.xia:
                // TODO 20/07/15
                initDownLoad();
                break;
            default:
                break;
        }
    }

    private void initDownLoad() {
        OkHttpClient okHttpClient = new OkHttpClient.Builder().build();
        Request request = new Request.Builder()
                .url("https://alissl.ucdl.pp.uc.cn/fs08/2019/07/05/1/110_17e4089aa3a4b819b08069681a9de74b.apk")
                .build();
        //异步调用,不用再新建线程了
        Call call = okHttpClient.newCall(request);

        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.e("11", "onFailure: "+e.getMessage());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
//保存文件
                long contentLength = response.body().contentLength();
                InputStream inputStream = response.body().byteStream();
                String path = Environment.getExternalStorageDirectory() + "/ban.apk";
                File file = new File(path);
                if (!file.exists()){
                    file.createNewFile();
                }
                //读写的进度
                FileOutputStream outputStream = new FileOutputStream(file);
                int readLength;
                long currLength = 0;
                byte[] bytes = new byte[1024];
                //输出流
                while ((readLength=inputStream.read(bytes))!=-1){
                    outputStream.write(bytes,0,readLength);
                    currLength+=readLength;
                    //传递当前读写的进度
                    final int progress = (int) (currLength*100/contentLength);
                    mPb1.setProgress(progress);
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {

                            mHx1Tv.setText("当前下载进度"+progress+"%");
                        }
                    });
                }

                //完成写入
                outputStream.close();
                inputStream.close();

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        Toast.makeText(DownloadActivity.this, "11", Toast.LENGTH_SHORT).show();

                        ApkInstallUtil.installApk(DownloadActivity.this, Environment.getExternalStorageDirectory() + "/ban.apk");
                    }
                });

            }
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == ApkInstallUtil.UNKNOWN_CODE) {
            ApkInstallUtil.installApk(this,Environment.getExternalStorageDirectory() + "/ban.apk");//鍐嶆鎵ц瀹夎娴佺▼锛屽寘鍚潈闄愬垽绛?
        }

    }
}
