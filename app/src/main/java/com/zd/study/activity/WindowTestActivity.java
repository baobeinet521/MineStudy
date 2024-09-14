package com.zd.study.activity;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.res.Configuration;
import android.os.Build;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.zd.study.R;

public class WindowTestActivity extends AppCompatActivity {
    private LinearLayout layout;
    private TextView viewText;
    private ValueAnimator valueAnimator;
    private int newHeight;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setTheme();
        setContentView(R.layout.activity_window_test);
        layout = findViewById(R.id.layout_view1);
        viewText = findViewById(R.id.view_change);
        viewText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                newHeight = dip2px(470);
//                viewChange();
                int targetHeight = dip2px(470);
                int height = dip2px(370);
                // 开始动画
                animateHeight(layout, height, targetHeight);
            }
        });
    }
    private void animateHeight(final View view, int startHeight, int endHeight) {
        valueAnimator = ValueAnimator.ofInt(startHeight, endHeight);
        valueAnimator.setDuration(500);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int val = (Integer) animation.getAnimatedValue();
                ConstraintLayout.LayoutParams layoutParams = (ConstraintLayout.LayoutParams) view.getLayoutParams();
                layoutParams.height = val;
                view.setLayoutParams(layoutParams);
            }
        });
        valueAnimator.start();
    }
    @Override
    protected void onPause() {
        super.onPause();
        if(valueAnimator != null){
            valueAnimator.cancel();
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(valueAnimator != null){
            valueAnimator.cancel();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        // 当小窗口模式下的高度发生变化时，你可以在这里处理你的逻辑
        if (newConfig.smallestScreenWidthDp < 600) {
            // 小窗口模式下的处理逻辑
            int newHeight = getWindow().getDecorView().getHeight();
            // 使用新的高度值进行你需要的操作
        }
    }


    public int dip2px(float dipValue){
        float scale = this.getResources().getDisplayMetrics().density;
        return (int) (dipValue * scale +0.5f);
    }
}
