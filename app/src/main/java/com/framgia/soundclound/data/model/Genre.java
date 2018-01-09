package com.framgia.soundclound.data.model;


import java.io.Serializable;

/**
 * Created by Bui Danh Nam on 5/1/2018.
 */

public class Genre implements Serializable {
    private int mName;
    private int mImage;
    private String mKeyname;
    private int mIcon;

    public Genre(int name, int image, String keyname, int icon) {
        mName = name;
        mImage = image;
        mKeyname = keyname;
        mIcon = icon;
    }

    public int getName() {
        return mName;
    }

    public void setName(int name) {
        mName = name;
    }

    public int getImage() {
        return mImage;
    }

    public void setImage(int image) {
        mImage = image;
    }

    public String getKeyname() {
        return mKeyname;
    }

    public void setKeyname(String keyname) {
        mKeyname = keyname;
    }

    public int getIcon() {
        return mIcon;
    }

    public void setIcon(int icon) {
        mIcon = icon;
    }
}
