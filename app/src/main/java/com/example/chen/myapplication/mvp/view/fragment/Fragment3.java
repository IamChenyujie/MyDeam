package com.example.chen.myapplication.mvp.view.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ExpandableListView;
import android.widget.TextView;

import com.example.chen.myapplication.R;
import com.example.chen.myapplication.mvp.modle.bean.Data;
import com.example.chen.myapplication.mvp.modle.bean.Gouwu;
import com.example.chen.myapplication.mvp.modle.db.Dian;
import com.example.chen.myapplication.mvp.modle.db.GoumaiDao;
import com.example.chen.myapplication.mvp.modle.db.GouwuDao;
import com.example.chen.myapplication.mvp.view.adapter.GoodsExpandableListAdapter;

import java.util.ArrayList;
import java.util.List;


/**
 * 时间: 2017/9/4 8:14
 * 作者: 陈宇杰
 * 作用:
 */

public class Fragment3 extends Fragment {
    private ExpandableListView mFgwEx;
    private TextView mFgwTv;
    private List<List<Gouwu>> lists = new ArrayList<>();
    private List<Data> list = new ArrayList<>();
    private CheckBox mFgwCk;
    private GouwuDao gouwuDao;
    private List<Gouwu> gouwus;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View viewRoot = inflater.inflate(R.layout.fragment3, container, false);
        initView(viewRoot);

        return viewRoot;
    }

    private void initView(View viewRoot) {
        mFgwEx = (ExpandableListView) viewRoot.findViewById(R.id.fgw_ex);
        mFgwTv = (TextView) viewRoot.findViewById(R.id.fgw_tv);
        mFgwCk = (CheckBox) viewRoot.findViewById(R.id.fgw_ck);
        gouwuDao = new GouwuDao(getActivity());
        gouwus = gouwuDao.finAll();
        Dian dian = new Dian();
        dian.b(mFgwCk);
        lists.add(gouwus);
        Data data = new Data();
        data.setName("好商城V5");
        data.setCheck(false);
        list.add(data);
        GoodsExpandableListAdapter adapter = new GoodsExpandableListAdapter(getActivity(), lists, list);
        mFgwEx.setAdapter(adapter);
        mFgwEx.expandGroup(0);
        mFgwCk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                list.get(0).setCheck(true);
                GoodsExpandableListAdapter adapter = new GoodsExpandableListAdapter(getActivity(), lists, list);
                mFgwEx.setAdapter(adapter);
                mFgwEx.expandGroup(0);
            }
        });
        mFgwTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GoumaiDao goumaiDao = new GoumaiDao(getActivity());
                List<Gouwu> gouwus = goumaiDao.finAll();
                GouwuDao gouwuDao = new GouwuDao(getActivity());
                for (int i = 0; i < gouwus.size(); i++) {
                    gouwuDao.delete(gouwus.get(i).getName());
                    goumaiDao.delete(gouwus.get(i).getName());
                }
                List<Gouwu> gouwus1 = gouwuDao.finAll();
                List<List<Gouwu>> ls = new ArrayList<>();
                ls.add(gouwus1);
                GoodsExpandableListAdapter adapter = new GoodsExpandableListAdapter(getActivity(), ls, list);
                mFgwEx.setAdapter(adapter);
                mFgwEx.expandGroup(0);
            }
        });
    }
}
