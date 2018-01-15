package com.framgia.soundclound.screen.detailgenre;

import android.databinding.BaseObservable;
import android.view.View;

import com.framgia.soundclound.data.model.Track;
import com.framgia.soundclound.util.Constant;

/**
 * Created by ADMIN on 1/6/2018.
 */

public class ItemGenreDetailViewModel extends BaseObservable {
    private TrackClickListener mTrackClickListener;
    private MoreInfoClickListener mMoreInfoClickListener;
    private Track mTrack;
    private int mPosition;

    public ItemGenreDetailViewModel(Track track, int position,
                                    TrackClickListener trackClickListener,
                                    MoreInfoClickListener moreInfoClickListener) {
        mTrack = track;
        mPosition = position;
        mMoreInfoClickListener = moreInfoClickListener;
        mTrackClickListener = trackClickListener;
    }

    public String getPosition() {
        return String.valueOf(mPosition);
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

    public void onClickTrack(View view) {
        if (mTrackClickListener == null) {
            return;
        }
        mTrackClickListener.onItemTrackClick(mTrack, mPosition);
    }

    public void onMoreInfoClick(View view) {
        if (mMoreInfoClickListener == null) {
            return;
        }
        mMoreInfoClickListener.onClickMore(mTrack);
    }

}
