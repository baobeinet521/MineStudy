package com.zd.study.handler;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;

import androidx.annotation.NonNull;

import com.zd.study.utils.Utils;

public class MyThread extends Thread{
    private static final String TAG = "MyThread";
    public Handler mHandler = null;
    private Looper mLooper;

    public MyThread(String name){
        super(name);
    }

    @Override
    public void run() {
        super.run();
        Log.d(TAG, "run: &&&&&&&&&&&&&&&&");
        Looper.prepare();
        mLooper = getLooper();
        Log.d(TAG, " MyThread ,Looper " + mLooper);
        Log.d(TAG, " MyThread ,run " + Utils.getPid());
        mHandler = new Handler(Looper.myLooper()) {
            @Override
            public void handleMessage(@NonNull Message msg) {
                super.handleMessage(msg);
                Log.d(TAG, "MyThread ,handleMessage  " + Utils.getPid());
                Log.d(TAG,"MyThread ,handleMessage == " + msg);
            }
        };
        Looper.loop();
    }

    public Looper getLooper(){
        if (mLooper != null) {
            Log.d(TAG, " getLooper -----if-----  ");
            return  mLooper;
        }else{
            Log.d(TAG, " getLooper ----else------  ");
            return  Looper.myLooper();
        }

    }
}
