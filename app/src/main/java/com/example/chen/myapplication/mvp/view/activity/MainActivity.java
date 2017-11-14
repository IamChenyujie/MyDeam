package com.example.chen.myapplication.mvp.view.activity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.TextView;

import com.example.chen.myapplication.R;

public class MainActivity extends BaseActivity {

    private TextView tv;
    private int time = 5;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == 0) {
                time--;
                tv.setText("还有" + time + "秒");
                sendEmptyMessageDelayed(0, 1000);
                if (time == 0) {
                    inTentFinsh(MainActivity.this, HomeActivity.class);
                }
            }
        }
    };



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv = (TextView) findViewById(R.id.tiao);
//        quxiao();
        handler.sendEmptyMessageDelayed(0, 1000);

        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inTentFinsh(MainActivity.this, HomeActivity.class);
                handler.removeMessages(0);
            }
        });
    }
}
