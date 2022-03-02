package com.zd.study;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.zd.study.R;
import com.zd.study.activity.LifeCycleActivity;
import com.zd.study.activity.TestFlagActivity;
import com.zd.study.broadcast.TestBroadcastReceiverActivity;
import com.zd.study.handler.HandlerActivity;
import com.zd.study.kotlin.StudyKotlinActivity;
import com.zd.study.service.ServiceTestActivity;
import com.zd.study.touchevent.TouchEventTestActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    public static String TAG = "TestActivity";
    private Button mTestBtn;
    private Button mTouchEventTestBtn;
    private Button mServiceTestBtn;
    private Button mBroadcastReceiverBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTestBtn = findViewById(R.id.test_activity_btn);
        mTestBtn.setOnClickListener(this);
        mTouchEventTestBtn = findViewById(R.id.touch_event_btn);
        mTouchEventTestBtn.setOnClickListener(this);
        mServiceTestBtn = findViewById(R.id.service_test_btn);
        mServiceTestBtn.setOnClickListener(this);
        mBroadcastReceiverBtn = findViewById(R.id.broadcast_test_btn);
        mBroadcastReceiverBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        Intent intent = new Intent();
        switch (id){
            case R.id.test_activity_btn:
//                Intent intent = new Intent(this, HandlerActivity.class);
//                Intent intent = new Intent(this, LifeCycleActivity.class);
//                Intent intentTest = new Intent();
//                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                intent.setClass(this, TestFlagActivity.class);
//                startActivity(intentTest);
                SingleInstanceD mSingleInstanceD = SingleInstanceD.getInstance();
                System.out.println("_____分割线_______");
                mSingleInstanceD.testPrint();


                break;
            case R.id.touch_event_btn:
                intent.setClass(this, TouchEventTestActivity.class);
                break;
            case R.id.service_test_btn:
                intent.setClass(this, ServiceTestActivity.class);
                break;
            case R.id.broadcast_test_btn:
                intent.setClass(this, TestBroadcastReceiverActivity.class);
                break;
        }
        startActivity(intent);
    }
}