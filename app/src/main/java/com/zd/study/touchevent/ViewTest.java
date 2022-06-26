package com.zd.study.touchevent;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;


public class ViewTest extends View {
    public static String TAG = "TouchEventTestView";

    public ViewTest(Context context) {
        super(context);
    }

    public ViewTest(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ViewTest(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        Log.d(TAG, " dispatchTouchEvent=====   action   " + event.getAction());
        return super.dispatchTouchEvent(event);
//        return false;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.d(TAG, " onTouchEvent=====   action   " + event.getAction());
//        getParent().requestDisallowInterceptTouchEvent(true);
        return super.onTouchEvent(event);
//        return true;
    }
}
