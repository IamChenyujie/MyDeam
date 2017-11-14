package com.example.chen.myapplication.mvp.modle.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * 时间: 2017/9/8 15:57
 * 作者: 陈宇杰
 * 作用:
 */

public class SQLiteGwDBOpenHelper extends SQLiteOpenHelper {
    public SQLiteGwDBOpenHelper(Context context) {
        super(context, "gouwu", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table gouwu (id Integer primary key autoincrement," +
                "name varchar(20),price varchar(20),store_name varchar(20)," +
                "num Integer(20),url varchar(50))");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
