package com.zd.study.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

/**
 * LocalBroadcast有如下缺点：
 * 1.不能跨进程。即使本应用里的两个进程之间也无法通信
 */
public class LocalBroadCastReceiverTest extends BroadcastReceiver {
    public static String TAG = "testBroadcast";
    @Override
    public void onReceive(Context context, Intent intent) {
        String test = intent.getStringExtra("test_local");
//        Bundle mBundle = getResultExtras(true);
//        String addTest = "";
//        if(mBundle != null){
//            addTest = mBundle.getString("addTest");
//        }
//        String resultData = getResultData();
//        Log.d(TAG," LocalBroadCastReceiverTest 收到到的数据 === " + test + " addTest =  " + addTest + "   resultData " + resultData);
        Log.d(TAG," LocalBroadCastReceiverTest 收到到的数据 === " + test);

    }
}
