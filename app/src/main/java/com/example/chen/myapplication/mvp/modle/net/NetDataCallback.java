package com.example.chen.myapplication.mvp.modle.net;


/**
 * 时间: 2017/11/14 18:29
 * 作者: 陈宇杰
 * 作用:
 */

public interface NetDataCallback<T> {
    void callback(T t);
    void err(int errCode, String errMsg);
}
