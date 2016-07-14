package com.llk.okhttp.utils;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Administrator on 2016/7/12 0012.
 */
public abstract class OkHttpUtils {
    public String url;
    public OkHttpUtils(String url) {
        this.url = url;
        get();
    }
    public void get() {
        //创建OkHttpClient对象
        OkHttpClient mOkHttpClient = new OkHttpClient();
        //创建一个request
        final Request request = new Request.Builder()
                .url(url)
                .build();
        //new一个call
        Call call = mOkHttpClient.newCall(request);
        //请求加入调度
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                onRequestFailure();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String result = StringUtil.convertStreamToString(response.body().byteStream());
                onRequestSuccess(result);
            }
        });
    }

    public abstract void onRequestSuccess(String result);

    public abstract void onRequestFailure();
}
