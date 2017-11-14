package com.example.chen.myapplication.mvp.view.IView;

/**
 * 时间: 2017/11/9 13:22
 * 作者: 陈宇杰
 * 作用:
 */

public interface ISecondView<T> {
    void successCallbackSecond(T str);
    void errCallbackSecond(String msg, int code);
}
