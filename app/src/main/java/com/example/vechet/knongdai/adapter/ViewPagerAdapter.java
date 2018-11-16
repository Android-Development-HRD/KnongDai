package com.example.vechet.knongdai.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

public class ViewPagerAdapter extends FragmentPagerAdapter {

    ArrayList<Fragment> list_fragment = new ArrayList<>();
    ArrayList<Integer> list_icon = new ArrayList<>();


    public ViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {

        return list_fragment.get(position);
    }

    @Override
    public int getCount() {
        return list_fragment.size();
    }

    //add Fragment and Icon
    public void addFragment(Fragment fragment, int icon){
        list_fragment.add(fragment);
        list_icon.add(icon);
    }
}
