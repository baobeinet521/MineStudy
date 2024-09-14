package com.zd.study.fragment;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import java.util.ArrayList;
import java.util.List;

public class FragmentAdapter extends FragmentStateAdapter {
    private FragmentTestOne mFragmentTestOne;
    private FragmentTestTwo mFragmentTestTwo;
    private FragmentTestThree mFragmentTestThree;
    private FragmentTestFour mFragmentTestFour;
    private FragmentTestFive mFragmentTestFive;

    List<Fragment> list = new ArrayList<>();

    public FragmentAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
        mFragmentTestOne = new FragmentTestOne();
        mFragmentTestTwo = new FragmentTestTwo();
        mFragmentTestThree = new FragmentTestThree();
        mFragmentTestFour = new FragmentTestFour();
        mFragmentTestFour = new FragmentTestFour();
        mFragmentTestFive = new FragmentTestFive();
        setList();
    }

    public void setList(){
        list.add(mFragmentTestOne);
        list.add(mFragmentTestTwo);
        list.add(mFragmentTestThree);
        list.add(mFragmentTestFour);
        list.add(mFragmentTestFive);
    }

    @NonNull
    @Override
    public Fragment createFragment(int i) {
        return list.get(i);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
