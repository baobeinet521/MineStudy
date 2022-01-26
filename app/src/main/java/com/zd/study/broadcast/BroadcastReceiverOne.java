package com.zd.study.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

public class BroadcastReceiverOne extends BroadcastReceiver {
    public static String TAG = "testBroadcast";
    @Override
    public void onReceive(Context context, Intent intent) {
        String test = intent.getStringExtra("test1");
        Log.d(TAG," mBroadcastReceiverOne 收到到的数据 === " + test);
        setResultData("setResultData添加一个数据");

        Bundle mBundle = new Bundle();
        mBundle.putString("addTest","广播one增加一条数据");
        setResultExtras(mBundle);

        //拦截广播
        abortBroadcast();
    }
}
