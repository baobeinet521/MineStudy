package com.zd.study.view;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

import androidx.annotation.NonNull;

import com.zd.study.R;

public class HalfScreenDialog extends Dialog {
    private View mContentView;
    private boolean mCanceledOnTouchOutside = true;

    public HalfScreenDialog(@NonNull Context context, int layoutId) {
        super(context, R.style.BottomDialog);
        init(layoutId);
    }

    public HalfScreenDialog(@NonNull Context context, int layoutId, boolean canceledOnTouchOutside) {
        super(context, R.style.BottomDialog);
        mCanceledOnTouchOutside = canceledOnTouchOutside;
        init(layoutId);
    }

    public View getContentView() {
        return mContentView;
    }

    private void init(int layoutId) {
        mContentView = LayoutInflater.from(getContext()).inflate(layoutId, null);
//        int statusBarHeight = DisplayUtil.getStatusBarHeight(getContext());
//        ViewGroup.MarginLayoutParams params = new ViewGroup.MarginLayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
//        params.topMargin = statusBarHeight;
//        mContentView.setLayoutParams(params);
        setContentView(mContentView);
        ViewGroup.LayoutParams layoutParams = mContentView.getLayoutParams();
        layoutParams.width = getWindow().getWindowManager().getDefaultDisplay().getWidth();
        layoutParams.height = WindowManager.LayoutParams.MATCH_PARENT;
        mContentView.setLayoutParams(layoutParams);
        getWindow().setGravity(Gravity.BOTTOM);
        if(!mCanceledOnTouchOutside){
            setCanceledOnTouchOutside(false);//背景不可点击
        }else {
            setCanceledOnTouchOutside(true);//背景可点击
        }
        getWindow().setWindowAnimations(R.style.BottomDialog_Animation);
        getWindow().setBackgroundDrawableResource(android.R.color.transparent);
//        UIUtils.convertToWhiteAndBlackMode(mContentView);
    }

//    public void setMarginTop(int dp) {
//        int marginTop = UIUtils.autoSWPx(getContext(), dp);
//        ViewGroup.MarginLayoutParams params = (ViewGroup.MarginLayoutParams) mContentView.getLayoutParams();
//        params.topMargin = params.topMargin + marginTop;
//        mContentView.setLayoutParams(params);
//    }
//
//    public void setMarginBottom(int dp) {
//        int marginBottom = UIUtils.autoSWPx(getContext(), dp);
//        ViewGroup.MarginLayoutParams params = (ViewGroup.MarginLayoutParams) mContentView.getLayoutParams();
//        params.bottomMargin = params.bottomMargin + marginBottom;
//        mContentView.setLayoutParams(params);
//    }
}
