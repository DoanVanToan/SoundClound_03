package com.framgia.soundclound.screen.moretrack;

import android.databinding.BaseObservable;
import android.view.View;

import com.framgia.soundclound.data.model.Track;
import com.framgia.soundclound.util.Constant;

/**
 * Created by Sony on 1/10/2018.
 */

public class MoreTrackViewModel extends BaseObservable {
    private Track mTrack;

    public MoreTrackViewModel(Track track) {
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

    public void onClickLike(View view)  {
        // TODO: 1/10/2018  likee

    }

    public void onClickDownload(View view){
        // TODO: 1/10/2018  download file audio
    }
}
