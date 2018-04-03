package com.opengl.zhanf.nestrecyclerview.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import com.opengl.zhanf.nestrecyclerview.ui.IntensiveFragment;

import java.util.ArrayList;

public class ZhanfPagerAdapter extends FragmentPagerAdapter {

    public ZhanfPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return IntensiveFragment.newInstance(position);
    }

    @Override
    public int getCount() {
        return 10;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return super.isViewFromObject(view, object);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
//        container.removeView();
        super.destroyItem(container, position, object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        return super.instantiateItem(container, position);
    }
}
