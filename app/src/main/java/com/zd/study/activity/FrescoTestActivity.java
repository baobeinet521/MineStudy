package com.zd.study.activity;

import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;
import com.zd.study.R;

public class FrescoTestActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fresco_test_activity);

        SimpleDraweeView simpleDraweeView= (SimpleDraweeView) findViewById(R.id.drawable_test);
        DraweeController draweeController = Fresco.newDraweeControllerBuilder()
                .setAutoPlayAnimations(true)
                //设置uri,加载本地的gif资源
                .setUri(Uri.parse("res://"+getPackageName()+"/"+R.mipmap.test))
                .build();
        //设置Controller
        simpleDraweeView.setController(draweeController);
    }
}
