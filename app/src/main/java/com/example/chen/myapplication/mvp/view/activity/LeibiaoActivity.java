package com.example.chen.myapplication.mvp.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;

import com.example.chen.myapplication.R;
import com.example.chen.myapplication.mvp.modle.bean.Leibiao;
import com.example.chen.myapplication.mvp.presenter.MainPresenter;
import com.example.chen.myapplication.mvp.view.IView.IMainView;
import com.example.chen.myapplication.mvp.view.adapter.MyAdpter;

import java.util.ArrayList;
import java.util.List;

/**
 * 时间: 2017/9/7 16:01
 * 作者: 陈宇杰
 * 作用:
 */
public class LeibiaoActivity extends BaseActivity implements IMainView<Leibiao>{
    private ImageView mShaomiaoa;
    /**
     * 搜索一下
     */
    private MyAdpter myAdpter;
    private EditText mSousuoa;
    private ImageView mLiaotiana;
    private ListView mLeibiaoLv;
    private String urls = "http://169.254.76.109/mobile/index.php?act=goods&op=goods_list&page=100";
    private List<Leibiao.DatasBean.GoodsListBean> list = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.leibiao_main);
        initView();
//        initData();
        MainPresenter mainPresenter =  new MainPresenter();
        mainPresenter.attachView(this);
        myAdpter = new MyAdpter(LeibiaoActivity.this,list);
        mainPresenter.loadDataFromServer(urls, Leibiao.class);
        mLeibiaoLv.setAdapter(myAdpter);
        mLeibiaoLv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(LeibiaoActivity.this,XiaoqingActivity.class);
                intent.putExtra("num",i);
                startActivity(intent);
            }
        });
    }

//    private void initData() {
//        OkHttpUtils.get().url("http://169.254.76.109/mobile/index.php?act=goods&op=goods_list&page=100")
//                .build().execute(new StringCallback() {
//            @Override
//            public void onError(Call call, Exception e, int id) {
//
//            }
//            @Override
//            public void onResponse(String response, int id) {
//                Gson g = new Gson();
//                Leibiao leibiao = g.fromJson(response, Leibiao.class);
//                list.addAll(leibiao.getDatas().getGoods_list());
//                MyAdpter myAdpter = new MyAdpter(LeibiaoActivity.this,list);
//                mLeibiaoLv.setAdapter(myAdpter);
//            }
//        });
//
//    }

    private void initView() {
        mShaomiaoa = (ImageView) findViewById(R.id.shaomiaoa);
        mSousuoa = (EditText) findViewById(R.id.sousuoa);
        mLiaotiana = (ImageView) findViewById(R.id.liaotiana);
        mLeibiaoLv = (ListView) findViewById(R.id.leibiao_lv);
        mSousuoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LeibiaoActivity.this, SoushuoActivity.class);
                startActivity(intent);
            }
        });

    }

    @Override
    public void successCallback(Leibiao leibiao) {
        list = leibiao.getDatas().getGoods_list();
        myAdpter = new MyAdpter(LeibiaoActivity.this,list);
        mLeibiaoLv.setAdapter(myAdpter);
    }

    @Override
    public void errCallback(String msg, int code) {

    }
}
