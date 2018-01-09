package com.framgia.soundclound.data.source.remote;

import com.framgia.soundclound.data.model.Collection;
import com.framgia.soundclound.data.model.CollectionResult;
import com.framgia.soundclound.data.model.Track;
import com.framgia.soundclound.data.source.TrackDataSource;
import com.framgia.soundclound.util.Constant;
import com.framgia.soundclound.util.StringUtil;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

/**
 * Created by Sony on 1/5/2018.
 */

public class TrackRemoteDataSource implements TrackDataSource {

    private static TrackRemoteDataSource sTrackRemoteDataSource;

    public TrackRemoteDataSource() {
    }

    public static TrackRemoteDataSource getInstance() {
        return sTrackRemoteDataSource;
    }

    public static void init() {
        if (sTrackRemoteDataSource == null) {
            sTrackRemoteDataSource = new TrackRemoteDataSource();
        }
    }

    @Override
    public void getListTrack(String url, String genre, int limit, int offSet,
                             final Callback<List<Track>> callback) {
        new LoadAsync(new TrackDataSource.Callback<String>() {
            @Override
            public void onStartLoading() {
                callback.onStartLoading();
            }

            @Override
            public void onGetSuccess(String data) {
                if (data == null) {
                    callback.onGetFailure(Constant.ERROR_NULL);
                    return;
                }
                CollectionResult collectionResult =
                        new Gson().fromJson(data, CollectionResult.class);
                if (collectionResult == null) {
                    callback.onGetFailure(Constant.ERROR_NULL);
                    return;
                }
                List<Track> result = new ArrayList<>();
                for (Collection collection : collectionResult.getCollection()) {
                    result.add(collection.getTrack());
                }
                callback.onGetSuccess(result);
            }

            @Override
            public void onGetFailure(String message) {
                callback.onGetFailure(message);
            }

            @Override
            public void onComplete() {
                callback.onComplete();
            }
        }).execute(StringUtil.convertUrl(url, genre, limit, offSet));

    }
}
