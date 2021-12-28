package com.zd.study.touchevent;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.LinearLayout;

public class ViewGroupTest extends LinearLayout {
    public static String TAG = "TouchEventTestViewGroup";
    public ViewGroupTest(Context context) {
        super(context);
    }

    public ViewGroupTest(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public ViewGroupTest(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public ViewGroupTest(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        boolean disTag = super.dispatchTouchEvent(ev);
//        boolean disTag = false;
        Log.d(TAG,"  dispatchTouchEvent ==== disTag =  " + disTag + "  action   " + ev.getAction());
        return disTag;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        boolean onInterceptTag = super.onInterceptTouchEvent(ev);
        Log.d(TAG, "  onInterceptTouchEvent  onInterceptTag =" +onInterceptTag + "  action   " + ev.getAction());
        return onInterceptTag;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        boolean onTouchTag = super.onTouchEvent(event);
        Log.d(TAG,"    onTouchEvent====== onTouchTag = " + onTouchTag + "  action   " + event.getAction());
        return onTouchTag;
    }
}
