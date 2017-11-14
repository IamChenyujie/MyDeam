package com.example.chen.myapplication.mvp.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.chen.myapplication.R;
import com.example.chen.myapplication.mvp.modle.bean.Gouwu;
import com.example.chen.myapplication.mvp.modle.bean.Leibiao;
import com.example.chen.myapplication.mvp.modle.db.GouwuDao;
import com.example.chen.myapplication.mvp.view.IView.IMainView;
import com.example.chen.myapplication.mvp.view.adapter.MyAdpter;
import com.google.gson.Gson;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;

/**
 * 时间: 2017/9/7 19:43
 * 作者: 陈宇杰
 * 作用:
 */
public class XiaoqingActivity extends BaseActivity implements IMainView<Gouwu>{
    private ImageView mXqIm;
    private ImageView mXqIamge;
    private TextView mXqName;
    private TextView mXqNum;
    private TextView mXqMai;
    private TextView mTextView;
    private ListView mXqLv;
    private List<Leibiao.DatasBean.GoodsListBean> list = new ArrayList<>();
    private int num = 1;
    private GouwuDao gouwuDao;
    private RadioButton mXqra;
    private List<Gouwu> been;
    private boolean a = false;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.xiaoqing_main);
        initView();
        been = loadData();
        mXqLv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(XiaoqingActivity.this, XiaoqingActivity.class);
                intent.putExtra("num",position+1);
                startActivity(intent);
            }
        });
        mXqra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<Gouwu> gouwus = gouwuDao.finAll();
                for (Gouwu gouwu : gouwus){
                    if (gouwu.getName().equals(been.get(0).getName())){
                        gouwu.setNum(gouwu.getNum()+1);
                        double num1 = 0.0;
                        num1 = Double.valueOf(gouwu.getPrice());
                        double v1 = num1 * 2;
                        Log.e("num1",v1+"");
                        gouwuDao.update(gouwu.getName(),gouwu.getNum(),v1+"");
                        a = true;
                    }
                }
                if (a){
                    toAst(XiaoqingActivity.this,"成功添加到购物车");
                }else {
                    gouwuDao.add(been.get(0).getName(),been.get(0).getPrice(),
                            been.get(0).getStore_name(),1,been.get(0).getImage());
                    Log.e("been",been.toString());
                    toAst(XiaoqingActivity.this,"成功添加到购物车");
                }
            }
        });
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
        mXqra = (RadioButton) findViewById(R.id.xq_ra);
        gouwuDao = new GouwuDao(this);
    }

    private List<Gouwu> loadData() {
        final List<Gouwu> list1 = new ArrayList<>();
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
                Glide.with(XiaoqingActivity.this).load(list.get(num).getGoods_image_url()).into(mXqIamge);
                mXqName.setText(list.get(num).getGoods_name());
                mXqNum.setText("￥"+ list.get(num).getGoods_price());
                mXqMai.setText(list.get(num).getGoods_salenum());
                Gouwu bean = new Gouwu();
                bean.setNum(1);
                bean.setName(list.get(num).getGoods_name());
                bean.setPrice(list.get(num).getGoods_price());
                bean.setStore_name(list.get(num).getStore_name());
                bean.setImage(list.get(num).getGoods_image_url());
                Leibiao.DatasBean.GoodsListBean bean1 = list.get(num);
                list1.add(bean);
                list.remove(num);
                MyAdpter myAdpter = new MyAdpter(XiaoqingActivity.this,list);
                mXqLv.setAdapter(myAdpter);
            }
        });
        return list1;
    }

    @Override
    public void successCallback(Gouwu gouwu) {

    }

    @Override
    public void errCallback(String msg, int code) {

    }
}
