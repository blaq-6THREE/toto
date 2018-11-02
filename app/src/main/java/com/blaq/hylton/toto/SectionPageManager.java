package com.blaq.hylton.toto;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

import java.util.ArrayList;
import java.util.List;

public class SectionPageManager extends FragmentPagerAdapter
{
    private List<Fragment> mFragmentList;
    private List<String> mFragmentTitleList;

    public SectionPageManager(FragmentManager fm)
    {
        super(fm);
        mFragmentList = new ArrayList<>();
        mFragmentTitleList = new ArrayList<>();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return mFragmentTitleList.get(position);
    }

    @Override
    public Fragment getItem(int position) {
        return mFragmentList.get(position);
    }

    @Override
    public int getCount() {
        return mFragmentList.size();
    }

    public void addAdapter(Fragment fragment, String title)
    {
        mFragmentList.add(fragment);
        mFragmentTitleList.add(title);
    }
}
