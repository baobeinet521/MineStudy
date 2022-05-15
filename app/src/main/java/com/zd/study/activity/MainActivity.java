package com.zd.study.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.PersistableBundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.zd.study.R;
import com.zd.study.broadcast.TestBroadcastReceiverActivity;
import com.zd.study.service.ServiceTestActivity;
import com.zd.study.touchevent.TouchEventTestActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    public static String TAG = "TestActivity";
    private Button mTestBtn;
    private Button mTouchEventTestBtn;
    private Button mServiceTestBtn;
    private Button mBroadcastReceiverBtn;
    private Button mAIDLBtn;
    private Button mDrawBtn;
    private Button mTestLocalThreadBtn;

    public boolean CanShowFloat = false;

    private final int REQUEST_OVERLAY = 5004;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "------------onCreate---------");
        setContentView(R.layout.activity_main);
        mTestBtn = findViewById(R.id.test_activity_btn);
        mTestBtn.setOnClickListener(this);
        mTouchEventTestBtn = findViewById(R.id.touch_event_btn);
        mTouchEventTestBtn.setOnClickListener(this);
        mServiceTestBtn = findViewById(R.id.service_test_btn);
        mServiceTestBtn.setOnClickListener(this);
        mBroadcastReceiverBtn = findViewById(R.id.broadcast_test_btn);
        mBroadcastReceiverBtn.setOnClickListener(this);
        mAIDLBtn = findViewById(R.id.aidl_test_btn);
        mAIDLBtn.setOnClickListener(this);
        mDrawBtn = findViewById(R.id.draw_view_test_btn);
        mDrawBtn.setOnClickListener(this);
        mTestLocalThreadBtn = findViewById(R.id.local_thread_test_btn);
        mTestLocalThreadBtn.setOnClickListener(this);
    }



    @Override
    public void onClick(View v) {
        int id = v.getId();
        Intent intent = new Intent();
        switch (id){
            case R.id.test_activity_btn:
//                Intent intent = new Intent(this, HandlerActivity.class);
                 intent = new Intent(this, LifeCycleActivity.class);
//                Intent intentTest = new Intent();
//                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                intent.setClass(this, TestFlagActivity.class);
//                startActivity(intentTest);
//                SingleInstanceD mSingleInstanceD = SingleInstanceD.getInstance();
//                System.out.println("_____分割线_______");
//                mSingleInstanceD.testPrint();

                startActivity(intent);
                break;
            case R.id.touch_event_btn:
                intent.setClass(this, TouchEventTestActivity.class);
                startActivity(intent);
                break;
            case R.id.service_test_btn:
                intent.setClass(this, ServiceTestActivity.class);
                startActivity(intent);
                break;
            case R.id.broadcast_test_btn:
                intent.setClass(this, TestBroadcastReceiverActivity.class);
                startActivity(intent);
                break;
            case R.id.aidl_test_btn:
                intent.setClass(this, BookManagerActivity.class);
                startActivity(intent);
                break;
            case R.id.draw_view_test_btn:
                if (CanShowFloat) {
                    intent.setClass(this, DrawViewActivity.class);
                    startActivity(intent);
                }else {
                    Toast.makeText(MainActivity.this, "未设置悬浮窗权限,请开启权限！", Toast.LENGTH_SHORT).show();
                    RequestOverlayPermission(this);
                }

                break;
            case R.id.local_thread_test_btn:
                intent.setClass(this, TestLocalThreadActivity.class);
                startActivity(intent);
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

    public void gotoDrawView(){
        Intent intent = new Intent();
        intent.setClass(this, DrawViewActivity.class);
        startActivity(intent);
    }

    /**
     * 动态请求悬浮窗权限
     */
    public void RequestOverlayPermission(Activity activity) {
        if (Build.VERSION.SDK_INT >= 23) {
            if (!Settings.canDrawOverlays(activity)) {
                String ACTION_MANAGE_OVERLAY_PERMISSION = "android.settings.action.MANAGE_OVERLAY_PERMISSION";
                String packageName = getPackageName();
                Log.e("ceshi","packageName   = " + packageName);
                Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION, Uri.parse("package:" + packageName));
                activity.startActivityForResult(intent, REQUEST_OVERLAY);
            } else {
                CanShowFloat = true;
                gotoDrawView();
            }
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_OVERLAY)        // 从应用权限设置界面返回
        {
            if (resultCode == Activity.RESULT_OK) {
                CanShowFloat = true;        // 设置标识为可显示悬浮窗
                gotoDrawView();
            } else {
                CanShowFloat = false;
                // 若当前未允许显示悬浮窗，则提示授权
                if (!Settings.canDrawOverlays(this)) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(this);
                    builder.setCancelable(false);
                    builder.setTitle("悬浮窗权限未授权");
                    builder.setMessage("应用需要悬浮窗权限，以展示浮标");
                    builder.setPositiveButton("去添加 权限", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                            RequestOverlayPermission(MainActivity.this);
                        }
                    });

                    builder.setNegativeButton("拒绝则 退出", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                             Toast.makeText(MainActivity.this, "未设置悬浮窗权限！", Toast.LENGTH_SHORT).show();
                        }
                    });
                    builder.show();
                }
            }
        }
    }

}