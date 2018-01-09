package com.framgia.soundclound.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Sony on 1/5/2018.
 */

public class Collection {
    @SerializedName("track")
    @Expose
    private Track mTrack;
    @SerializedName("score")
    @Expose
    private int mScore;

    public Track getTrack() {
        return mTrack;
    }

    public void setTrack(Track track) {
        mTrack = track;
    }

    public int getScore() {
        return mScore;
    }

    public void setScore(int score) {
        mScore = score;
    }
}
