package com.ln.fragmentbottab.adapter;

import android.widget.Adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.List;

public class MyFragmentStateVPAdapter extends FragmentPagerAdapter {
    private List<Fragment> mfragmentList;
    public MyFragmentStateVPAdapter(@NonNull FragmentManager fm, List<Fragment> fragmentList) {
        super(fm);
        mfragmentList=fragmentList;
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
}
