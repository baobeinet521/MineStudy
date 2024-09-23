package com.zd.study.autoscrollview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import androidx.viewpager.widget.ViewPager;

public class BannerViewPager extends ViewPager {

    private boolean isScroll = true;//true支持手势滑动 false不支持手势滑动

    public BannerViewPager(Context context, AttributeSet attrs){
        super(context,attrs);
    }

    public BannerViewPager(Context context){
        super(context);
    }

    /**
     * 是否支持手势滑动 true支持 false不支持
     * @param scroll
     */
    public void setScroll(boolean scroll) {
        isScroll = scroll;
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        try {
            if (isScroll) {
                return super.onInterceptTouchEvent(ev);
            } else {
                return false;
            }
        } catch (Exception e) {
//            BCMLogger.printException(e);
        }
        return false;
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        try {
            if (isScroll) {
                return super.onTouchEvent(ev);
            } else {
                return true;
            }
        } catch (Exception e) {
//            BCMLogger.printException(e);

        }
        return false;
    }

}
