package com.zd.study;

import android.app.Application;
import android.os.Process;
import android.util.Log;

public class StudyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Log.e("zdTest","=======StudyApplication======onCreate====");
        Log.i("zdTest", "=======StudyApplication======onCreate=====Process==id =" + Process.myPid());
    }
}
