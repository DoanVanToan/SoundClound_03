package com.framgia.soundclound.data.source.remote;

import android.app.DownloadManager;
import android.content.Context;
import android.net.Uri;
import android.os.Environment;

import com.framgia.soundclound.data.model.Track;

/**
 * Created by Sony on 1/11/2018.
 */

public class TrackDownloadManager {
    private Track mTrack;
    private Context mContext;

    public TrackDownloadManager(Context context, Track track) {
        mContext = context;
        mTrack = track;
    }

    public void download() {
        DownloadManager downloadManager = (DownloadManager)
                mContext.getSystemService(Context.DOWNLOAD_SERVICE);
        DownloadManager.Request request = new DownloadManager.Request(Uri.parse(
                mTrack.getDowloadUrl()));
        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE);
        request.setTitle(mTrack.getTitle());
        request.setDestinationInExternalFilesDir(mContext, Environment.DIRECTORY_MUSIC,
                mTrack.getDowloadUrl());
        downloadManager.enqueue(request);
    }
}
