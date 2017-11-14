package com.example.chen.myapplication.mvp.view.IView;

/**
 * 时间: 2017/11/9 13:21
 * 作者: 陈宇杰
 * 作用:
 */

public interface IMainView<T> {
    void successCallback(T t);
    void errCallback(String msg,int code);
}
