package com.zd.study.view;

import android.app.Activity;
import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;

import com.zd.study.R;

import java.util.List;

public class GuidePopupDialog extends Dialog implements View.OnClickListener {
    public static String TAG = "GuidePopupDialog";
    private List<String> mPageCodeList;

    public GuidePopupDialog(@NonNull Activity mContext) {
        super(mContext);
    }

    public void setPageCodeList(List<String> mPageCodeList) {
        this.mPageCodeList = mPageCodeList;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_popup_guide_layout);
        getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
//        getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        getWindow().setGravity(Gravity.BOTTOM);
        ImageView imageViewGuide1 = findViewById(R.id.framework_guide1_finance_image);
        ImageView imageViewGuide2 = findViewById(R.id.framework_guide2_finance_image);
        imageViewGuide1.setOnClickListener(this);
        imageViewGuide2.setOnClickListener(this);
        setCanceledOnTouchOutside(true);
    }

    @Override
    public boolean dispatchTouchEvent(@NonNull MotionEvent ev) {
        return super.dispatchTouchEvent(ev);

    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.framework_guide1_finance_image || id == R.id.framework_guide2_finance_image) {
            dismiss();
        }
    }
}
