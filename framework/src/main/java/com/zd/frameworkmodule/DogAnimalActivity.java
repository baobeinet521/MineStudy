package com.zd.frameworkmodule;

import android.app.Activity;
import android.os.Bundle;

import androidx.annotation.Nullable;

import com.zd.framework.R;

public class DogAnimalActivity extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.framework_test_layout);
    }
}
