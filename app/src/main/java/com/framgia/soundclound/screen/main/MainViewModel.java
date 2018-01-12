package com.framgia.soundclound.screen.main;

import android.content.Context;
import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.framgia.soundclound.BR;
import com.framgia.soundclound.R;

/**
 * Created by Sony on 1/4/2018.
 */

public class MainViewModel extends BaseObservable {
    private Context mContext;
    private MainActivity mActivity;
    private MainPagerAdapter mPagerAdapter;

    public MainViewModel(Context context) {
        mContext = context;
        mActivity = (MainActivity) context;
        mPagerAdapter = new MainPagerAdapter(mActivity.getSupportFragmentManager());
    }

    @Bindable
    public MainPagerAdapter getPagerAdapter() {
        return mPagerAdapter;
    }

    public void setPagerAdapter(MainPagerAdapter pagerAdapter) {
        mPagerAdapter = pagerAdapter;
        notifyPropertyChanged(BR.pagerAdapter);
    }

    @Bindable
    public int getTabSelectedColor() {
        return R.color.colorAccent;
    }

    @Bindable
    public int getTabUnSelectedColor() {
        return R.color.textFade;
    }

    @Bindable
    public int getTabSelectedHome() {
        return R.color.colorAccent;
    }


}
