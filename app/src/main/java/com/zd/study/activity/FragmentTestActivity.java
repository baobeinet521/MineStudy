package com.zd.study.activity;

import android.os.Bundle;
import android.widget.FrameLayout;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager2.widget.ViewPager2;

import com.zd.study.R;
import com.zd.study.fragment.FragmentAdapter;
import com.zd.study.fragment.FragmentTestFour;
import com.zd.study.fragment.FragmentTestOne;
import com.zd.study.fragment.FragmentTestThree;
import com.zd.study.fragment.FragmentTestTwo;

import java.util.ArrayList;
import java.util.List;

public class FragmentTestActivity extends AppCompatActivity{
    private FragmentTestOne mFragmentTestOne;
    private FragmentTestTwo mFragmentTestTwo;
    private FragmentTestThree mFragmentTestThree;
    private FragmentTestFour mFragmentTestFour;

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
        mViewPager2.setUserInputEnabled(true);
        FragmentAdapter adapter = new FragmentAdapter(this);

        FragmentAdapter myAdapter = adapter;
        mViewPager2.setOffscreenPageLimit(adapter.getItemCount()-1);
        mViewPager2.setAdapter(myAdapter);

    }


//    public void test(){
//        fragmentManager = getSupportFragmentManager();
//        fragmentTransaction = fragmentManager.beginTransaction();
//
//        mFragmentTestOne = new FragmentTestOne();
//        mFragmentTestTwo = new FragmentTestTwo();
//        mFragmentTestThree = new FragmentTestThree();
//        mFragmentTestFour = new FragmentTestFour();
//
//        fragmentTransaction.add(R.id.fragment_container, mFragmentTestOne,null);
//        fragmentTransaction.add(R.id.fragment_container, mButtomFragment,null);
//
//        /*fragmentTransaction.replace(R.id.content_layout, mContentFragment);
//        fragmentTransaction.replace(R.id.buttom_layout, mButtomFragment);*/
//        fragmentTransaction.commit();
//    }
}
