package com.framgia.soundclound.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Sony on 1/5/2018.
 */

public class PublisherMetadata {

    @SerializedName("id")
    @Expose
    private int mId;
    @SerializedName("urn")
    @Expose
    private String mUrn;
    @SerializedName("artist")
    @Expose
    private String mArtist;
    @SerializedName("album_title")
    @Expose
    private String mAlbumTitle;
    @SerializedName("contains_music")
    @Expose
    private boolean mContainsMusic;

    public PublisherMetadata(String artist) {
        mArtist = artist;
    }

    public int getId() {
        return mId;
    }

    public void setId(int id) {
        mId = id;
    }

    public String getUrn() {
        return mUrn;
    }

    public void setUrn(String urn) {
        mUrn = urn;
    }

    public String getArtist() {
        return mArtist;
    }

    public void setArtist(String artist) {
        mArtist = artist;
    }

    public String getAlbumTitle() {
        return mAlbumTitle;
    }

    public void setAlbumTitle(String albumTitle) {
        mAlbumTitle = albumTitle;
    }

    public boolean isContainsMusic() {
        return mContainsMusic;
    }

    public void setContainsMusic(boolean containsMusic) {
        mContainsMusic = containsMusic;
    }
}
