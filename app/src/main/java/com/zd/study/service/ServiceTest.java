package com.zd.study.service;


import android.app.Service;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;


public class ServiceTest extends Service {
    public static String TAG ="ZdServiceTest";

    private Binder mBinder = new ServiceTestBinder();

    class ServiceTestBinder extends Binder{

        public void start(){
            Log.d(TAG, "ServiceBinder   start ===== ");

        }
    }
    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG,"onCreate ======");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(TAG,"onStartCommand ======");
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG,"onDestroy ======");
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.d(TAG,"onBind ======");
        return mBinder;
    }

    @Override
    public void onRebind(Intent intent) {
        super.onRebind(intent);
        Log.d(TAG,"onRebind ======");
    }

    @Override
    public void unbindService(ServiceConnection conn) {
        super.unbindService(conn);
        Log.d(TAG,"unbindService ======");
    }

    /**
     *
     * @param intent
     * @return true 表示服务如果没有被销毁再次调用时会调用onReBind(),返回false 表示服务 没有被销毁再次调用时不会调用onReBind()
     */
    @Override
    public boolean onUnbind(Intent intent) {
        Log.d(TAG,"onUnbind ======");
        return true;
    }
}
