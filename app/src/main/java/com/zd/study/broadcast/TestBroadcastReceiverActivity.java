package com.zd.study.broadcast;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import com.zd.study.R;

public class TestBroadcastReceiverActivity extends AppCompatActivity implements View.OnClickListener {
    public static String TAG = "testBroadcast";
    private AppCompatButton mSysBroadcast;
    private AppCompatButton mOrderBroadcast;
    private AppCompatButton mNoOrderBroadcast;
    private AppCompatButton mStickOrderBroadcast;
    private AppCompatButton mLocalBroadcast;
    private BroadcastReceiverOne mBroadcastReceiverOne;
    private BroadcastReceiverTwo mBroadcastReceiverTwo;
    private LocalBroadCastReceiverTest mLocalBroadcastReceiver;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_broadcastreceiver_layout);
        mSysBroadcast = findViewById(R.id.sys_broadcast_receiver);
        mSysBroadcast.setOnClickListener(this);
        mOrderBroadcast = findViewById(R.id.order_broadcast_receiver);
        mOrderBroadcast.setOnClickListener(this);
        mNoOrderBroadcast = findViewById(R.id.no_order_broadcast_receiver);
        mNoOrderBroadcast.setOnClickListener(this);
        mStickOrderBroadcast = findViewById(R.id.stick_broadcast_receiver);
        mStickOrderBroadcast.setOnClickListener(this);
        mLocalBroadcast = findViewById(R.id.local_broadcast_receiver);
        mLocalBroadcast.setOnClickListener(this);

        mBroadcastReceiverOne = new BroadcastReceiverOne();
        mBroadcastReceiverTwo = new BroadcastReceiverTwo();
        mLocalBroadcastReceiver = new LocalBroadCastReceiverTest();

        registerBroadcast();
        registerBroadcastTwo();
        registerLocalBroadCastReceiver();

    }

    public void registerBroadcast(){

        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("order_broadcast_receiver");
        registerReceiver(mBroadcastReceiverOne,intentFilter);
    }

    public void registerBroadcastTwo(){

        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("order_broadcast_receiver");
        registerReceiver(mBroadcastReceiverTwo,intentFilter);
    }

    public void registerLocalBroadCastReceiver(){
        LocalBroadcastManager mManager = LocalBroadcastManager.getInstance(this);
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("local_broadcast_receiver");
        mManager.registerReceiver(mLocalBroadcastReceiver,intentFilter);

    }
    @Override
    public void onClick(View v) {
        int id = v.getId();
        if(id == R.id.sys_broadcast_receiver){

        } else if (id == R.id.order_broadcast_receiver) {
            Intent intent = new Intent();
            intent.setAction("order_broadcast_receiver");
            intent.putExtra("test1","这是有序广播原始数据====");
            sendOrderedBroadcast(intent,null);
        }else if (id == R.id.no_order_broadcast_receiver){

        }else if (id == R.id.stick_broadcast_receiver){

        }else if (id == R.id.local_broadcast_receiver){
            Intent localIntent = new Intent();
            localIntent.setAction("local_broadcast_receiver");
            localIntent.putExtra("test_local","这是本地广播原始数据====");
            LocalBroadcastManager.getInstance(this).sendBroadcast(localIntent);
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(mBroadcastReceiverOne != null){
            unregisterReceiver(mBroadcastReceiverOne);
        }
        if(mBroadcastReceiverTwo != null){
            unregisterReceiver(mBroadcastReceiverTwo);
        }
        LocalBroadcastManager.getInstance(this).unregisterReceiver(mLocalBroadcastReceiver);

    }
}
