package com.purduediet.helloworld.purduedietdining.fragments;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class FragmentPageAdapter extends FragmentPagerAdapter {

    public static final String[] HEADINGS = new String[]{"BREAKFAST", "LUNCH", "DINNER"};

    public FragmentPageAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int i) {
        switch (i){
            case 0: return new BreakfastFragment();
            case 1: return new LunchFragment();
        }
        return new DinnerFragment();
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return HEADINGS[position];
    }
}
