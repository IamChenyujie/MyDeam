package com.example.chen.myapplication.mvp.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.chen.myapplication.R;
import com.example.chen.myapplication.mvp.modle.bean.Expands;

import java.util.List;

/**
 * 时间: 2017/9/6 13:44
 * 作者: 陈宇杰
 * 作用:
 */

public class MyGridAdapter extends BaseAdapter {
    private List<Expands.DatasBean.ClassListBean> mList;
    private Context mContext;
    private LayoutInflater layoutInflater;

    public MyGridAdapter(Context context, List<Expands.DatasBean.ClassListBean> mList) {
        mContext = context;
        this.mList = mList;
        layoutInflater = LayoutInflater.from(mContext);
    }

    @Override
    public int getCount() {
        // 返回listView中的数据条数
    		/*
    		 * if (mList != null) { return mList.size(); } else { return 0; }
    		 */
        // 三目运算符 （三元运算符）
        return mList == null ? 0 : mList.size();
    }

    @Override
    public Object getItem(int position) {
        // 根据点击的position（条目位置）返回对象
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        // 一般直接返回position
        return position;
    }

    /**
     * position ListView 中的第几个view convertView 用来复用view 尽量减少getView方法执行的时间
     * 成为listView的优化
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHoler;
        //第一步优化
        if (convertView == null) {

            // 获取view 这一步需要消耗一定的时间
            convertView = layoutInflater.inflate(R.layout.item_exgr, null);
            viewHoler=new ViewHolder();
            viewHoler.tv = (TextView) convertView.findViewById(R.id.ex_grtv);
            convertView.setTag(viewHoler);
        } else {
            // 当convertView！=null的时候 复用convertView 减少耗时
            //当convertView！=null的时候 从convertView上取出标签 拿到ViewHoler对象
            viewHoler =(ViewHolder)convertView.getTag();
        }
        //终极优化 优化findViewById findViewById也是一个比较耗时方法
        // 获取list中的数据
        Expands.DatasBean.ClassListBean classListBean = mList.get(position);
        // 设置数据
        viewHoler.tv.setText(classListBean.getGc_name());
        // 返回当前view
        return convertView;
    }
    // view的持有者
    class ViewHolder{
        TextView tv;
    }

}
