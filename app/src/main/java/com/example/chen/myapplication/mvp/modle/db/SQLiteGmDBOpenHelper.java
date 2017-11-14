package com.example.chen.myapplication.mvp.modle.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * 时间: 2017/9/14 14:49
 * 作者: 陈宇杰
 * 作用:
 */

public class SQLiteGmDBOpenHelper extends SQLiteOpenHelper {
    public SQLiteGmDBOpenHelper(Context context) {
        super(context, "goumai", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table goumai (id Integer primary key autoincrement," +
                "name varchar(20),price varchar(20),store_name varchar(20)," +
                "num Integer(20),url varchar(50))");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}