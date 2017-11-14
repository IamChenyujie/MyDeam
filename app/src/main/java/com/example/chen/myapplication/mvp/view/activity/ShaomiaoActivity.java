package com.example.chen.myapplication.mvp.view.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.chen.myapplication.R;
import com.example.chen.myapplication.mvp.modle.db.QRCodeUtil;


/**
 * 时间: 2017/9/14 19:44
 * 作者: 陈宇杰
 * 作用:
 */
public class ShaomiaoActivity extends BaseActivity implements View.OnClickListener {
    /**
     * 请输入要生成的二维码文字
     */
    private EditText mEtInput;
    /**
     * 生成二维码
     */
    private Button mBtnGenerate;
    private ImageView mImg;
    /**
     * 扫描二维码
     */
    private Button mBtn;
    private TextView mTextView;
    private WebView mWebView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.shaomiao_main);
        initView();
    }

    private void initView() {
        mEtInput = (EditText) findViewById(R.id.et_input);
        mBtnGenerate = (Button) findViewById(R.id.btn_generate);
        mBtnGenerate.setOnClickListener(this);
        mImg = (ImageView) findViewById(R.id.img);
        mBtn = (Button) findViewById(R.id.btn);
        mBtn.setOnClickListener(this);
        mTextView = (TextView) findViewById(R.id.tv);
        mWebView = (WebView) findViewById(R.id.wv);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_generate:
                String url = mEtInput.getText().toString().trim();
                Bitmap bitmap = QRCodeUtil.createQRImage(url, mImg.getWidth(), mImg.getHeight());
                mImg.setImageBitmap(bitmap);
                break;
            case R.id.btn:
                startActivityForResult(new Intent(this, ShaomiaosActivity.class), 1);
                break;
        }

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK){
            mTextView.setText(data.getStringExtra("text")); // 显示识别到的文字
            mWebView.loadUrl(data.getStringExtra("text")); // 将识别的内容当作网址加载到WebView
        }
    }

}
