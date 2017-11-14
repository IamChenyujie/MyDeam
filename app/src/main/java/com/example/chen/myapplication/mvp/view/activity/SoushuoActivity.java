package com.example.chen.myapplication.mvp.view.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.chen.myapplication.R;
import com.example.chen.myapplication.mvp.modle.bean.Soushuo;
import com.example.chen.myapplication.mvp.modle.db.SoushuoDao;
import com.example.chen.myapplication.mvp.view.adapter.ListAdapter;

import java.util.ArrayList;
import java.util.List;

import static com.example.chen.myapplication.R.array.languages;


/**
 * 时间: 2017/9/4 19:06
 * 作者: 陈宇杰
 * 作用:
 */
public class SoushuoActivity extends BaseActivity{
    private SoushuoDao dao;
    private EditText editText;
    private ListView lv;
    private ImageView chazhao;
    private ImageView tuihou;
    private Spinner spinner;
    private List<Soushuo> soushuolist;
    private Soushuo soushuo;
    private TextView dele;
    private ListAdapter adapter;
    private int i = 0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.soushuo_main);
        initView();
        adapter = new ListAdapter(SoushuoActivity.this) {
        };
        List<Soushuo> soushuos = dao.finAll();
        adapter.setData(soushuos);
        lv.setAdapter(adapter);
        chazhao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int name = name();
                if (name == 1) {
                    soushuo();
                    soushuolist.add(soushuo);
                    dao.add(soushuo.getName(), soushuo.getLeixing());
                    List<Soushuo> soushuos = dao.finAll();

                    adapter.setData(soushuos);
                    lv.setAdapter(adapter);

                } else {
                    toAst(SoushuoActivity.this, "搜索内容不能为空");
                }
            }


        });

        dele.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dao.delete();
                List<Soushuo> soushuos = dao.finAll();
                adapter.setData(soushuos);
                lv.setAdapter(adapter);
            }
        });
        tuihou.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    private int name() {
        int i = 0;
        String s = editText.getText().toString();
        if (s.equals("")) {

        } else {
            i = 1;
            soushuo = new Soushuo();
            soushuo.setName(s);
            return i;
        }
        return i;
    }

    private void soushuo() {
        String[] mItems = getResources().getStringArray(languages);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, mItems);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int pos, long id) {
                String str = (String) spinner.getSelectedItem();
                Log.e("str", str);
                if (str.equals("宝贝")) {
                    i = 0;
                } else {
                    i = 1;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Another interface callback
            }
        });
        if (i == 1) {
            String leixing = "商品";
            soushuo.setLeixing(leixing);
        } else if (i == 0) {
            String leixing = "宝贝";
            soushuo.setLeixing(leixing);
        }
    }

    private void initView() {
        dele = (TextView) findViewById(R.id.dele);
        editText = (EditText) findViewById(R.id.sousuo);
        chazhao = (ImageView) findViewById(R.id.chazhao);
        tuihou = (ImageView) findViewById(R.id.tuihou);
        lv = (ListView) findViewById(R.id.sslt);
        spinner = (Spinner) findViewById(R.id.spinner1);
        soushuolist = new ArrayList<>();
        dao = new SoushuoDao(SoushuoActivity.this);

    }
}
