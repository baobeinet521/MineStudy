package com.zd.study.activity;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.os.Process;
import android.support.annotation.NonNull;
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

    private AppCompatButton mButton1;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.acticity_test_flag_layout);
        Log.e("zdTest  ","=======TestFlagActivity======onCreate====");
        Log.i("zdTest", "=======TestFlagActivity======onCreate=====Process==id==: " + Process.myPid());
        mButton = findViewById(R.id.go_to_one_Activity);
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TestFlagActivity.this, ServiceTestActivity.class);
                startActivity(intent);
            }
        });

        mButton1 = findViewById(R.id.go_to_lifeCycle_Activity);
        mButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TestFlagActivity.this, LifeCycleActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();

        Log.d(TAG, "------------onStart---------");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(TAG, "------------onRestart---------");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "------------onResume---------");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "------------onPause---------");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "------------onStop---------");
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "------------onDestroy---------");
    }


    @Override
    public void onSaveInstanceState(@NonNull Bundle outState, @NonNull PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);

        Log.d(TAG, "------------onSaveInstanceState---------");
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        Log.d(TAG, "------------onRestoreInstanceState---------");
    }


    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        Log.d(TAG, "------------onWindowFocusChanged---------hasFocus = " + hasFocus);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        Log.d(TAG, "------------onConfigurationChanged---------");
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        Log.d(TAG, "------------onNewIntent---------");
    }
}
