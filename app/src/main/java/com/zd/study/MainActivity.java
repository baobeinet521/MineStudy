package com.zd.study;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.zd.study.R;
import com.zd.study.activity.LifeCycleActivity;
import com.zd.study.handler.HandlerActivity;
import com.zd.study.kotlin.StudyKotlinActivity;
import com.zd.study.touchevent.TouchEventTestActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button mTestBtn;
    private Button mTouchEventTestBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        mTestBtn = findViewById(R.id.test_btn);
//        mTestBtn.setOnClickListener(this);
        mTouchEventTestBtn = findViewById(R.id.touch_event_btn);
        mTouchEventTestBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        Intent intent = new Intent();
        switch (id){
            case R.id.test_btn:
//                Intent intent = new Intent(this, HandlerActivity.class);
//                Intent intent = new Intent(this, LifeCycleActivity.class);
                intent.setClass(this, StudyKotlinActivity.class);

                break;
            case R.id.touch_event_btn:
                intent.setClass(this, TouchEventTestActivity.class);
                break;
        }
        startActivity(intent);
    }
}