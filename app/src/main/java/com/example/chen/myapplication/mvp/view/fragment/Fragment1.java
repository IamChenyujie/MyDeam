package com.example.chen.myapplication.mvp.view.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.chen.myapplication.R;
import com.example.chen.myapplication.mvp.view.activity.ShaomiaoActivity;
import com.example.chen.myapplication.mvp.view.activity.SoushuoActivity;


/**
 * 时间: 2017/9/4 8:13
 * 作者: 陈宇杰
 * 作用:
 */

public class Fragment1 extends Fragment {

    private View viewRoot;
    private ImageView mShaomiao;
    private ImageView mLiaotian;
    private ImageView mIdBall;
    private ImageView mBall;
    int i = 1;
    int ii = 1;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        viewRoot = inflater.inflate(R.layout.fragment1, container, false);
//        initView();
        return viewRoot;
    }

    private void initView() {
        EditText soushuo = (EditText) viewRoot.findViewById(R.id.sousuo);
        soushuo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), SoushuoActivity.class);
                startActivity(intent);
            }
        });
        mShaomiao = (ImageView) viewRoot.findViewById(R.id.shaomiao);
        mLiaotian = (ImageView) viewRoot.findViewById(R.id.liaotian);
        mShaomiao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ShaomiaoActivity.class);
                startActivity(intent);
            }
        });
//        mBall = (ImageView) viewRoot.findViewById(R.id.ball);
//        mIdBall = (ImageView) viewRoot.findViewById(R.id.id_ball);
//        mIdBall.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                ObjectAnimator//
//                        .ofFloat(v, "rotationX", 0.0F, 360.0F)//
//                        .setDuration(500)//
//                        .start();
//                i++;
//                if (i % 2 == 0) {
//                    mIdBall.setImageDrawable(getResources().getDrawable(R.drawable.ab));
//                } else {
//                    mIdBall.setImageDrawable(getResources().getDrawable(R.drawable.as));
//                }
//            }
//        });
//        mBall.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                ObjectAnimator//
//                        .ofFloat(v, "rotationY", 0.0F, 360.0F)//
//                        .setDuration(500)//
//                        .start();
//                ii++;
//                if (ii % 2 == 0) {
//                    mBall.setImageDrawable(getResources().getDrawable(R.drawable.z));
//                } else {
//                    mBall.setImageDrawable(getResources().getDrawable(R.drawable.a));
//                }
//            }
//
//        });
    }

}
