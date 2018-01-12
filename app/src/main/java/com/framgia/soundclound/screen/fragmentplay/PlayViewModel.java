package com.framgia.soundclound.screen.fragmentplay;

import android.databinding.BaseObservable;

import com.framgia.soundclound.data.model.Track;

import java.util.List;

/**
 * Created by Sony on 1/12/2018.
 */

public class PlayViewModel extends BaseObservable {
    private List<Track> mTracks;
    private Track mTrack;

    public PlayViewModel(List<Track> tracks, Track track) {
        mTracks = tracks;
        mTrack = track;
    }
}
