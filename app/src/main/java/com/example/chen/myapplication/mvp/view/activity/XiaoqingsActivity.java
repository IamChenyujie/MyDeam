package com.example.chen.myapplication.mvp.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.chen.myapplication.R;
import com.example.chen.myapplication.mvp.modle.bean.Leibiao;
import com.example.chen.myapplication.mvp.view.adapter.MyAdpter;
import com.google.gson.Gson;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;

/**
 * 时间: 2017/9/8 14:35
 * 作者: 陈宇杰
 * 作用:
 */

public class XiaoqingsActivity extends BaseActivity {
    private ImageView mXqIm;
    private ImageView mXqIamge;
    private TextView mXqName;
    private TextView mXqNum;
    private TextView mXqMai;
    private TextView mTextView;
    private ListView mXqLv;
    private List<Leibiao.DatasBean.GoodsListBean> list = new ArrayList<>();
    private ImageLoader imageLoader;
    private DisplayImageOptions options;
    private int num = 1;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.xiaoqing_main);
        initView();
        initData();
        mXqLv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(XiaoqingsActivity.this, XiaoqingActivity.class);
                intent.putExtra("num",position);
                startActivity(intent);
            }
        });
    }
    private void initData() {
        loadData();

    }

    private void initView() {
        mXqIm = (ImageView) findViewById(R.id.xq_im);
        mXqIamge = (ImageView) findViewById(R.id.xq_iamge);
        mXqName = (TextView) findViewById(R.id.xq_name);
        mXqNum = (TextView) findViewById(R.id.xq_num);
        mXqMai = (TextView) findViewById(R.id.xq_mai);
        mTextView = (TextView) findViewById(R.id.textView);
        mXqLv = (ListView) findViewById(R.id.xq_lv);
        mXqIm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        Intent intent = getIntent();
        num = intent.getIntExtra("num",0);
    }

    private void loadData() {
        OkHttpUtils.get().url("http://169.254.76.109/mobile/index.php?act=goods&op=goods_list&page=100")
                .build().execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {

            }

            @Override
            public void onResponse(String response, int id) {
                Gson g = new Gson();
                Leibiao leibiao = g.fromJson(response, Leibiao.class);
                list.addAll(leibiao.getDatas().getGoods_list());
                Glide.with(XiaoqingsActivity.this).load(list.get(num).getGoods_image_url()).into(mXqIamge);
                mXqName.setText(list.get(num).getGoods_name());
                mXqNum.setText("￥"+ list.get(num).getGoods_price());
                mXqMai.setText(list.get(num).getGoods_salenum());
                list.remove(num);
                MyAdpter myAdpter = new MyAdpter(XiaoqingsActivity.this,list);
                mXqLv.setAdapter(myAdpter);
            }
        });
    }
}
