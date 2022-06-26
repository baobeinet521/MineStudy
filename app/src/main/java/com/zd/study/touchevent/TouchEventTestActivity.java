package com.zd.study.touchevent;

import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.zd.study.R;

public class TouchEventTestActivity extends AppCompatActivity {

    public static String TAG = "TouchEventTestTouchEventActivity";
    private ViewGroupTest mViewGroup;
    private ViewTest mViewTest;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_touch_event_layout);
        mViewGroup = findViewById(R.id.view_group_test);
        mViewTest = findViewById(R.id.view_test);
        mViewTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG,"老娘要起飞-----");
            }
        });
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        Log.d(TAG, " dispatchTouchEvent=====   action   " + ev.getAction());
        return super.dispatchTouchEvent(ev);
//        return false;

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.d(TAG, " onTouchEvent=====   action   " + event.getAction());
        return super.onTouchEvent(event);
    }
}
