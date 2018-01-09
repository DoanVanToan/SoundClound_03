package com.framgia.soundclound.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Sony on 1/5/2018.
 */

public class Track {
    @SerializedName("artwork_url")
    @Expose
    private String mArtworkUrl;
    @SerializedName("comment_count")
    @Expose
    private int mCommentCount;
    @SerializedName("description")
    @Expose
    private String mDescription;
    @SerializedName("download_count")
    @Expose
    private int mDownloadCount;
    @SerializedName("download_url")
    @Expose
    private int mDuration;
    @SerializedName("full_duration")
    @Expose
    private int mFullDuration;
    @SerializedName("genre")
    @Expose
    private String mGenre;
    @SerializedName("id")
    @Expose
    private int mId;
    @SerializedName("likes_count")
    @Expose
    private int mLikesCount;
    @SerializedName("permalink_url")
    @Expose
    private String mPermalinkUrl;
    @SerializedName("playback_count")
    @Expose
    private int mPlaybackCount;
    @SerializedName("publisher_metadata")
    @Expose
    private PublisherMetadata mPublisherMetadata;
    @SerializedName("title")
    @Expose
    private String mTitle;
    @SerializedName("uri")
    @Expose
    private String mUri;
    @SerializedName("user_id")
    @Expose
    private int mUserId;
    @SerializedName("display_date")
    @Expose
    private String mDisplayDate;

    public Track(String title) {
        mTitle = title;
    }

    public Track() {}

    public String getArtworkUrl() {
        return mArtworkUrl;
    }

    public void setArtworkUrl(String artworkUrl) {
        mArtworkUrl = artworkUrl;
    }

    public int getCommentCount() {
        return mCommentCount;
    }

    public void setCommentCount(int commentCount) {
        mCommentCount = commentCount;
    }

    public String getDescription() {
        return mDescription;
    }

    public void setDescription(String description) {
        mDescription = description;
    }

    public int getDownloadCount() {
        return mDownloadCount;
    }

    public void setDownloadCount(int downloadCount) {
        mDownloadCount = downloadCount;
    }

    public int getDuration() {
        return mDuration;
    }

    public void setDuration(int duration) {
        mDuration = duration;
    }

    public int getFullDuration() {
        return mFullDuration;
    }

    public void setFullDuration(int fullDuration) {
        mFullDuration = fullDuration;
    }

    public String getGenre() {
        return mGenre;
    }

    public void setGenre(String genre) {
        mGenre = genre;
    }

    public int getId() {
        return mId;
    }

    public void setId(int id) {
        mId = id;
    }

    public int getLikesCount() {
        return mLikesCount;
    }

    public void setLikesCount(int likesCount) {
        mLikesCount = likesCount;
    }

    public String getPermalinkUrl() {
        return mPermalinkUrl;
    }

    public void setPermalinkUrl(String permalinkUrl) {
        mPermalinkUrl = permalinkUrl;
    }

    public int getPlaybackCount() {
        return mPlaybackCount;
    }

    public void setPlaybackCount(int playbackCount) {
        mPlaybackCount = playbackCount;
    }

    public PublisherMetadata getPublisherMetadata() {
        return mPublisherMetadata;
    }

    public void setPublisherMetadata(PublisherMetadata publisherMetadata) {
        mPublisherMetadata = publisherMetadata;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public String getUri() {
        return mUri;
    }

    public void setUri(String uri) {
        mUri = uri;
    }

    public int getUserId() {
        return mUserId;
    }

    public void setUserId(int userId) {
        mUserId = userId;
    }

    public String getDisplayDate() {
        return mDisplayDate;
    }

    public void setDisplayDate(String displayDate) {
        mDisplayDate = displayDate;
    }

}
