package com.framgia.soundclound.data.source.remote;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;

import com.framgia.soundclound.data.model.Collection;
import com.framgia.soundclound.data.model.CollectionResult;
import com.framgia.soundclound.data.model.PublisherMetadata;
import com.framgia.soundclound.data.model.Track;
import com.framgia.soundclound.data.source.TrackDataSource;
import com.framgia.soundclound.util.Constant;
import com.framgia.soundclound.util.StringUtil;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;
/**
 * Created by Sony on 1/5/2018.
 */

public class TrackRemoteDataSource implements TrackDataSource {

    private static TrackRemoteDataSource sTrackRemoteDataSource;
    private Context mContext;

    public TrackRemoteDataSource(Context context) {
        mContext = context;

    }

    public static TrackRemoteDataSource getInstance() {
        return sTrackRemoteDataSource;
    }

    public static void init(Context context) {
        if (sTrackRemoteDataSource == null) {
            sTrackRemoteDataSource = new TrackRemoteDataSource(context);
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

    public List<Track> getLocalTrack() {
        ContentResolver contentResolver = mContext.getContentResolver();
        Uri uri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
        Cursor cursor = contentResolver.query(uri, null, null,
                null, null);
        List<Track> listLocal = new ArrayList<>();
        while (cursor.moveToNext()) {
            Track track = new Track();

            track.setUri(cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.DATA)));
            track.setTitle(
                    cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.TITLE)));
            track.setFullDuration(
                    cursor.getInt(cursor.getColumnIndex(MediaStore.Audio.Media.DURATION)));
            track.setPublisherMetadata(new PublisherMetadata(cursor.getString(
                    cursor.getColumnIndex(MediaStore.Audio.Media.ARTIST))));
            listLocal.add(track);
        }
        cursor.close();
        return listLocal;
    }

}
