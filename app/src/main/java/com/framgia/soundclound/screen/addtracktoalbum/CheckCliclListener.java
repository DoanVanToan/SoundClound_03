package com.framgia.soundclound.screen.addtracktoalbum;

import com.framgia.soundclound.data.model.Track;

/**
 * Created by ADMIN on 1/14/2018.
 */

public interface CheckCliclListener {
    void onTrackClickListenner(Track track, int position, boolean isChecked);
}
