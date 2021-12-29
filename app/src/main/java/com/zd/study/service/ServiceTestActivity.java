package com.zd.study.service;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.view.View;

import com.zd.study.R;

public class ServiceTestActivity extends AppCompatActivity {

    private AppCompatButton mTestService;
    private AppCompatButton mTestIntentService;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_test_layout);

        mTestService = findViewById(R.id.test_service_btn);
        mTestIntentService = findViewById(R.id.test_intent_service_btn);

        mTestService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        mTestIntentService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }
}
