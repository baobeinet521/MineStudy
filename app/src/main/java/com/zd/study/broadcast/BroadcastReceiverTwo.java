package com.zd.study.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

public class BroadcastReceiverTwo extends BroadcastReceiver {

    public static String TAG = "testBroadcast";
    @Override
    public void onReceive(Context context, Intent intent) {
        String test = intent.getStringExtra("test1");
        Bundle mBundle = getResultExtras(true);
        String addTest = "";
        if(mBundle != null){
            addTest = mBundle.getString("addTest");
        }
        String resultData = getResultData();
        Log.d(TAG," mBroadcastReceiverTwo 收到到的数据 === " + test + " addTest =  " + addTest + "   resultData " + resultData);

    }
}
