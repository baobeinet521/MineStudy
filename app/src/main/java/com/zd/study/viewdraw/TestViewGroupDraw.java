package com.zd.study.viewdraw;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.ViewGroup;

public class TestViewGroupDraw extends ViewGroup {

    public TestViewGroupDraw(Context context) {
        super(context);
    }

    public TestViewGroupDraw(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public TestViewGroupDraw(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public TestViewGroupDraw(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }
}
