package com.zd.study.handler;

import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.zd.study.R;
import com.zd.study.utils.Utils;

public class HandlerActivity extends AppCompatActivity implements View.OnClickListener {
    public static String TAG = "MyThreadHandlerActivity";
    private TextView mTxtTest;
    private Button mBtn1;
    private Button mBtn2;
    private Button mBtn3;

    private HandlerThread mHandlerThread;
    private Handler mWorkHandler;
    private MyThread workThread;
    private Handler workHandler2;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_layout_handler);
        Log.d(TAG," onCreate  pid = " + Utils.getPid());

        mTxtTest = findViewById(R.id.txt_test);
        mBtn1 = findViewById(R.id.btn_1);
        mBtn2 = findViewById(R.id.btn_2);
        mBtn3 = findViewById(R.id.btn_3);

        mBtn1.setOnClickListener(this);
        mBtn2.setOnClickListener(this);
        mBtn3.setOnClickListener(this);

        mHandlerThread = new HandlerThread("handlerThread",999){
            @Override
            protected void onLooperPrepared() {
                super.onLooperPrepared();
                Log.d(TAG,"  onLooperPrepared  ******   " + Utils.getPid());
            }
        };

        mHandlerThread.start();


        mWorkHandler = new Handler(mHandlerThread.getLooper()){
            @Override
            public void handleMessage(@NonNull Message msg) {
                super.handleMessage(msg);
                Log.d(TAG, "    workHandler,  handleMessage  " + Utils.getPid());
                switch (msg.what){
                    case 1:
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                        break;
                }
            }
        };

        workThread = new MyThread("workThread");
        workThread.start();

        Looper mLooper = workThread.getLooper();

        Log.d(TAG, "workThread.getLooper()   " + mLooper + "   MainLooper  " + getMainLooper()
                + "  mHandlerThread.getLooper  " + mHandlerThread.getLooper());

        workHandler2 = new Handler(mLooper){
            @Override
            public void handleMessage(@NonNull Message msg) {
                super.handleMessage(msg);

                Log.d(TAG, "workHandler2  handleMessage    " + Utils.getPid());

                switch (msg.what){
                    case 1:
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                        mTxtTest.setText((String)msg.obj);
                        break;
                }

            }
        };
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id){
            case R.id.btn_1:
//                Message msg1 = Message.obtain();
//                msg1.what = 1;
//                msg1.obj = "HandlerThread 刷新UI：你是猪猪";
//                mWorkHandler.sendMessage(msg1);
                Looper mLooper = workThread.getLooper();
                Log.d(TAG, "88888888888888workThread.getLooper()   " + mLooper + "   MainLooper  " + getMainLooper()
                        + "  mHandlerThread.getLooper  " + mHandlerThread.getLooper());
                break;
            case  R.id.btn_2:

                Message msg2 = Message.obtain();
                msg2.what =1;
                msg2.obj = "普通的Threads刷新UI:我要吃肉肉";
                workHandler2.sendMessage(msg2);

                break;

            case  R.id.btn_3:

                Message msg3 = Message.obtain();
                msg3.what =1;
                msg3.obj = "普通的Threads刷新UI:我要吃肉肉";
                workThread.mHandler.sendMessage(msg3);

                break;
        }

    }
}
