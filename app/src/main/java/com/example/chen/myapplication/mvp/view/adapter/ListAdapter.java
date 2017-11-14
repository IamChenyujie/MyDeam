package com.example.chen.myapplication.mvp.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.chen.myapplication.R;
import com.example.chen.myapplication.mvp.modle.bean.Soushuo;

import java.util.List;

/**
 * 时间: 2017/9/4 19:18
 * 作者: 陈宇杰
 * 作用:
 */

public class ListAdapter extends BaseAdapter {
    private List<Soushuo> mList;
    private Context mContext;
    private LayoutInflater layoutInflater;

    public ListAdapter(Context context) {
        mContext = context;
        layoutInflater = LayoutInflater.from(mContext);
    }

    // 设置数据
    public void setData(List<Soushuo> list) {
        this.mList = list;
        //提醒Adapter刷新当前view
        notifyDataSetChanged();
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
            convertView = layoutInflater.inflate(R.layout.item_soushuo, null);
            viewHoler = new ViewHolder();
            viewHoler.sstext=(TextView) convertView.findViewById(R.id.sstext);
            viewHoler.sstexts=(TextView) convertView.findViewById(R.id.sstexts);
            convertView.setTag(viewHoler);
        } else {
            // 当convertView！=null的时候 复用convertView 减少耗时
            //当convertView！=null的时候 从convertView上取出标签 拿到ViewHoler对象
            viewHoler = (ViewHolder) convertView.getTag();
        }
        //终极优化 优化findViewById findViewById也是一个比较耗时方法
        // 获取list中的数据
        Soushuo soushuo = mList.get(position);
        // 设置数据
        viewHoler.sstext.setText(soushuo.getName());
        viewHoler.sstexts.setText(soushuo.getLeixing());
        // 返回当前view
        return convertView;
    }

    // view的持有者
    class ViewHolder {
        TextView sstext,sstexts;
    }
}

