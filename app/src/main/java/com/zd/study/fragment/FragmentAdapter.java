package com.zd.study.fragment;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import java.util.ArrayList;
import java.util.List;

public class FragmentAdapter extends FragmentStateAdapter {
    private HomeFragment mHomeFragment;
    private FragmentTestTwo mFragmentTestTwo;
    private FragmentTestThree mFragmentTestThree;
    private FragmentTestFour mFragmentTestFour;
    private MineFragment mMineFragment;

    List<Fragment> list = new ArrayList<>();

    public FragmentAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
        mHomeFragment = new HomeFragment();
        mFragmentTestTwo = new FragmentTestTwo();
        mFragmentTestThree = new FragmentTestThree();
        mFragmentTestFour = new FragmentTestFour();
        mFragmentTestFour = new FragmentTestFour();
        mMineFragment = new MineFragment();
        setList();
    }

    public void setList(){
        list.add(mHomeFragment);
        list.add(mFragmentTestTwo);
        list.add(mFragmentTestThree);
        list.add(mFragmentTestFour);
        list.add(mMineFragment);
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
