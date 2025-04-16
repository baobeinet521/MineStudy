package com.zd.study.service;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import com.zd.study.R;

public class ServiceTestActivity extends AppCompatActivity implements View.OnClickListener {

    public static String TAG = "ZdServiceTestActivity";

    private AppCompatButton mTestStartService;
    private AppCompatButton mTestStopService;
    private AppCompatButton mTestBindService;
    private AppCompatButton mTestUnBindService;
    private AppCompatButton mTestIntentService;
    private AppCompatButton mGotoTestService;

    private boolean isBindService = false;

    private ServiceConnection mServiceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            Log.d(TAG, "onServiceConnected     componentName  = " + name);
            ServiceTest.ServiceTestBinder binder = (ServiceTest.ServiceTestBinder) service;
            binder.start();

        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            Log.d(TAG, "onServiceDisconnected    componentName  = " + name);
        }
    };


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_test_layout);

        mTestStartService = findViewById(R.id.test_start_service_btn);
        mTestStartService.setOnClickListener(this);
        mTestStopService = findViewById(R.id.test_stop_service_btn);
        mTestStopService.setOnClickListener(this);

        mTestBindService = findViewById(R.id.test_bind_service_btn);
        mTestBindService.setOnClickListener(this);

        mTestUnBindService = findViewById(R.id.test_unbind_service_btn);
        mTestUnBindService.setOnClickListener(this);

        mTestIntentService = findViewById(R.id.test_intent_service_btn);
        mTestIntentService.setOnClickListener(this);

        mTestIntentService = findViewById(R.id.test_intent_service_btn);
        mTestIntentService.setOnClickListener(this);

        mGotoTestService = findViewById(R.id.goto_test_service_btn);
        mGotoTestService.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(this, ServiceTest.class);
        int id = v.getId();
        if (id == R.id.test_start_service_btn) {
//                Log.d(TAG, " onClick start service -----");
            startService(intent);
        } else if (id == R.id.test_stop_service_btn) {
            //                Log.d(TAG, " onClick stop service -----");
            stopService(intent);
        } else if (id == R.id.test_bind_service_btn) {
//                Log.d(TAG, " onClick bind service -----");
            isBindService = bindService(intent, mServiceConnection, BIND_AUTO_CREATE);
//                Log.d(TAG, " onClick bind service -----  isBindService = " + isBindService);
        } else if (id == R.id.test_unbind_service_btn) {
//                Log.d(TAG, " onClick unbind service -----");
            if (isBindService) {
//                    Log.d(TAG, "  有已绑定服务，可以解绑 -----");
                unbindService(mServiceConnection);
                isBindService = false;
            } else {
//                    Log.d(TAG, "  没有已经绑定服务，不可以解绑 -----");
            }
        } else if (id == R.id.test_intent_service_btn) {
//                Log.d(TAG, " onClick start intentService -----");
            Intent intentSer = new Intent(this, IntentServiceTest.class);
            startService(intentSer);
        } else if (id == R.id.goto_test_service_btn) {
            Intent intentAct = new Intent(this, ServiceTestActivity.class);
            startActivity(intentAct);
        }
    }
}
