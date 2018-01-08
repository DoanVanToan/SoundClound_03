package com.framgia.soundclound.screen.local;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;

import com.framgia.soundclound.BR;
import com.framgia.soundclound.data.model.Track;
import com.framgia.soundclound.util.Constant;

/**
 * Created by ADMIN on 1/7/2018.
 */

public class TrackLocalViewModel extends BaseObservable implements TrackClickListener {

    private TrackLocalAdapter mTrackLocalAdapter;
    private Context mContext;
    
    public TrackLocalViewModel(Context context) {
        mContext = context;
        mTrackLocalAdapter = new TrackLocalAdapter();
        mTrackLocalAdapter.setTrackClickLisener(this);
        if (checkPermisson()) {
            // TODO: 1/11/2018  getData 
        }
    }

    @Bindable
    public TrackLocalAdapter getTrackLocalAdapter() {
        return mTrackLocalAdapter;
    }

    public void setTrackLocalAdapter(TrackLocalAdapter trackLocalAdapter) {
        mTrackLocalAdapter = trackLocalAdapter;
        notifyPropertyChanged(BR.trackLocalAdapter);
    }

    @Override
    public void onItemTrackClick(Track track) {
        // TODO: 1/10/2018 open playtrack
    }

    public boolean checkPermisson() {
        boolean isAllow = true;
        if (ContextCompat.checkSelfPermission(mContext,
                Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            isAllow = false;
            ActivityCompat.requestPermissions((Activity) mContext,
                    Constant.PERMISSON, Constant.REQUEST_READ_STORAGE);
        }
        return isAllow;
    }

}
