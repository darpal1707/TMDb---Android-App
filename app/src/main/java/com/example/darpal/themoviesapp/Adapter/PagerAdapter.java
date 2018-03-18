package com.example.darpal.themoviesapp.Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.darpal.themoviesapp.NowPlayingTabFragment;
import com.example.darpal.themoviesapp.UpComingTabFragment;

public class PagerAdapter extends FragmentStatePagerAdapter {

    int mNumTabs;
    public PagerAdapter(FragmentManager fm, int NumTabs) {
        super(fm);
        this.mNumTabs = NumTabs;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                NowPlayingTabFragment nowPlayingTabFragment = new NowPlayingTabFragment();
                return nowPlayingTabFragment;

            case 1:
                UpComingTabFragment upComingTabFragment = new UpComingTabFragment();
                return upComingTabFragment;

            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return mNumTabs;
    }
}
