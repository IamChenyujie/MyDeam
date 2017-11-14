package com.example.chen.myapplication.mvp.view.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.ListView;

import com.example.chen.myapplication.R;
import com.example.chen.myapplication.mvp.modle.bean.Expand;
import com.example.chen.myapplication.mvp.modle.bean.Expands;
import com.example.chen.myapplication.mvp.modle.bean.Fenye;
import com.example.chen.myapplication.mvp.view.activity.SoushuoActivity;
import com.example.chen.myapplication.mvp.view.adapter.FlvAdapter;
import com.example.chen.myapplication.mvp.view.adapter.MyExpandAbleAdapter;
import com.google.gson.Gson;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;

/**
 * 时间: 2017/11/10 8:14
 * 作者: 陈宇杰
 * 作用:
 */
public class Fragment2 extends Fragment {
    private MyExpandAbleAdapter adapter1 = new MyExpandAbleAdapter(getActivity(), null, null, null);
    private FlvAdapter adapter;
    private View viewRoot;
    private ListView flv_lv;
    private ExpandableListView flv_ev;
    private String currentGroup;
    private String currentGroups;
    private List<Fenye.DatasBean.ClassListBean> list = new ArrayList<>();
    private List<Expand.DatasBean.ClassListBean> ls;
    private List<Expands.DatasBean.ClassListBean> lss;
    private List<List<Expands.DatasBean.ClassListBean>> listmap;
    private int count;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        viewRoot = inflater.inflate(R.layout.fragment2, container, false);
        initView();
        adapter = new FlvAdapter(getActivity(), list);
        flv_lv.setAdapter(adapter);
        loadData();
        flv_lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                Fenye.DatasBean.ClassListBean classListBean = list.get(i);
                String gc_id = classListBean.getGc_id();
                currentGroup = gc_id;
                loadDatas(gc_id);
            }
        });

        flv_ev.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView expandableListView, View view, int i, int i1, long l) {

                return false;
            }
        });

        return viewRoot;
    }

    private void loadDatas(String i) {
        ls = new ArrayList<>();
        listmap = new ArrayList<>();
        String s = "http://169.254.76.109/mobile/index.php?act=goods_class&gc_id=" + i;
        OkHttpUtils.get().url(s)
                .build().execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {

            }

            @Override
            public void onResponse(String response, int id) {
                Gson gson = new Gson();
                Expand data = gson.fromJson(response.toString(), Expand.class);
                ls.addAll(data.getDatas().getClass_list());
                adapter1.notifyDataSetChanged();
                for (int t = 0; t < ls.size(); t++) {
                    Log.e("t",t+"");
                    flv_ev.expandGroup(t);
                    currentGroups = ls.get(t).getGc_id();
                    listmap.add(loadDatass(currentGroup, currentGroups));
                }
            }
        });
        adapter1 = new MyExpandAbleAdapter(getActivity(), ls, lss, listmap);
        flv_ev.setAdapter(adapter1);

    }

    private List loadDatass(String s, String ss) {
        final ArrayList lss = new ArrayList<>();
        String url = "http://169.254.76.109/mobile/index.php?act=goods_class&gc_id=" + s + "&gc_id=" + ss;
        OkHttpUtils.get().url(url)
                .build().execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {

            }

            @Override
            public void onResponse(String response, int id) {
                Gson gson = new Gson();
                Expands data = gson.fromJson(response.toString(), Expands.class);
                lss.addAll(data.getDatas().getClass_list());
                adapter1.notifyDataSetChanged();
            }
        });
        return lss;
    }

    private void initView() {
        EditText soushuo = (EditText) viewRoot.findViewById(R.id.sousuos);
        flv_lv = (ListView) viewRoot.findViewById(R.id.flv_lv);
        flv_ev = (ExpandableListView) viewRoot.findViewById(R.id.flv_ev);
        count = flv_ev.getCount();

        soushuo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), SoushuoActivity.class);
                startActivity(intent);
            }
        });
    }

    private void loadData() {
        OkHttpUtils.get().url("http://169.254.76.109/mobile/index.php?act=goods_class")
                .build().execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {

            }

            @Override
            public void onResponse(String response, int id) {
                Gson gson = new Gson();
                Fenye data = gson.fromJson(response.toString(), Fenye.class);
                list.addAll(data.getDatas().getClass_list());
                adapter.notifyDataSetChanged();
            }
        });
    }
}

