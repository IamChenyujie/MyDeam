package com.example.chen.myapplication.mvp.modle.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * 时间: 2017/9/4 8:17
 * 作者: 陈宇杰
 * 作用:
 */

public class SQLiteSsDBOpenHelper extends SQLiteOpenHelper {
    public SQLiteSsDBOpenHelper(Context context) {
        super(context, "soushuo.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table soushuo (id Integer primary key autoincrement," +
                "name varchar(20),leixing varchar(20))");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
