package com.zd.study.activity;

import android.animation.Animator;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.airbnb.lottie.LottieAnimationView;
import com.airbnb.lottie.LottieComposition;
import com.zd.study.R;
import com.zd.study.utils.DisplayUtil;

/**
 * @author zd
 */
public class LottieTestActivity extends AppCompatActivity {
    private String TAG = "LottieTestActivity";
    private String lottie1 = "https://mbanktest.bankcomm.com:8724/mobs6.0-TMF-UAT/home/oss/pub/file/img/public/wonhot/images/ZPa856c35de36c457c80c3c6edad373ea3.lottie?InstRoom=zj";
    private String lottie2 = "https://mbanktest.bankcomm.com:8724/mobs6.0-TMF-UAT/home/oss/pub/file/img/public/wonhot/images/ZP33357b3f30544a20b4adc2bf3c4dd49b.lottie?InstRoom=zj";

    private String lottie3 = "https://mbanktest.bankcomm.com:8724/mobs6.0-TMF-UAT/home/oss/pub/file/img/public/wonhot/images/ZP58ec6b59a7ad436dab30f8148a91c2d3.lottie?InstRoom=zj";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lottie_test);
        Button change = findViewById(R.id.change_size_view);
        Button changeLottie = findViewById(R.id.change_lottie3);
        LottieAnimationView lottieAnimationView1 = findViewById(R.id.lottie_test1);
        lottieAnimationView1.setAnimationFromUrl(lottie3);
        lottieAnimationView1.playAnimation();

        LottieAnimationView lottieAnimationView2 = findViewById(R.id.lottie_test2);
        lottieAnimationView2.setAnimation(getResources().openRawResource(R.raw.star),"zhuyi");
        lottieAnimationView2.setAnimationFromUrl(url,"cachekey");
        lottieAnimationView2.playAnimation();

        change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ConstraintLayout.LayoutParams params = (ConstraintLayout.LayoutParams) lottieAnimationView2.getLayoutParams();
                params.width = DisplayUtil.dpToPx(LottieTestActivity.this, 200f);
                params.width = DisplayUtil.dpToPx(LottieTestActivity.this, 300f);
                lottieAnimationView2.setLayoutParams(params);
            }
        });


        LottieAnimationView lottieAnimationView3 = findViewById(R.id.animationView3);
        lottieAnimationView3.playAnimation();

        changeLottie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LottieComposition composition = lottieAnimationView2.getComposition();
                if (composition != null) {
                    lottieAnimationView3.setComposition(composition);
                }
            }
        });

        LottieAnimationView lottieAnimationView4 = findViewById(R.id.animationView4);
        lottieAnimationView4.playAnimation();
        lottieAnimationView1.addAnimatorListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(@NonNull Animator animation) {
                Log.e(TAG, " lottieAnimationView1  onAnimationStart动画开始了======");
            }

            @Override
            public void onAnimationEnd(@NonNull Animator animation) {
                Log.e(TAG, " lottieAnimationView1  onAnimationEnd======");
            }

            @Override
            public void onAnimationCancel(@NonNull Animator animation) {
                Log.e(TAG, " lottieAnimationView1  onAnimationCancel======");
            }

            @Override
            public void onAnimationRepeat(@NonNull Animator animation) {
                Log.e(TAG, " lottieAnimationView1  onAnimationRepeat======");
            }
        });

        lottieAnimationView2.addAnimatorListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(@NonNull Animator animation) {
                Log.e(TAG, " lottieAnimationView2  onAnimationStart动画开始了======");
            }

            @Override
            public void onAnimationEnd(@NonNull Animator animation) {
                Log.e(TAG, " lottieAnimationView2  onAnimationEnd======");
            }

            @Override
            public void onAnimationCancel(@NonNull Animator animation) {
                Log.e(TAG, " lottieAnimationView2  onAnimationCancel======");
            }

            @Override
            public void onAnimationRepeat(@NonNull Animator animation) {
                Log.e(TAG, " lottieAnimationView2  onAnimationRepeat======");
            }
        });
        lottieAnimationView1.setAnimationFromUrl(lottie1, "");
        lottieAnimationView1.playAnimation();

        lottieAnimationView2.setAnimationFromUrl(lottie2);
        lottieAnimationView2.playAnimation();

    }

    @Override
    public void onConfigurationChanged(@NonNull Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        float density = Resources.getSystem().getDisplayMetrics().density;
        Log.e(TAG, " onConfigurationChanged  density   " + density);
        float fontScale = newConfig.fontScale;
        Log.e(TAG, " onConfigurationChanged  fontScale   " + fontScale);
    }
}
