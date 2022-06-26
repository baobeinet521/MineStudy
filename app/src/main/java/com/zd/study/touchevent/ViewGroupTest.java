package com.zd.study.touchevent;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;

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
        Log.d(TAG,"  dispatchTouchEvent ====   action   " + ev.getAction());
        return super.dispatchTouchEvent(ev);
//        return false;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        Log.d(TAG, "  onInterceptTouchEvent    action   " + ev.getAction());
//        if(ev.getAction() != 0){
//            return true;
//
//        }
        return super.onInterceptTouchEvent(ev);
//        return true;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.d(TAG,"    onTouchEvent======   action   " + event.getAction());
        return super.onTouchEvent(event);
//        return true;
    }
}
