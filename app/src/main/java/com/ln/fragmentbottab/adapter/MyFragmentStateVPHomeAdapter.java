package com.ln.fragmentbottab.adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.List;

public class MyFragmentStateVPHomeAdapter extends FragmentPagerAdapter {
    private List<Fragment> mfragmentList;
    private List<String> mtitleList;
    public MyFragmentStateVPHomeAdapter(@NonNull FragmentManager fm, List<Fragment> fragmentList,List<String> titleList) {
        super(fm);
        mfragmentList=fragmentList;
        mtitleList=titleList;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return mfragmentList==null ?null : mfragmentList.get(position);
    }

    @Override
    public int getCount() {
        return mfragmentList==null ?0 : mfragmentList.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return mtitleList==null ? null :mtitleList.get(position);
    }
}
