package com.zhuke.yixun.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.view.View;

import com.zhuke.comlibrary.base.BaseFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 15653 on 2018/3/26.
 */

public class MainFragmentAdapter extends FragmentPagerAdapter {
    ArrayList<BaseFragment> list;
    public MainFragmentAdapter(FragmentManager fm, ArrayList<BaseFragment> list) {
        super(fm);
        this.list =  list;
    }

    @Override
    public Fragment getItem(int position) {
        return list.get(position);
    }

    @Override
    public int getCount() {
        return list.size();
    }
}
