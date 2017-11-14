package com.example.chen.myapplication.mvp.presenter;

import com.example.chen.myapplication.mvp.view.IView.ISecondView;

/**
 * 时间: 2017/11/9 13:20
 * 作者: 陈宇杰
 * 作用:
 */

public class SecondPresenter extends BasePresenter<ISecondView>{

//    private final HttpUtils httpUtils;
//
//    public SecondPresenter() {
//        // IMainView mIMainView = MainActivity
//
//        httpUtils = new HttpUtils();
//    }


    public <T>void loadDataFromServer(String url,Class<T> t) {
//        httpUtils.loadDataFromServer(url, new NetDataCallback<T>() {
//            @Override
//            public void callback(T o) {
//                getMvpView().successCallbackSecond(o);
//            }
//
//            @Override
//            public void err(int errCode, String errMsg) {
//                getMvpView().errCallbackSecond(errMsg,errCode);
//            }
//        }, t);
    }
}
