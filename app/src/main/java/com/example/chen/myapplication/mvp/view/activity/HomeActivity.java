package com.example.chen.myapplication.mvp.view.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;

import com.example.chen.myapplication.R;
import com.example.chen.myapplication.mvp.view.fragment.Fragment1;
import com.example.chen.myapplication.mvp.view.fragment.Fragment2;
import com.example.chen.myapplication.mvp.view.fragment.Fragment3;
import com.example.chen.myapplication.mvp.view.fragment.Fragment4;


/**
 * 时间: 2017/9/4 19:03
 * 作者: 陈宇杰
 * 作用:
 */
public class HomeActivity extends BaseActivity implements View.OnClickListener {

    private EditText soushuo;
    private RadioButton rb04;
    private RadioButton rb03;
    private RadioButton rb02;
    private RadioButton rb01;
    private Fragment1 fragment1;
    private Fragment2 fragment2;
    private Fragment3 fragment3;
    private Fragment4 fragment4;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_main);
        initView();
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.add(R.id.fragment, fragment1, "fragment1");
        ft.commit();
    }

    private void initView() {
        rb01 = (RadioButton) findViewById(R.id.rb01);
        rb02 = (RadioButton) findViewById(R.id.rb02);
        rb03 = (RadioButton) findViewById(R.id.rb03);
        rb04 = (RadioButton) findViewById(R.id.rb04);
        fragment1 = new Fragment1();
        fragment2 = new Fragment2();
        fragment3 = new Fragment3();
        fragment4 = new Fragment4();
        rb01.setOnClickListener(this);
        rb02.setOnClickListener(this);
        rb03.setOnClickListener(this);
        rb04.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        switch (v.getId()) {
            case R.id.rb01:
                if (!fragment1.isAdded()) {
                    ft.add(R.id.fragment, fragment1, "fragment1");
                }
                ft.show(fragment1);
                ft.hide(fragment2);
                ft.hide(fragment3);
                ft.hide(fragment4);
                ft.commit();
                break;
            case R.id.rb02:
                if (!fragment2.isAdded()) {
                    ft.add(R.id.fragment, fragment2, "fragment2");
                }
                ft.show(fragment2);
                ft.hide(fragment1);
                ft.hide(fragment3);
                ft.hide(fragment4);
                ft.commit();
                break;
            case R.id.rb03:
                if (!fragment3.isAdded()) {
                    ft.add(R.id.fragment, fragment3, "fragment3");
                }
                ft.show(fragment3);
                ft.hide(fragment1);
                ft.hide(fragment2);
                ft.hide(fragment4);
                ft.commit();
                break;
            case R.id.rb04:
                if (!fragment4.isAdded()) {
                    ft.add(R.id.fragment, fragment4, "fragment4");
                }
                ft.show(fragment4);
                ft.hide(fragment1);
                ft.hide(fragment3);
                ft.hide(fragment2);
                ft.commit();
                break;
        }
    }
}
