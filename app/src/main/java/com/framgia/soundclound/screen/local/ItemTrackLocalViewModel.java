package com.framgia.soundclound.screen.local;

import android.databinding.BaseObservable;
import android.view.View;

import com.framgia.soundclound.data.model.Track;
/**
 * Created by ADMIN on 1/7/2018.
 */

public class ItemTrackLocalViewModel extends BaseObservable {

    private Track mTrack;
    private TrackClickListener mTrackClickListener;

    public ItemTrackLocalViewModel(Track track, TrackClickListener trackClickListener) {
        mTrack = track;
        mTrackClickListener = trackClickListener;
    }

    public void onClickTrack() {
        if (mTrackClickListener == null) {
            return;
        }
        mTrackClickListener.onItemTrackClick(mTrack);
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

    public void onClickTrack(View view) {
        if (mTrackClickListener == null) {
            return;
        }
        mTrackClickListener.onItemTrackClick(mTrack);
    }
}
