package com.framgia.soundclound.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Sony on 1/5/2018.
 */

public class CollectionResult {

    @SerializedName("genre")
    @Expose
    private String mGenre;
    @SerializedName("kind")
    @Expose
    private String mKind;
    @SerializedName("last_updated")
    @Expose
    private String mLastUpdated;
    @SerializedName("collection")
    @Expose
    private List<Collection> mCollection;
    @SerializedName("query_urn")
    @Expose
    private String mQueryUrn;
    @SerializedName("next_href")
    @Expose
    private String mNextHref;

    public String getGenre() {
        return mGenre;
    }

    public void setGenre(String genre) {
        mGenre = genre;
    }

    public String getKind() {
        return mKind;
    }

    public void setKind(String kind) {
        mKind = kind;
    }

    public String getLastUpdated() {
        return mLastUpdated;
    }

    public void setLastUpdated(String lastUpdated) {
        mLastUpdated = lastUpdated;
    }

    public List<Collection> getCollection() {
        return mCollection;
    }

    public void setCollection(List<Collection> collection) {
        mCollection = collection;
    }

    public String getQueryUrn() {
        return mQueryUrn;
    }

    public void setQueryUrn(String queryUrn) {
        mQueryUrn = queryUrn;
    }

    public String getNextHref() {
        return mNextHref;
    }

    public void setNextHref(String nextHref) {
        mNextHref = nextHref;
    }
}
