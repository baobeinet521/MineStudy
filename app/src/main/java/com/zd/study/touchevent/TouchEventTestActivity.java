package com.zd.study.touchevent;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;

import com.zd.study.R;

public class TouchEventTestActivity extends AppCompatActivity {

    public static String TAG = "TouchEventTestTouchEventActivity";
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_touch_event_layout);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        boolean disTag = super.dispatchTouchEvent(ev);
//        boolean disTag = false;
        Log.d(TAG, " dispatchTouchEvent===== disTag = " + disTag + "  action   " + ev.getAction());
        return disTag;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        boolean onTouchTag = super.onTouchEvent(event);
        Log.d(TAG, " onTouchEvent===== onTouchTag = " + onTouchTag + "  action   " + event.getAction());
        return onTouchTag;
    }
}
