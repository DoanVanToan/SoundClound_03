package com.framgia.soundclound.data.model;

import java.util.List;

/**
 * Created by Bui Danh Nam on 8/1/2018.
 */

public class Album {
    private String mName;
    private int mId;
    private int mNumberSong;
    private String mImage;
    private List<Track> mTracks;

    public Album() {
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public int getId() {
        return mId;
    }

    public void setId(int id) {
        mId = id;
    }

    public int getNumberSong() {
        return mNumberSong;
    }

    public void setNumberSong(int numberSong) {
        mNumberSong = numberSong;
    }

    public String getImage() {
        return mImage;
    }

    public void setImage(String image) {
        mImage = image;
    }

    public List<Track> getTracks() {
        return mTracks;
    }

    public void setTracks(List<Track> tracks) {
        mTracks = tracks;
    }

    public static class AlbumEntity {
        public static final String NAME_ALBUM = "name";
        public static final String ID_ALBUM = "nam_key";
        public static final String NUMBER_SONG = "number_song";
        public static final String LIST_TRACK = "list_track";
        public static final String IMAGE_ALBUM = "image";
    }
}
