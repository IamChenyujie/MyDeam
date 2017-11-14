package com.example.chen.myapplication.mvp.presenter;

/**
 * 时间: 2017/11/9 13:19
 * 作者: 陈宇杰
 * 作用:
 */

public class BasePresenter<V> {

    private V mV;

    public void attachView(V v) {
        this.mV = v;
    }

    public V getMvpView() {
        return mV;
    }

    public void dettachView() {
        mV = null;
    }
}
