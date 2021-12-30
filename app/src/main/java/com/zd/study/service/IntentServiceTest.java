package com.zd.study.service;

import android.app.IntentService;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.util.Log;

public class IntentServiceTest extends IntentService {
    public static String TAG ="IntentZdServiceTest";
    public IntentServiceTest() {
        super("");
        Log.d(TAG,"IntentServiceTest 没有参数的构造方法=====");
    }
    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     *
     * @param name Used to name the worker thread, important only for debugging.
     */
    public IntentServiceTest(String name) {
        super(name);
        Log.d(TAG,"IntentServiceTest ======");
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        Log.d(TAG,"onHandleIntent ======");
    }

}
