package com.framgia.soundclound.data.source;
import android.support.annotation.NonNull;

import com.framgia.soundclound.data.model.Track;

import java.util.List;

/**
 * Created by Sony on 1/5/2018.
 */

public class TrackRepository implements TrackDataSource {

    private TrackDataSource mRemoteDataSource;

    public TrackRepository(TrackDataSource trackDataSource) {
        mRemoteDataSource = trackDataSource;
    }

    @Override
    public void getListTrack(String url, String genre, int limit, @NonNull int offSet,
                             Callback<List<Track>> callback) {
        mRemoteDataSource.getListTrack(url, genre, limit, offSet, callback);
    }

    @Override
    public List<Track> getLocalTrack() {
        return mRemoteDataSource.getLocalTrack();
    }

}
