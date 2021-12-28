package com.zd.study.touchevent;

import android.content.Context;
import android.support.v7.widget.AppCompatButton;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;


public class ViewButtonTest extends AppCompatButton {
    public static String TAG = "TouchEventTestViewButton";

    public ViewButtonTest(Context context) {
        super(context);
    }

    public ViewButtonTest(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ViewButtonTest(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
//        boolean disTag = false;
        Log.d(TAG, " dispatchTouchEvent=====   action   " + event.getAction());
        return super.dispatchTouchEvent(event);
//        return true;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.d(TAG, " onTouchEvent=====   action   " + event.getAction());
        getParent().requestDisallowInterceptTouchEvent(true);
        return super.onTouchEvent(event);
//        return true;
    }
}
