package com.example.chen.myapplication.mvp.view.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.chen.myapplication.R;


/**
 * 时间: 2017/9/4 8:14
 * 作者: 陈宇杰
 * 作用:
 */

public class Fragment4 extends Fragment {
    private View view;
    private ImageView mHeadImageView;
    /**
     * 点击登录
     */
    private TextView mUsernameTextView;
    /**
     * 商品收藏
     */
    private TextView mCollectionGoodsTextView;
    /**
     * 店铺收藏
     */
    private TextView mCollectionStoreTextView;
    /**
     * 我的足迹
     */
    private TextView mMyFootprintTextView;
    private RelativeLayout mUserRelativeLayout;
    /**
     * 全部订单
     */
    private TextView mOrderTextView;
    /**
     * 未付款
     */
    private TextView mOrderWaitPayTextView;
    /**
     * 待发货
     */
    private TextView mOrderWaitDriveTextView;
    /**
     * 待收货
     */
    private TextView mOrderWaitReceiptTextView;
    /**
     * 待评价
     */
    private TextView mOrderWaitCommentTextView;
    /**
     * 退款/货
     */
    private TextView mOrderWaitRefundTextView;
    private LinearLayout mOrderLinearLayout;
    /**
     * 我的财产
     */
    private TextView mPropertyTextView;
    /**
     * 预存款
     */
    private TextView mPropertyMoneyTextView;
    /**
     * 充值卡
     */
    private TextView mPropertyCardTextView;
    /**
     * 代金券
     */
    private TextView mPropertyVouchersTextView;
    /**
     * 红包
     */
    private TextView mPropertyRedTextView;
    /**
     * 积分
     */
    private TextView mPropertyIntegralTextView;
    private LinearLayout mPropertyLinearLayout;
    /**
     * 收货地址
     */
    private TextView mAddressTextView;
    /**
     * 系统设置
     */
    private TextView mSettingTextView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View viewRoot = inflater.inflate(R.layout.fragment4, container, false);
        initView(viewRoot);
        return viewRoot;
    }

    private void initView(View viewRoot) {
        mHeadImageView = (ImageView) viewRoot.findViewById(R.id.headImageView);
        mUsernameTextView = (TextView) viewRoot.findViewById(R.id.usernameTextView);
        mCollectionGoodsTextView = (TextView) viewRoot.findViewById(R.id.collectionGoodsTextView);
        mCollectionStoreTextView = (TextView) viewRoot.findViewById(R.id.collectionStoreTextView);
        mMyFootprintTextView = (TextView) viewRoot.findViewById(R.id.myFootprintTextView);
        mUserRelativeLayout = (RelativeLayout) viewRoot.findViewById(R.id.userRelativeLayout);
        mOrderTextView = (TextView) viewRoot.findViewById(R.id.orderTextView);
        mOrderWaitPayTextView = (TextView) viewRoot.findViewById(R.id.orderWaitPayTextView);
        mOrderWaitDriveTextView = (TextView) viewRoot.findViewById(R.id.orderWaitDriveTextView);
        mOrderWaitReceiptTextView = (TextView) viewRoot.findViewById(R.id.orderWaitReceiptTextView);
        mOrderWaitCommentTextView = (TextView) viewRoot.findViewById(R.id.orderWaitCommentTextView);
        mOrderWaitRefundTextView = (TextView) viewRoot.findViewById(R.id.orderWaitRefundTextView);
        mOrderLinearLayout = (LinearLayout) viewRoot.findViewById(R.id.orderLinearLayout);
        mPropertyTextView = (TextView) viewRoot.findViewById(R.id.propertyTextView);
        mPropertyMoneyTextView = (TextView) viewRoot.findViewById(R.id.propertyMoneyTextView);
        mPropertyCardTextView = (TextView) viewRoot.findViewById(R.id.propertyCardTextView);
        mPropertyVouchersTextView = (TextView) viewRoot.findViewById(R.id.propertyVouchersTextView);
        mPropertyRedTextView = (TextView) viewRoot.findViewById(R.id.propertyRedTextView);
        mPropertyIntegralTextView = (TextView) viewRoot.findViewById(R.id.propertyIntegralTextView);
        mPropertyLinearLayout = (LinearLayout) viewRoot.findViewById(R.id.propertyLinearLayout);
        mAddressTextView = (TextView) viewRoot.findViewById(R.id.addressTextView);
        mSettingTextView = (TextView) viewRoot.findViewById(R.id.settingTextView);
    }
}
