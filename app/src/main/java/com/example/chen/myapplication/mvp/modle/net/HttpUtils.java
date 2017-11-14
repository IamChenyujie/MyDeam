package com.example.chen.myapplication.mvp.modle.net;

import android.os.Handler;
import android.os.Message;

import com.google.gson.Gson;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * 时间: 2017/11/14 18:28
 * 作者: 陈宇杰
 * 作用:
 */

public class HttpUtils {
    //NetDataCallback netDataCallback = MainActivity;
    private NetDataCallback netDataCallback;

    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 0:
                    netDataCallback.callback(msg.obj);
                    break;
            }
        }
    };

    public <T>void loadDataFromServer(String url,final NetDataCallback netDataCallback,final Class<T> clazz) {
        this.netDataCallback = netDataCallback;
        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(new LoggingInterceptor())
                .connectTimeout(10, TimeUnit.SECONDS)
                .readTimeout(20, TimeUnit.SECONDS)
                .build();

        Request request = new Request.Builder().url(url).build();

        okhttp3.Call call = client.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(okhttp3.Call call, IOException e) {

            }

            @Override
            public void onResponse(okhttp3.Call call, Response response) throws IOException {
                Message message = handler.obtainMessage();
                message.what = 0;

                Gson gson = new Gson();
                T t = gson.fromJson(response.body().string(), clazz);
                message.obj = t;
                handler.sendMessage(message);
            }

        });
    }
}
