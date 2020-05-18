package com.example.cttarde.tcc_wissen.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;


import com.example.cttarde.tcc_wissen.activity.HomeClassActivity;
import com.example.cttarde.tcc_wissen.fragment.ItemFragmentSug;

public class CarouselPagerAdapterSug extends FragmentPagerAdapter implements ViewPager.OnPageChangeListener {

    public final static float BIG_SCALE = 1.0f;
    public final static float SMALL_SCALE = 0.7f;
    private HomeClassActivity context;
    private FragmentManager fragmentManager;
    private float scale;




    public CarouselPagerAdapterSug(HomeClassActivity context, FragmentManager fm) {
        super(fm);
        this.fragmentManager = fm;
        this.context = context;
    }

    @Override
    public Fragment getItem(int position) {
        // make the first pager bigger than others
        try {
            if (position == HomeClassActivity.FIRST_PAGE4)
                scale = BIG_SCALE;
            else
                scale = SMALL_SCALE;

            position = position % ItemFragmentSug.count4;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return ItemFragmentSug.newInstance4(context, position, scale);
    }

    @Override
    public int getCount() {
        int count = 0;
        try {
            count = ItemFragmentSug.count4;
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        return count;
    }


    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }



}