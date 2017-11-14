package com.example.chen.myapplication.mvp.presenter;

import com.example.chen.myapplication.mvp.view.IView.IMainView;

/**
 * 时间: 2017/11/9 13:19
 * 作者: 陈宇杰
 * 作用:
 */

public class MainPresenter<T> extends BasePresenter<IMainView>{

//    private final HttpUtils httpUtils;
//
//    public MainPresenter() {
//        // IMainView mIMainView = MainActivity
//        httpUtils = new HttpUtils();
//    }

    public void loadDataFromServer(String url,Class<T> t) {
//        httpUtils.loadDataFromServer(url, new NetDataCallback<T>() {
//            @Override
//            public void callback(T o) {
//                if(getMvpView()!=null) {
//                    getMvpView().successCallback(o);
//                }
//            }
//
//            @Override
//            public void err(int errCode, String errMsg) {
//                getMvpView().errCallback(errMsg,errCode);
//            }
//        }, t);

    }
}
