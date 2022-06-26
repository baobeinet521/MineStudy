package com.zd.study.handler;

import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.zd.study.R;
import com.zd.study.utils.Utils;

public class HandlerActivity extends AppCompatActivity implements View.OnClickListener {
    public static String TAG = "MyThreadHandlerActivity";
    private TextView mTxtTest;
    private Button mBtn1;
    private Button mBtn2;
    private Button mBtn3;
    private Button mBtn4;

    private HandlerThread mHandlerThread;
    private Handler mWorkHandler;
    private MyThread workThread;
    private Handler workHandler2;
    private Handler handlerRunnable;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_layout_handler);
        Log.d(TAG," onCreate  pid = " + Utils.getPid());

        mTxtTest = findViewById(R.id.txt_test);
        mBtn1 = findViewById(R.id.btn_1);
        mBtn2 = findViewById(R.id.btn_2);
        mBtn3 = findViewById(R.id.btn_3);
        mBtn4 = findViewById(R.id.btn_4);

        mBtn1.setOnClickListener(this);
        mBtn2.setOnClickListener(this);
        mBtn3.setOnClickListener(this);
        mBtn4.setOnClickListener(this);

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

                        mTxtTest.setText((String)msg.obj);
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

        mWorkHandler.removeCallbacksAndMessages(null);

        handlerRunnable = new Handler();
        handlerRunnable.post(new Runnable() {
            @Override
            public void run() {
                Message msg = new Message();
                handlerRunnable.obtainMessage();
                Message msg1 = Message.obtain();
                mTxtTest.setText("handlerRunnable 刷新UI:我想要一个车车");
            }
        });

        
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        Message msg = Message.obtain();
        switch (id){
            case R.id.btn_1:
                msg.what = 1;
                msg.obj = "HandlerThread 刷新UI：你是猪猪";
                mWorkHandler.sendMessage(msg);
                Looper mLooper = workThread.getLooper();
                Log.d(TAG, "88888888888888workThread.getLooper()   " + mLooper + "   MainLooper  " + getMainLooper()
                        + "  mHandlerThread.getLooper  " + mHandlerThread.getLooper());
                break;
            case  R.id.btn_2:


                msg.what =1;
                msg.obj = "普通的Threads刷新UI:我要吃肉肉";
                workHandler2.sendMessage(msg);

                break;

            case  R.id.btn_3:

                msg.what =1;
                msg.obj = "普通的Threads刷新UI:我要去旅行";
                workThread.mHandler.sendMessage(msg);

                break;

            case R.id.btn_4:

                break;
        }

    }
}
