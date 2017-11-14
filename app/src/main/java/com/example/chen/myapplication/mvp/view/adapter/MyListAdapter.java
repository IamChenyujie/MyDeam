package com.example.chen.myapplication.mvp.view.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.bumptech.glide.Glide;
import com.example.chen.myapplication.R;
import com.example.chen.myapplication.mvp.modle.bean.Gouwu;
import com.example.chen.myapplication.mvp.modle.db.Dian;
import com.example.chen.myapplication.mvp.modle.db.GoumaiDao;
import com.example.chen.myapplication.mvp.modle.db.GouwuDao;

import java.util.List;

/**
 * 时间: 2017/9/12 15:46
 * 作者: 陈宇杰
 * 作用:
 */
public class MyListAdapter extends BaseAdapter {
    private List<Gouwu> mList;
    private Context mContext;
    private LayoutInflater layoutInflater;

    public MyListAdapter(Context context, List<Gouwu> list) {
        mContext = context;
        this.mList = list;
        layoutInflater = LayoutInflater.from(mContext);
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
    public View getView(final int position, View convertView, ViewGroup parent) {

        final ViewHolder holder;
        if (convertView == null) {
            convertView = convertView.inflate(mContext, R.layout.iten_gw, null);
            holder = new ViewHolder();
            holder.gw_num = (TextView) convertView.findViewById(R.id.gw_num);
            holder.jian = (TextView) convertView.findViewById(R.id.jian);
            holder.jia = (TextView) convertView.findViewById(R.id.jia);
            holder.gw_pr = (TextView) convertView.findViewById(R.id.gw_pr);
            holder.gw_name = (TextView) convertView.findViewById(R.id.gw_name);
            holder.gw_ck = (CheckBox) convertView.findViewById(R.id.gw_ck);
            holder.gw_im = (ImageView) convertView.findViewById(R.id.gw_im);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        final Gouwu gouwu = mList.get(position);
        holder.gw_num.setText("" + gouwu.getNum());
        holder.gw_pr.setText(gouwu.getPrice());
        holder.gw_name.setText(gouwu.getName());
        holder.gw_ck.setChecked(gouwu.isChecked());
        Glide.with(mContext).load(gouwu.getImage()).into(holder.gw_im);
        holder.gw_ck.setOnClickListener(new View.OnClickListener() {

            private GoumaiDao goumai = new GoumaiDao(mContext);
            private GouwuDao gouwuDao = new GouwuDao(mContext);

            @Override
            public void onClick(View v) {
                if (holder.gw_ck.isChecked()) {
                    List<Gouwu> gouwus = gouwuDao.finAll();
                    Gouwu gouwu = gouwus.get(position);
                    goumai.add(gouwu.getName(), gouwu.getPrice(), "", gouwu.getNum(), gouwu.getImage());

                } else {
                    gouwuDao.finAll();
                    goumai.delete(gouwu.getName());
                    Log.e("---", "添加失败");
                }
                List<Gouwu> gouwus = gouwuDao.finAll();
                List<Gouwu> gouwus1 = goumai.finAll();
                if (gouwus.size() == gouwus1.size()) {
                    Dian dian = new Dian();
                    dian.dian(1);
                }
            }
        });
        holder.jian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GouwuDao gouwuDao = new GouwuDao(mContext);
                List<Gouwu> gouwus = gouwuDao.finAll();
                Gouwu gouwu1 = gouwus.get(position);
                int i = gouwu1.getNum() - 1;
                if (i != 0) {
                    String price = gouwu.getPrice();
                    Double aDouble = Double.valueOf(price);
                    Double bouble = aDouble / gouwu1.getNum();
                    Double doubles = bouble * i;
                    gouwuDao.update(gouwu.getName(), i, doubles + "");

                    holder.gw_num.setText("" + i);
                    holder.gw_pr.setText(doubles + "");
                    holder.gw_name.setText(gouwu1.getName());
                    holder.gw_ck.setChecked(gouwu1.isChecked());
                    Glide.with(mContext).load(gouwu1.getImage()).into(holder.gw_im);
                } else {
                    Toast.makeText(mContext, "数量不能为0", Toast.LENGTH_SHORT).show();
                }
            }
        });
        holder.jia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GouwuDao gouwuDao = new GouwuDao(mContext);
                List<Gouwu> gouwus = gouwuDao.finAll();
                Gouwu gouwu1 = gouwus.get(position);
                int i = gouwu1.getNum() + 1;
                String price = gouwu1.getPrice();
                Double aDouble = Double.valueOf(price);
                Double bouble = aDouble / gouwu.getNum();
                Double doubles = bouble * i;
                gouwuDao.update(gouwu1.getName(), i, doubles + "");
                Log.e("gouwuDao", "添加成功");
                holder.gw_num.setText("" + i);
                holder.gw_pr.setText(doubles + "");
                holder.gw_name.setText(gouwu1.getName());
                holder.gw_ck.setChecked(gouwu1.isChecked());
                Glide.with(mContext).load(gouwu1.getImage()).into(holder.gw_im);
            }
        });

        return convertView;
    }

    // view的持有者
    class ViewHolder {
        TextView gw_num, gw_pr, gw_name, jian, jia;
        CheckBox gw_ck;
        ImageView gw_im;
    }
}
