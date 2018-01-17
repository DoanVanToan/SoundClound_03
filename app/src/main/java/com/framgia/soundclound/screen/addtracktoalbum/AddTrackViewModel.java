package com.framgia.soundclound.screen.addtracktoalbum;

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
import com.framgia.soundclound.data.source.TrackRepository;
import com.framgia.soundclound.data.source.remote.TrackRemoteDataSource;
import com.framgia.soundclound.data.source.repository.AlbumRepository;
import com.framgia.soundclound.screen.detailalbum.DetailAlbumActivity;
import com.framgia.soundclound.util.Constant;

import java.util.List;

/**
 * Created by ADMIN on 1/14/2018.
 */

public class AddTrackViewModel extends BaseObservable implements CheckCliclListener {

    private AddTrackAdapter mAddTrackAdapter;
    private Context mContext;
    private TrackRepository mTrackRepository;
    private List<Track> mTracks;
    private int mId;

    public AddTrackViewModel(Context context, int id) {
        mContext = context;
        mId = id;
        mAddTrackAdapter = new AddTrackAdapter();
        mTrackRepository = new TrackRepository(TrackRemoteDataSource.getInstance());
        mAddTrackAdapter.setCheckClickListener(this);
        if (checkPermisson()) {
            getData();
        }
    }

    public void getData() {
        mTracks = mTrackRepository.getLocalTrack();
        mAddTrackAdapter.addData(mTracks);
    }

    @Bindable
    public AddTrackAdapter getAddTrackAdapter() {
        return mAddTrackAdapter;
    }

    public void setAddTrackAdapter(AddTrackAdapter addTrackAdapter) {
        mAddTrackAdapter = addTrackAdapter;
        notifyPropertyChanged(BR.addTrackAdapter);
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

    public void onClickSuccess() {
        for (Track track : mTracks) {
            if (!track.isChecked()) {
                continue;
            }
            AlbumRepository.getInstance(mContext).addTrack(mId, track);
        }
        mContext.startActivity(DetailAlbumActivity.getInstance(mContext, mId));

    }

    @Override
    public void onTrackClickListenner(Track track, int position, boolean isChecked) {
        mTracks.get(position).setChecked(!isChecked);
    }
}