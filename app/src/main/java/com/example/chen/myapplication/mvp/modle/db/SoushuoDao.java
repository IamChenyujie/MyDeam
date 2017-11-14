package com.example.chen.myapplication.mvp.modle.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.chen.myapplication.mvp.modle.bean.Gouwu;
import com.example.chen.myapplication.mvp.modle.bean.Soushuo;

import java.util.ArrayList;
import java.util.List;

/**
 * 时间: 2017/9/4 8:15
 * 作者: 陈宇杰
 * 作用:
 */

public class SoushuoDao {
    private SQLiteDatabase db;

    public SoushuoDao(Context context) {
        SQLiteSsDBOpenHelper helper = new SQLiteSsDBOpenHelper(context);
        db = helper.getWritableDatabase();
    }

    public List<Gouwu> add(String name, String leixing) {
        ContentValues values = new ContentValues();
        List<Gouwu> soushuos = new ArrayList<>();
        values.put("name", name);
        values.put("leixing", leixing);
        long result = db.insert("soushuo", null, values);
        //result 不等于-1 代表插入数据库成功
        if (result != -1) {
            return soushuos;
        } else {
            return soushuos;
        }
    }

    public List<Soushuo> finAll() {
        Cursor cursor = db.query("soushuo", null, null, null, null, null, null);

        //StringBuffer sb = new StringBuffer();

        List<Soushuo> persons = new ArrayList<Soushuo>();

        while (cursor.moveToNext()) {

            String name = cursor.getString(cursor.getColumnIndex("name"));
            String phone = cursor.getString(cursor.getColumnIndex("leixing"));

            //创建对象
            Soushuo person = new Soushuo();
            person.setName(name);
            person.setLeixing(phone);

            //sb.append("姓名 : "+name+"   电话 : "+phone+"\n");
            //添加到集合
            persons.add(person);

        }
        return persons;
    }

    public boolean delete() {

        int result = db.delete("soushuo", null, null);

        if (result > 0) {
            return true;
        } else {
            return false;
        }

    }

    public List<Soushuo> findOption(int id) {

        Cursor cursor = db.query("soushuo", null, "id = ?", new String[]{String.valueOf(id)}, null, null, null);


        List<Soushuo> persons = new ArrayList<Soushuo>();

        while (cursor.moveToNext()) {
            Soushuo soushuo = new Soushuo();
            String name = cursor.getString(cursor.getColumnIndex("name"));
            String phone = cursor.getString(cursor.getColumnIndex("leixing"));
            soushuo.setName(name);
            soushuo.setLeixing(phone);
            persons.add(soushuo);
        }

        return persons;
    }

}
