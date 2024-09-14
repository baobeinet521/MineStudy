package com.zd.study.activity;

import android.os.Bundle;
import android.widget.FrameLayout;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager2.widget.ViewPager2;

import com.zd.study.R;
import com.zd.study.fragment.FragmentAdapter;

public class FragmentTestActivity extends AppCompatActivity{

    private FragmentTransaction fragmentTransaction;
    private FragmentManager fragmentManager;

    private FrameLayout mFrameLayout;
    private ViewPager2 mViewPager2;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_fragment);
        mFrameLayout = findViewById(R.id.fragment_container);
        mViewPager2 = findViewById(R.id.view_pager);
        mViewPager2.setUserInputEnabled(false);
        FragmentAdapter adapter = new FragmentAdapter(this);

        FragmentAdapter myAdapter = adapter;
        mViewPager2.setOffscreenPageLimit(adapter.getItemCount()-1);
        mViewPager2.setAdapter(myAdapter);

    }
}
