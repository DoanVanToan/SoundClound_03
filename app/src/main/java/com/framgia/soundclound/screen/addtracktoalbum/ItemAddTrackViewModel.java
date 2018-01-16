package com.framgia.soundclound.screen.addtracktoalbum;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.view.View;

import com.framgia.soundclound.data.model.Track;
import com.framgia.soundclound.util.Constant;


/**
 * Created by ADMIN on 1/14/2018.
 */

public class ItemAddTrackViewModel extends BaseObservable {

    private Track mTrack;
    private CheckCliclListener mCheckCliclListener;
    private int mPosition;
    private boolean mIsChecked;

    public ItemAddTrackViewModel(Track track, CheckCliclListener checkCliclListener, int position,
                                 boolean isChecked) {
        mTrack = track;
        mCheckCliclListener = checkCliclListener;
        mPosition = position;
        mIsChecked = isChecked;
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
        if (mCheckCliclListener == null) {
            return;
        }
        mCheckCliclListener.onTrackClickListenner(mTrack, mPosition, isChecked());
        setChecked(!mIsChecked);
    }

    @Bindable
    public boolean isChecked() {
        return mIsChecked;
    }

    public void setChecked(boolean checked) {
        mIsChecked = checked;
        notifyPropertyChanged(com.framgia.soundclound.BR.checked);
    }

}
