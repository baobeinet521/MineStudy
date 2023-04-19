package com.zd.study.activity;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.os.PersistableBundle;
import android.provider.MediaStore;
import android.provider.Settings;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.zd.study.R;
import com.ruubypay.demo2.RidingCodeActivity;
import com.zd.study.broadcast.TestBroadcastReceiverActivity;
import com.zd.study.guide.DataTest;
import com.zd.study.guide.GuidePopUtils;
import com.zd.study.guide.data.GuideConfigData;
import com.zd.study.guide.data.GuideConfigList;
import com.zd.study.guide.inter.GuidePopLister;
import com.zd.study.service.ServiceTestActivity;
import com.zd.study.touchevent.TouchEventTestActivity;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    public static String TAG = "TestActivity";
    private Button mTestBtn;
    private Button mTouchEventTestBtn;
    private Button mServiceTestBtn;
    private Button mBroadcastReceiverBtn;
    private Button mAIDLBtn;
    private Button mDrawBtn;
    private Button mTestLocalThreadBtn;
    private Button mVideoTestBtn;
    private Button mFrescoTestBtn;

    public boolean CanShowFloat = false;

    private final int REQUEST_OVERLAY = 5004;

    private final int PICK_IMAGE_VIDEO = 5005;

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
        mVideoTestBtn = findViewById(R.id.vedio_test_btn);
        mVideoTestBtn.setOnClickListener(this);
        mFrescoTestBtn = findViewById(R.id.fresco_test_btn);
        mFrescoTestBtn.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        int id = v.getId();
        Intent intent = new Intent();
        switch (id) {
            case R.id.test_activity_btn:
//                Intent intent = new Intent(this, HandlerActivity.class);
//                 intent = new Intent(this, LifeCycleActivity.class);
//                Intent intentTest = new Intent();
//                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                intent.setClass(this, TestFlagActivity.class);
//                startActivity(intentTest);
//                SingleInstanceD mSingleInstanceD = SingleInstanceD.getInstance();
//                System.out.println("_____分割线_______");
//                mSingleInstanceD.testPrint();

//                startActivity(intent);

//                Intent i = new Intent(Intent.ACTION_PICK);
////                i.addCategory(Intent.CATEGORY_OPENABLE);
//                i.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
//                i.putExtra(Intent.EXTRA_INTENT, i);
////                i.setType("image/*|audio/*");
//                startActivityForResult(i, 0);

                //打开的是文件夹
//                Intent galleryIntent=new Intent(Intent.ACTION_GET_CONTENT);
//                galleryIntent.addCategory(Intent.CATEGORY_OPENABLE);
//                galleryIntent.setType("image/*");//图片
//                startActivityForResult(galleryIntent,1);

//                Intent intentToPickPic = new Intent(Intent.ACTION_PICK, null);
//                // 如果限制上传到服务器的图片类型时可以直接写如："image/jpeg 、 image/png等的类型" 所有类型则写 "image/*"
//                intentToPickPic.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/jpeg");
//                startActivityForResult(intentToPickPic, PICK_IMAGE_VIDEO);

//                GuidePopupDialog dialog = new GuidePopupDialog(this);
//                dialog.show();


//                intent.setAction("android.intent.action.VIEW");
//                intent.setData(Uri.parse("cn.12306.comm://url"));
//                intent.addCategory("android.intent.category.DEFAULT");
//                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//                startActivity(intent);


//                String str = "cn.12306.comm://url";
//                String str1 = Uri.encode(str);
//                Log.d(TAG, "------------str1    " + str1);
//
//                String str2 = Uri.encode(str, ":/");
//                Log.d(TAG, "------------str2    " + str2);
//
//                String str3 = Uri.encode(str, "://");
//                Log.d(TAG, "------------str3    " + str3);

//                intent.setClass(this, RidingCodeActivity.class);
//                startActivity(intent);

//                String encode = Uri.encode("https://www.baidu.com");
//                Log.e("ceshi", encode);


//                test(testStr, 4000);

//                getReadPermissions();

                getReadAndWritePermissions();
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
                } else {
                    Toast.makeText(MainActivity.this, "未设置悬浮窗权限,请开启权限！", Toast.LENGTH_SHORT).show();
                    RequestOverlayPermission(this);
                }

                break;
            case R.id.local_thread_test_btn:
                intent.setClass(this, TestLocalThreadActivity.class);
                startActivity(intent);
                break;
            case R.id.vedio_test_btn:
                intent.setClass(this, VedioPlayerTestActivity.class);
                startActivity(intent);
                break;
            case R.id.fresco_test_btn:
                intent.setClass(this, FrescoTestActivity.class);
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

    public void gotoDrawView() {
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
                Log.e("ceshi", "packageName   = " + packageName);
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
        if (requestCode == PICK_IMAGE_VIDEO) {
            Uri uri = data.getData();
            //通过uri的方式返回，部分手机uri可能为空
            if (uri != null) {
                String path = uri.getPath();
                Log.e(TAG, "path  " + path);
                try {
                    //通过uri获取到bitmap对象
                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
                } catch (IOException e) {
                    e.printStackTrace();
                }

            } else {
                //部分手机可能直接存放在bundle中
                Bundle bundleExtras = data.getExtras();
                if (bundleExtras != null) {
                    Bitmap bitmaps = bundleExtras.getParcelable("data");

                }
            }

        }
    }

    String testStr = "说话 你好   这样的  ，，你有一份年度账单待查收你有一份年度账单待查收你有一份年度账单待查收你有一份年度账单待查收你有一份年度账单待查收你有一份年度账单待查收你有一份年度账单待查收你有一份年度账单待查收你有一份年度账单待查收你有一份年度账单待查收你有一份年度账单待查收你有一份年度账单待查收你有一份年度账单待查收你有一份年度账单待查收你有一份年度账单待查收你有一份年度账单待查收你有一份年度账单待查收你有一份年度账单待查收你有一份年度账单待查收你有一份年度账单待查收待查收你有一份年度账单待查收你有一份年度账单待查收你有一份年度账单待查收你有一份年度账单待查收你有一份年度账单待查收你有一份年度账单待查收你有一份年度账单待查收度账单待查收你有一份年度账1234567890123单待查收你有一份年度账单待查收你有一份年度账单待查收你有一份年度账单待查收待查收你有一份年度账单待查收你有一份年度账单待查收你有一份年度账单待查收你有一份年度账单待查收你有一份年度账单待查收你有一份年度账单待查收你有一份年度账单待查收度账单待查收987654你有一份年度账单待查收你有一份年度账单待查收你有一份年度账单待查收你有一份年度账单待查收待查收你有一份年度账单待查收你有一份年度账单待查收你有一份年度账单待查收你有一份年度账单待查收你有一份年度账单待查收你有一份年度账单待查收你有一份年度账单待查收度账单待查收你有一份年度账单待查收你有一份456年度账单待查收你有一份年度账单待查收你有一份年度账单待查收待查收你有一份年度账单待查收你有一份年度账单待查收你有一份年度账单待查收你有一份年yy度账单待查收你说话 你好   这样的  ，，你有一份年度账单待查收你有一份年度账单待查收你有待查收你有一份年度账单待查收你有一份年度账单待查收你有一份年度账单待查收你有一份年度账单待查收你有一份年度账单待查收你有一份年度账单待查收你有一份年度账单待查收你有一份年7899ut8度账单待查收你有一份年度账单待查收你有一份年度账单待查收你有一份年度账单待查收你有一份年度账单待查收你有一份年度账单待查收你有一份年度账单待查收你有一份年度账单待查收你有一份年度账单待查收你有一份年度账单待查收待查收你有一份年度账单待查收你有一份年度账单待查收你有一份年度账单待查收你有一份年度账单待查收你有一份年度账单待查收你有一份年度账单待查收你有一份年度账单待查收\n" +
            "6230580000261339829有一份年度账单待查收你有一份年度账单待查收你有一份年度账单待查收说话 你好   这样的  ，，你有一份年度账单待查收你有一份年度账单待查收你有一份年度账单待查收你有一份年度账单待查收你有一份年度账单待查收你有一份年度账单待查收你有一份年度账单待查收你有一份年度账单待查收你有一份年度账单待查收你有一份年度账单待查收你有一份年度账单待查收你有一份年度账单待查收你有一份年度账单待查收你有一份年度账单待查收你有一份年度账单待查收你有一份年度账单待查收你有一份年度账单待查收你有一份年度账单待查收你有一份年度账单待查收你有一份年度账单待查收待查收你有一份年度账单待查收你有一份年度账单待查收你有一份年度账单待查收你有一份年度账单待查收你有一份年度账单待查收你有一份年度账单待查收你有一份年度账单待查收度账单待查收你有一份年度账单待查收你有一份年度账单待查收你有一份年度账单待查收你有一份年度账单待查收待查收你有一份年度账单待查收你有一份年度账单待查收你有一份年度账单待查收你有一份年度账单待查收你有一份年度账单待查收你有一份年度账单待查收你有一份年度账单待查收度账单待查收你有一份年度账单待查收你有一份年度账单待查收你有一份年度账单待查收你有一份年度账单待查收待查收你有一份年度账单待查收你有一份年度账单待查收你有一份年度账单待查收你有一份年度账单待查收你有一份年度账单待查收你有一份年度账单待查收你有一份年度账单待查收度账单待查收你有一份年度账单待查收你有一份年度账单待查收你有一份年度账单待查收你有一份年度账单待查收待查收你有一份年度账单待查收你有一份年度账单待查收你有一份年度账单待查收你有一份年度账单待查收你说话 你好   这样的  ，，你有一份年度账单待查收你有一份年度账单待查收你有一份年度账单待查收你有一份年度账单待查收你有一份年度账单待查收你有一份年度账单待查收你有一份年度账单待查收你有一份年度账单待查收你有一份年度账单待查收你有一份年度账单待查收你有一份年度账单待查收你有一份年度账单待查收你有一份年度账单待查收你有一份年度账单待查收你有一份年度账单待查收你有一份年度账单待查收你有一份年度账单待查收你有一份年度账单待查收你有一份年度账单待查收你有一份年度账单待查收待查收你有123一份年度账单待查收你有一份年度账单待查收你有一份年度账单待查收你有一份年度账单待查收你有一份年度账单待查收你有一份年度账单待查收你有一份年度账单待查收\n" +
            "6230580000261339829有一份年度账单待查收你有一份年度账单待查收你有一份年度账单待查收说话 你好   这样的  ，，你有123456789012345678901234567890123待查收你有一份年度账单待查收你有一份年度账单待查收你有一份年度账单待查收你有一份年度账单待查收你有一份年度账单待查收你有一份年度账单待查收你有一份年度账单待查收你有一份年度账单待查收你有一份年度账单待查收你有一份年度账单待查收你有一份年度账单待查收你有一份年度账单待查收你有一份年度账单待查收你有一份年度账单待查收你有一份年度账单待查收你有一份年度账单待查收你有一份年度账单待查收待查收你有一份年度账单待查收你有一份年度账单待查收你有一份年度账单待查收你有一份年度账单待查收你有一份年度账单待查收你有一份年度账单待查收你有一份年度账单待查收度账单待查收你有一份年度账单待查收你有一份年度账单待查收你有一份年度账单待查收你有一份年度账单待查收待查收你有一份年度账单待查收你有一份年度账单待查收你有一份年度账单待查收你有一份年度账单待查收你有一份年度账单待查收你有一份年度账单待查收你有一份年度账单待查收度账单待查收你有一份年度账单待查收你有一份年度账单待查收你有一份年度账单待查收你有一份年度账单待查收待查收你有一份年度账单待查收你有一份年度账单待查收你有一份年度账单待查收你有一份年度账单待查收你有一份年度账单待查收你有一份年度账单待查收你有一份年度账单待查收度账单待查收你有一份年度账单待查收你有一份年度账单待查收你有一份年度账单待查收你有一份年度账单待查收待查收你有一份年度账单待查收你有一份年度账单待查收你有一份年度账单待查收你有一份年度账单待查收你说话 你好   这样的  ，，你有一份年度账单待查收你有一份年度账单待查收你有一份年度账单待查收你有一份年度账单待查收你有一份年度账单待查收你有一份年度账单待查收你有一份年年度账单待查收你有一份年度账单待查收你有一份年度账单待查收你有一份年度账单待查收你有一份年度账单待查收你有一份年度账单待查收待查收你有一份年度账单待查收你有一份年度账单待查收你有一份年度账单待查收你有一份年度账单待查收你有一份年度账单待查收你有一份年度账单待查收你有一份年度账单待查收\n" +
            "6230580000261339828账单待查收你有一份年度账单待查有一份年度账单待查收你有一份年度账单待查收你有一份年度账单待查收说话 你好   这样的  ，，你有一份年度账单待查收你有一份年度账单待查收你有一份年度账单待查收你有一份年度账单待查收你有一份年度账单待查收你有一份年度账单待查收你有一份年度账单待查收你有一份年度账单待查收你有一份年度账单待查收你有一份年度账单待查收你有一份年度账单待查收你有一份年度账单待查收你有一份年度账单待查收你有一份年度账单待查收你有一份年度账单待查收你有一份年度账单待查收你有一份年度账单待查收你有一份年度账单待查收你有一份年度账单待查收你有一份年度账单待查收待查收你有一份年度账单待查收你有一份年度账单待查收你有一份年度账单待查收你有一份年度账单待查收你有一份年度账单待查收你有一份年度账单待查收你有一份年度账单待查收度账单待查收你有一份年度账单待查收你有一份年度账单待查收你有一份年度账单待查收你有一份年度账单待查收待查收你有一份年度账单待查收你有一份年度账单待查收你有一份年度账单待查收你有一份年度账单待查收你有一份年度账单待查收你有一份年度账单待查收你有一份年度账单待查收度账单待查收你有一份年度账单待查收你有一份年度账单待查收你有一份年度账单待查收你有一份年度账单待查收待查收你有一份年度账单待查收你有一份年度账单待查收你有一份年度账单待查收你有一份年度账单待查收你有一份年度账单待查收你有一份年度账单待查收你有一份年度账单待查收度账单待查收你有一份年度账单待查收你有一份年度账单待查收你有一份年度账单待查收你有一份年度账单待查收待查收你有一份年度账单待查收你有一份年度账单待查收你有一份年度账单待查收你有一份年度账单待查收你说话 你好待查收你有一份年度账单待查收你有一份年度账单待查收你有一份年度账单待查收你有一份年度账单待查收你有一份年度账单待查收你有一份年度账单待查收你有一份年度账单待查收你有一份年度账单待查收你有一份年度账单待查收你有一份年度账单待查收你有一份年度账单待查收你有一份年度账单待查收你有一份年度账单待查收你有一份年度账单待查收你有一份年度账单待查收你有一份年度账单待查收待查收你有一份年度账单待查收你有一份年度账单待查收你有一份年度账单待查收你有一份年度账单待查收你6230580000261339830";


    public void test(String testStr, int length) {
        List<String> result = new ArrayList<>();
        Log.d("zdTest", "初始字符串长度是   " + testStr.length());
        int forLen = Math.min(testStr.length(), length);
        String temp = "";
        long startTime = System.currentTimeMillis();
        Log.d("zdTest", "开始时间   " + startTime);
        for (int i = 0; i < forLen; i++) {
            char subStr = testStr.charAt(i);
            if (subStr >= '0' && subStr <= '9') {
                temp += subStr;
            } else {
                if (temp.length() >= 13 && temp.length() <= 30) {
                    result.add(temp);
                }
                temp = "";
            }
        }
        //最后一位如果是数字的话 需要在判断一下
        if (temp.length() >= 13 && temp.length() <= 30) {
            result.add(temp);
        }

        long stopTime = System.currentTimeMillis();
        Log.d("zdTest", "开始时间   " + stopTime);

        if (result != null) {
            for (int i = 0; i < result.size(); i++) {
                Log.d("zdTest", "i =  " + i + "  最终的卡号有   " + result.get(i));
            }
        }


    }

    public void charTest() {
        String test = "0123456789";
        for (int i = 0; i < test.length(); i++) {
            char subStr = test.charAt(i);
            boolean is = subStr >= '0' && subStr <= '9';
            Log.d("zdTest", "is   " + is + "  i = " + i);
        }
    }


    /**
     * 权限读取权限
     */
    private void getReadAndWritePermissions() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED
                    | ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                //没有权限
                ActivityCompat.requestPermissions(this,
                        new String[]{
                                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                                Manifest.permission.READ_EXTERNAL_STORAGE}, 10001);
            } else {//如果已经获取到了权限则直接进行下一步操作
                Log.e(TAG, "如果已经获取到了权限则直接进行下一步操作");
                testCreateFile();
            }
        }

    }
    public void testCreateFile(){
//        String BASE_PATH = Environment.getExternalStorageDirectory().getAbsolutePath() +"/Zdtest1111/";

        String BASE_PATH = Environment.getExternalStorageDirectory() + File.separator +"Zdtest1111" + File.separator ;
        Log.e(TAG, "BASE_PATH  = " + BASE_PATH);
        File file = new File(BASE_PATH);
        // video文件夹不存在
        if (!file.exists()) {
            // 创建文件夹
            file.mkdir();
            Log.e(TAG, "BASE_PATH  文件不存在 ");
        }else {
            Log.e(TAG, "BASE_PATH  = 文件已经存在 ");
        }

    }


    public void test2() {
        GuideConfigList guideConfigList = DataTest.makeData();
        GuidePopUtils.getInstance().setGuideConfigList(guideConfigList);
        GuidePopUtils.getInstance().showGuidePopByGuideId(this, "MGC2023021614110007", new GuidePopLister() {
            @Override
            public void onError(String errorCode, String errorMsg) {
                Log.d("zdTest", "onError  errorCode   " + errorCode + "  errorMsg  = " + errorMsg);
            }

            @Override
            public void showGuide(String guideId, int step) {
                Log.d("zdTest", "showGuide  guideId   " + guideId + "  step  = " + step);
            }

            @Override
            public void dismissGuide(int step) {
                Log.d("zdTest", "step    " + step);
            }
        });

    }

    /**
     * 权限的验证及处理，相关方法
     */
    private void getReadPermissions() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED
                    | ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.RECEIVE_SMS) | ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)) {//是否请求过该权限
                    ActivityCompat.requestPermissions(this,
                            new String[]{
                                    Manifest.permission.WRITE_EXTERNAL_STORAGE,
                                    Manifest.permission.READ_EXTERNAL_STORAGE}, 10001);
                } else {//没有则请求获取权限，示例权限是：存储权限和短信权限，需要其他权限请更改或者替换
                    ActivityCompat.requestPermissions(this,
                            new String[]{
                                    Manifest.permission.READ_EXTERNAL_STORAGE,
                                    Manifest.permission.WRITE_EXTERNAL_STORAGE}, 10001);
                }
            } else {//如果已经获取到了权限则直接进行下一步操作
                Log.e(TAG, "onRequestPermissionsResult");
                test2();
            }
        }

    }

    /**
     * 一个或多个权限请求结果回调
     * 当点击了不在询问，但是想要实现某个功能，必须要用到权限，可以提示用户，引导用户去设置
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case 10001:
                for (int i = 0; i < grantResults.length; i++) {
//                   如果拒绝获取权限
                    if (grantResults[i] != PackageManager.PERMISSION_GRANTED) {
                        //判断是否勾选禁止后不再询问
                        boolean flag = ActivityCompat.shouldShowRequestPermissionRationale(this, permissions[i]);
                        if (flag) {
                            getReadPermissions();
                            return;//用户权限是一个一个的请求的，只要有拒绝，剩下的请求就可以停止，再次请求打开权限了
                        } else { // 勾选不再询问，并拒绝
                            Toast.makeText(this, "请到设置中打开权限", Toast.LENGTH_LONG).show();
                            return;
                        }
                    }
                }
                break;
            default:
                break;
        }

    }
}