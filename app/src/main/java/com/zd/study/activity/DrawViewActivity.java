package com.zd.study.activity;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.zd.study.R;
import com.zd.study.viewdraw.MyButton;

public class DrawViewActivity extends Activity {
    private View mMyButton;
    private Context context;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.draw_circle_layout);
        mMyButton = findViewById(R.id.my_button);
        Toast.makeText(this, "", Toast.LENGTH_SHORT).show();
    }
}
