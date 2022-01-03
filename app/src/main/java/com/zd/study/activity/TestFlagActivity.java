package com.zd.study.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.util.Log;
import android.view.View;

import com.zd.study.R;
import com.zd.study.service.ServiceTestActivity;

public class TestFlagActivity extends AppCompatActivity {
    public static String TAG = "TestActivityFlag";

    private AppCompatButton mButton;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.acticity_test_flag_layout);
        mButton = findViewById(R.id.go_to_one_Activity);
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TestFlagActivity.this, ServiceTestActivity.class);
                startActivity(intent);
            }
        });
    }
}
