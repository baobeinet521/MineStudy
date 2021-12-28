package com.zd.study.touchevent;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import com.zd.study.R;

public class TouchEventTestActivity extends AppCompatActivity {

    public static String TAG = "TouchEventTestTouchEventActivity";
    private ViewGroupTest mViewGroup;
    private ViewButtonTest mViewButton;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_touch_event_layout);
        mViewGroup = findViewById(R.id.view_group_test);
        mViewButton = findViewById(R.id.view_test);
        mViewButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG,"老娘要起飞-----");
            }
        });
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
//        boolean disTag = super.dispatchTouchEvent(ev);
//        boolean disTag = false;
//        Log.d(TAG, " dispatchTouchEvent===== disTag = " + disTag + "  action   " + ev.getAction());
        Log.d(TAG, " dispatchTouchEvent=====   action   " + ev.getAction());
        return super.dispatchTouchEvent(ev);
//        return false;

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
//        boolean onTouchTag = super.onTouchEvent(event);
//        Log.d(TAG, " onTouchEvent===== onTouchTag = " + onTouchTag + "  action   " + event.getAction());
        Log.d(TAG, " onTouchEvent=====   action   " + event.getAction());
        return super.onTouchEvent(event);
    }
}
