package com.example.chen.myapplication.mvp.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;

import com.example.chen.myapplication.R;
import com.example.chen.myapplication.mvp.modle.bean.Data;
import com.example.chen.myapplication.mvp.modle.bean.Gouwu;

import java.util.List;

/**
 * 时间: 2017/9/8 15:09
 * 作者: 陈宇杰
 * 作用:
 */

public class GoodsExpandableListAdapter extends BaseExpandableListAdapter {

    private Context context;
    private List<Data> list;
    private List<List<Gouwu>> lists;
    private LayoutInflater inflater;
    public GoodsExpandableListAdapter(Context context, List<List<Gouwu>> lists, List<Data> list) {
        this.context = context;
        inflater = LayoutInflater.from(context);
        this.list = list;
        this.lists = lists;
        notifyDataSetChanged();
    }

    @Override
    public int getGroupCount() {
        return null != list ? list.size() : 0;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return 1;
    }

    @Override
    public Object getGroup(int groupPosition) {
        return lists.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return lists.get(groupPosition).get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(final int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        final ViewHolder holder;
        if (convertView == null) {
            convertView = convertView.inflate(context, R.layout.item_gwex, null);
            holder = new ViewHolder();
            holder.tv = (TextView) convertView.findViewById(R.id.gwex_tv);
            holder.ck = (CheckBox) convertView.findViewById(R.id.gw_cb);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        Data data = list.get(0);
        holder.tv.setText(data.getName());
        holder.ck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<Gouwu> gouwus = lists.get(groupPosition);
                if (holder.ck.isChecked()) {
                    //选中
                    holder.ck.setChecked(true);
                    for (int i = 0; i < gouwus.size(); i++) {
                        gouwus.get(i).setChecked(true);
                    }
                } else {
                    //未选中
                    holder.ck.setChecked(false);
                    for (int i = 0; i < gouwus.size(); i++) {
                        gouwus.get(i).setChecked(false);
                    }
                }
                notifyDataSetChanged();
            }
        });
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        final ViewHolder holder;
        if (convertView == null) {
            convertView = convertView.inflate(context, R.layout.item_gwlv, null);
            holder = new ViewHolder();
            holder.lv = (ListView) convertView.findViewById(R.id.gw_ls);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        List<Gouwu> gouwus = lists.get(groupPosition);
        MyListAdapter adapter = new MyListAdapter(context, gouwus);
        holder.lv.setAdapter(adapter);
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }

    class ViewHolder {
        TextView tv;
        ListView lv;
        CheckBox ck;
    }


}
