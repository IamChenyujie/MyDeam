package com.example.chen.myapplication.mvp.view.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseExpandableListAdapter;
import android.widget.GridView;
import android.widget.TextView;

import com.example.chen.myapplication.R;
import com.example.chen.myapplication.mvp.modle.bean.Expand;
import com.example.chen.myapplication.mvp.modle.bean.Expands;
import com.example.chen.myapplication.mvp.modle.bean.Fenye;
import com.example.chen.myapplication.mvp.view.activity.LeibiaoActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * 时间: 2017/9/5 10:10
 * 作者: 陈宇杰
 * 作用:
 */

public class MyExpandAbleAdapter extends BaseExpandableListAdapter {

    private Context context;
    private List<Expand.DatasBean.ClassListBean> Mlist;
    private List<Expands.DatasBean.ClassListBean> list;
    private List<Fenye.DatasBean.ClassListBean> ls;
    private List<List<Expands.DatasBean.ClassListBean>> lists = new ArrayList<>();
    private final Handler handler;

    public MyExpandAbleAdapter(Context context,
                               List<Expand.DatasBean.ClassListBean> Mlist,
                               List<Expands.DatasBean.ClassListBean> list,
                               List<List<Expands.DatasBean.ClassListBean>> lists) {
        this.context = context;
        this.Mlist = Mlist;
        this.list = list;
        this.lists = lists;
        handler = new Handler() {

            @Override
            public void handleMessage(Message msg) {
                notifyDataSetChanged();
                super.handleMessage(msg);
            }
        };
        notifyDataSetChanged();
    }

    public void refresh() {
        handler.sendMessage(new Message());
    }


    @Override
    public int getGroupCount() {
        return Mlist.size();
    }

    @Override
    public int getChildrenCount(int i) {
        return 1;
    }

    @Override
    public Object getGroup(int i) {
        return lists.get(i);
    }

    @Override
    public Object getChild(int i, int i1) {
        return lists.get(i).get(i1);
    }

    @Override
    public long getGroupId(int i) {
        return i;
    }

    @Override
    public long getChildId(int i, int i1) {
        return i1;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int i, boolean b, View view, ViewGroup viewGroup) {
        ViewHolder holder;
        if (view == null) {
            view = view.inflate(context, R.layout.item_expand, null);
            holder = new ViewHolder();
            holder.tv = (TextView) view.findViewById(R.id.ex_tv);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        holder.tv.setText(Mlist.get(i).getGc_name());
        return view;
    }

    @Override
    public View getChildView(int i, int i1, boolean b, View view, ViewGroup viewGroup) {

        ViewHolder holder;
        if (view == null) {
            view = viewGroup.inflate(context, R.layout.item_expands, null);
            holder = new ViewHolder();
            holder.gv = (GridView) view.findViewById(R.id.ex_gr);
            holder.gv.setNumColumns(3);
            holder.gv.setGravity(Gravity.CENTER);// 位置居中
            holder.gv.setVerticalSpacing(10);// 位置居中
            holder.gv.setVisibility(View.VISIBLE);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        List<Expands.DatasBean.ClassListBean> classListBeen = lists.get(i);
        MyGridAdapter adapter = new MyGridAdapter(context, classListBeen);
        holder.gv.setAdapter(adapter);
        holder.gv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(context, LeibiaoActivity.class);
                context.startActivity(intent);
            }
        });
        return view;
    }


    @Override
    public boolean isChildSelectable(int i, int i1) {
        return true;
    }

    class ViewHolder {
        TextView tv;
        GridView gv;
    }

}


