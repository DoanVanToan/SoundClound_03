package com.framgia.soundclound.data.model;

/**
 * Created by Bui Danh Nam on 8/1/2018.
 */

public class Album {
    private String mName;
    private String mKeyName;
    private int mNumbMusic;
    private String mImage;

    public Album() {
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public String getKeyName() {
        return mKeyName;
    }

    public void setKeyName(String keyName) {
        mKeyName = keyName;
    }

    public int getNumbMusic() {
        return mNumbMusic;
    }

    public void setNumbMusic(int numbMusic) {
        mNumbMusic = numbMusic;
    }

    public String getImage() {
        return mImage;
    }

    public void setImage(String image) {
        this.mImage = image;
    }
}
