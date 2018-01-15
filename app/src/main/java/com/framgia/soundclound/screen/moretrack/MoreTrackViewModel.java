package com.framgia.soundclound.screen.moretrack;

import android.content.Context;
import android.databinding.BaseObservable;
import android.view.View;

import com.framgia.soundclound.R;
import com.framgia.soundclound.data.model.Track;
import com.framgia.soundclound.data.source.remote.TrackDownloadManager;
import com.framgia.soundclound.util.Constant;

/**
 * Created by Sony on 1/10/2018.
 */

public class MoreTrackViewModel extends BaseObservable {
    private Track mTrack;
    private Context mContext;
    private String canDownLoad;

    public MoreTrackViewModel(Context context, Track track) {
        mContext = context;
        mTrack = track;
    }

    public String getTitle() {
        return mTrack != null ? mTrack.getTitle() : "";
    }

    public String getArtist() {
        if (mTrack != null) {
            return mTrack.getPublisherMetadata() != null ? mTrack.getPublisherMetadata().getArtist()
                    : "";
        }
        return "";
    }

    public String getUrl() {
        return mTrack != null ? mTrack.getArtworkUrl() : Constant.LINK_DEFAULT;
    }

    public String getCanDownLoad() {
        if (mTrack != null) {
            return mTrack.isDownloadable() ? mContext.getString(R.string.title_candownload)
                    : mContext.getString(R.string.title_cantdownload);
        }
        return mContext.getString(R.string.title_cantdownload);
    }

    public void onClickLike(View view) {
        // TODO: 1/10/2018  likee

    }

    public void onClickDownload(View view) {
        if (mTrack.isDownloadable()) {
            TrackDownloadManager trackDownloadManager = new TrackDownloadManager(mContext, mTrack);
            trackDownloadManager.download();
        }
    }
    
}
