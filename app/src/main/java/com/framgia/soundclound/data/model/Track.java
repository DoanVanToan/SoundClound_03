package com.framgia.soundclound.data.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Sony on 1/5/2018.
 */

public class Track implements Parcelable {
    @SerializedName("artwork_url")
    @Expose
    private String mArtworkUrl;
    @SerializedName("description")
    @Expose
    private String mDescription;
    @SerializedName("downloadable")
    @Expose
    private boolean mDownloadable;
    @SerializedName("download_count")
    @Expose
    private int mDownloadCount;
    @SerializedName("download_url")
    @Expose
    private String mDowloadUrl;
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

    private boolean mChecked;

    public Track(String title) {
        mTitle = title;
    }

    public Track() {
    }

    protected Track(Parcel in) {
        mArtworkUrl = in.readString();
        mDescription = in.readString();
        mDownloadCount = in.readInt();
        mDowloadUrl = in.readString();
        mFullDuration = in.readInt();
        mGenre = in.readString();
        mId = in.readInt();
        mLikesCount = in.readInt();
        mPermalinkUrl = in.readString();
        mTitle = in.readString();
        mUri = in.readString();
        mUserId = in.readInt();
        mDisplayDate = in.readString();
    }
    public boolean isChecked() {
        return mChecked;
    }

    public void setChecked(boolean checked) {
        mChecked = checked;
    }

    public static final Creator<Track> CREATOR = new Creator<Track>() {
        @Override
        public Track createFromParcel(Parcel in) {
            return new Track(in);
        }

        @Override
        public Track[] newArray(int size) {
            return new Track[size];
        }
    };

    public String getArtworkUrl() {
        return mArtworkUrl;
    }

    public void setArtworkUrl(String artworkUrl) {
        mArtworkUrl = artworkUrl;
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

    public String getDowloadUrl() {
        return mDowloadUrl;
    }

    public void setDowloadUrl(String dowloadUrl) {
        mDowloadUrl = dowloadUrl;
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

    public boolean isDownloadable() {
        return mDownloadable;
    }

    public void setDownloadable(boolean downloadable) {
        mDownloadable = downloadable;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Track track = (Track) o;

        return mId == track.mId;
    }

    @Override
    public int hashCode() {
        return mId;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(mArtworkUrl);
        parcel.writeString(mDescription);
        parcel.writeInt(mDownloadCount);
        parcel.writeString(mDowloadUrl);
        parcel.writeInt(mFullDuration);
        parcel.writeString(mGenre);
        parcel.writeInt(mId);
        parcel.writeInt(mLikesCount);
        parcel.writeString(mPermalinkUrl);
        parcel.writeString(mTitle);
        parcel.writeString(mUri);
        parcel.writeInt(mUserId);
        parcel.writeString(mDisplayDate);

    }

}
