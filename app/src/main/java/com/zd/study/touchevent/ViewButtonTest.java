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
        boolean disTag = super.dispatchTouchEvent(event);
//        boolean disTag = false;
        Log.d(TAG, " dispatchTouchEvent===== dis = " + disTag + "  action   " + event.getAction());
        return disTag;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        boolean touchTag = super.onTouchEvent(event);
        Log.d(TAG, " onTouchEvent===== touchTag = " + touchTag + "  action   " + event.getAction());
        return touchTag;
    }
}
