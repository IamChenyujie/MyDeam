package com.example.chen.myapplication.mvp.view.activity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.widget.ProgressBar;
import android.widget.Toast;

/**
 * 时间: 2017/9/4 18:59
 * 作者: 陈宇杰
 * 作用:
 */

public class BaseActivity extends AppCompatActivity {
    private AlertDialog alert;
    private ProgressBar progressBar02;

    //对话框,自带的布局样式
    public void alertDialog(Context context, int drawable) {
        alert = new AlertDialog.Builder(context).setTitle("提示")
                .setIcon(drawable)
                .setMessage("确认要退出吗?").setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        alert.dismiss();
                    }
                }).setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                }).create();
    }

    //跳转下一个页面,并可以返回这个页面
    public void inTent(Context context, Class c) {
        Intent intent = new Intent(context, c);
        startActivity(intent);
    }

    //跳转下一个页面,并关闭这个页面
    public void inTentFinsh(Context context, Class c) {
        Intent intent = new Intent(context, c);
        startActivity(intent);
        finish();

    }

    //吐司
    public void toAst(Context context, String string) {
        Toast.makeText(context, string, Toast.LENGTH_SHORT).show();
    }
}
