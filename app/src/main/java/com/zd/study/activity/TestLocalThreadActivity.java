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

public class TestLocalThreadActivity extends AppCompatActivity implements View.OnClickListener {
    public static String TAG = "TestLocalThreadActivity";

    private ThreadLocal<String> mThreadLocal = new ThreadLocal<>();

    private AppCompatButton mMainThreadButton;
    private AppCompatButton mThreadButton;
    private AppCompatButton mThread1Button;
    private AppCompatButton mThread2Button;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_threadlocal_layout);
        Log.e(TAG,"======onCreate====");
        Log.i(TAG, "======onCreate=====Process==id==: " + Process.myPid());
        mMainThreadButton = findViewById(R.id.test_main_thread_btn);
        mMainThreadButton.setOnClickListener(this);

        mThreadButton = findViewById(R.id.test_thread_btn);
        mThreadButton.setOnClickListener(this);

        mThread1Button = findViewById(R.id.test_thread1_btn);
        mThread1Button.setOnClickListener(this);

        mThread2Button = findViewById(R.id.test_thread2_btn);
        mThread2Button.setOnClickListener(this);

    }

    public void testMainThread(){
        mThreadLocal.set("mainThread");
        Log.e(TAG,"======mBooleanThreadLocal data====" + mThreadLocal.get());
    }

    public void testThread(){
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                mThreadLocal.set("thread");
                Log.e(TAG,"test===thread===mBooleanThreadLocal data====" + mThreadLocal.get());
            }
        });

        thread.start();

    }
    public void testThread1(){
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                mThreadLocal.set("thread1");
                Log.e(TAG,"test===thread1===mBooleanThreadLocal data====" + mThreadLocal.get());
            }
        });

        thread1.start();


    }

    public void testThread2(){

        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                mThreadLocal.set("thread2");
                Log.e(TAG,"test===thread2===mBooleanThreadLocal data====" + mThreadLocal.get());

            }
        });

        thread2.start();


    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id){
            case R.id.test_main_thread_btn:
                testMainThread();
                break;
            case R.id.test_thread_btn:
                testThread();
                break;
            case R.id.test_thread1_btn:
                testThread1();
                break;
            case R.id.test_thread2_btn:
                testThread2();
                break;
            default:
                break;
        }
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
