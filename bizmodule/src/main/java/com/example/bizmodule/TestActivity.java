package com.example.bizmodule;

import android.app.Activity;
import android.os.Bundle;

import androidx.annotation.Nullable;

import com.zd.frameworkmodule.widget.AnimationImageView;


public class TestActivity extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.biz_test_layout);

        BizUrlManager bizUrlManager = new BizUrlManager();
        AnimationImageView imageView = new AnimationImageView(bizUrlManager);
        imageView.deal();
    }
}
