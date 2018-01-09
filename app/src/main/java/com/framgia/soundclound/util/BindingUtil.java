package com.framgia.soundclound.util;

import android.databinding.BindingAdapter;
import android.graphics.PorterDuff;
import android.support.design.widget.TabLayout;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;

import com.framgia.soundclound.screen.main.TabType;

/**
 * Created by Sony on 1/4/2018.
 */

public class BindingUtil {

    @BindingAdapter({"pagerAdapter"})
    public static void setViewPagerAdapter(ViewPager viewPager, PagerAdapter adapter) {
        viewPager.setAdapter(adapter);
    }

    @BindingAdapter({"selectedColor", "unSelectedColor", "viewPager"})
    public static void setupTabIndicatorListener(final TabLayout tabLayout, final int selectedColor,
                                                 final int unSelectedColor,
                                                 final ViewPager viewPager) {
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if (tab.getIcon() == null) {
                    return;
                }
                tab.getIcon().setColorFilter(ContextCompat.getColor(tabLayout.getContext(),
                        selectedColor), PorterDuff.Mode.SRC_ATOP);
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                if (tab.getIcon() == null) {
                    return;
                }
                tab.getIcon().setColorFilter(ContextCompat.getColor(tabLayout.getContext(),
                        unSelectedColor), PorterDuff.Mode.SRC_ATOP);
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                // noops
            }
        });
    }

    @BindingAdapter({"setTabHome"})
    public static void setUpColorFilterHome(final TabLayout tabLayout, final int selectedHome) {
        if (tabLayout.getTabAt(TabType.HOME).getIcon() != null) {
            int selectedColor = ContextCompat.getColor(tabLayout.getContext(), selectedHome);
            tabLayout.getTabAt(TabType.HOME).getIcon().setColorFilter(selectedColor,
                    PorterDuff.Mode.SRC_ATOP);
        }
    }

}
