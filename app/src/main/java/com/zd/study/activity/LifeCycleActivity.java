package com.zd.study.activity;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.zd.study.R;

/**
 * Activity 横竖屏切换  生命周期
 */
public class LifeCycleActivity extends AppCompatActivity {

    public static String TAG = "LifeCycleActivity";

    private Button mTest;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lifecycle_layout);
        Log.d(TAG, "------------onCreate---------");
        mTest = findViewById(R.id.test);

        mTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LifeCycleActivity.this, TestFlagActivity.class);
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
