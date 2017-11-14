package com.example.chen.myapplication.mvp.modle.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.chen.myapplication.mvp.modle.bean.Gouwu;

import java.util.ArrayList;
import java.util.List;

/**
 * 时间: 2017/9/8 16:02
 * 作者: 陈宇杰
 * 作用:
 */

public class GouwuDao {
    private SQLiteDatabase db;

    public GouwuDao(Context context) {
        SQLiteGwDBOpenHelper helper = new SQLiteGwDBOpenHelper(context);
        db = helper.getWritableDatabase();
        db = helper.getReadableDatabase();
    }



    public List<Gouwu> add(String name, String price, String store_name, int num, String url) {
        ContentValues values = new ContentValues();
        List<Gouwu> soushuos = new ArrayList<>();
        values.put("name", name);
        values.put("price", price);
        values.put("store_name", store_name);
        values.put("num", num);
        values.put("url",url);
        long result = db.insert("gouwu", null, values);
        //result 不等于-1 代表插入数据库成功
        if (result != -1) {
            return soushuos;
        } else {
            return soushuos;
        }
    }
    public List<Gouwu> finAll() {
        Cursor cursor = db.query("gouwu", null, null, null, null, null, null);
//        Log.e("cursor",cursor.toString());
        List<Gouwu> persons = new ArrayList<Gouwu>();
        while (cursor.moveToNext()) {
            int id = cursor.getInt(cursor.getColumnIndex("id"));
            String name = cursor.getString(cursor.getColumnIndex("name"));
            String price = cursor.getString(cursor.getColumnIndex("price"));
            String store_name = cursor.getString(cursor.getColumnIndex("store_name"));
            int num = cursor.getInt(cursor.getColumnIndex("num"));
            String url = cursor.getString(cursor.getColumnIndex("url"));
            //创建对象
            Gouwu gouwu = new Gouwu();
            gouwu.setId(id);
            gouwu.setName(name);
            gouwu.setPrice(price);
            gouwu.setStore_name(store_name);
            gouwu.setNum(num);
            gouwu.setImage(url);
            persons.add(gouwu);

        }
        return persons;
    }

    public boolean update(String name, int num , String price){

        /**
         * 参数1：表名
         * 参数2：修改值
         * 参数3：修改的条件
         * 参数4：修改的条件值
         */
        ContentValues values = new ContentValues();

        values.put("num", num);
        values.put("price", price);
        int result = db.update("gouwu", values, "name = ?", new String[]{String.valueOf(name)});
        if(result > 0){
            return true;
        }else{
            return false;
        }

    }
    public boolean delete(String name){

        /**
         * 参数1：表明
         * 参数2：删除的条件   id
         * 参数3：删除条件值  1
         */
        int result = db.delete("gouwu", "name = ?", new String[]{String.valueOf(name)});

        if(result > 0){
            return true;
        }else{
            return false;
        }

    }
}
