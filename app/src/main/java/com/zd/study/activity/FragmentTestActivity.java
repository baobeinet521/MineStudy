package com.zd.study.activity;

import android.os.Bundle;
import android.widget.FrameLayout;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;
import com.zd.study.R;
import com.zd.study.fragment.FragmentAdapter;

/**
 * @author zd
 */
public class FragmentTestActivity extends AppCompatActivity {

    private FragmentTransaction fragmentTransaction;
    private FragmentManager fragmentManager;

    private TabLayout mTabsLayout;
    private ViewPager2 mViewPager2;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_fragment);
        mTabsLayout = findViewById(R.id.tabs_layout);
        mViewPager2 = findViewById(R.id.view_pager);
        mViewPager2.setUserInputEnabled(false);
        FragmentAdapter adapter = new FragmentAdapter(this);

        mViewPager2.setOffscreenPageLimit(adapter.getItemCount() - 1);
        mViewPager2.setAdapter(adapter);

    }
}
