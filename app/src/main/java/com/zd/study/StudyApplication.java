package com.zd.study;

import android.app.Activity;
import android.app.Application;
import android.content.ComponentCallbacks2;
import android.os.Bundle;
import android.os.Process;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.facebook.drawee.backends.pipeline.Fresco;

public class StudyApplication extends Application {
    private int activityCount = 0;

    @Override
    public void onCreate() {
        super.onCreate();
        Log.e("zdTest", "=======StudyApplication======onCreate====");
        Log.i("zdTest", "=======StudyApplication======onCreate=====Process==id =" + Process.myPid());
        Fresco.initialize(this);

        registerActivityLifecycleCallbacks(new ActivityLifecycleCallbacks() {
            @Override
            public void onActivityCreated(@NonNull Activity activity, @Nullable Bundle savedInstanceState) {

            }

            @Override
            public void onActivityStarted(Activity activity) {
                if (activityCount == 0) {
                    // 应用进入前台:cite[3]:cite[6]
                    Log.d("AppState", "切换到前台");
                }
                activityCount++;
            }

            @Override
            public void onActivityResumed(@NonNull Activity activity) {

            }

            @Override
            public void onActivityPaused(@NonNull Activity activity) {

            }

            @Override
            public void onActivityStopped(Activity activity) {
                activityCount--;
                if (activityCount == 0) {
                    // 应用进入后台:cite[1]:cite[7]
                    Log.d("AppState", "切换到后台");
                }
            }

            @Override
            public void onActivitySaveInstanceState(@NonNull Activity activity, @NonNull Bundle outState) {

            }

            @Override
            public void onActivityDestroyed(@NonNull Activity activity) {

            }
        });
    }

    @Override
    public void onTrimMemory(int level) {
        super.onTrimMemory(level);
        // API 29及以上版本的处理逻辑
        if (level == ComponentCallbacks2.TRIM_MEMORY_COMPLETE) {
            // 处理完全清理内存的情况
            Log.e("zdTest", "=======StudyApplication======onTrimMemory====TRIM_MEMORY_COMPLETE");
        } else if (level == ComponentCallbacks2.TRIM_MEMORY_RUNNING_CRITICAL) {
            Log.e("zdTest", "=======StudyApplication======onTrimMemory====TRIM_MEMORY_RUNNING_CRITICAL");
        } else if(level == ComponentCallbacks2.TRIM_MEMORY_RUNNING_LOW){
            Log.e("zdTest", "=======StudyApplication======onTrimMemory====TRIM_MEMORY_RUNNING_LOW");

        }
    }


}
