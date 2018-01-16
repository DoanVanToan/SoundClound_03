package com.framgia.soundclound.data.source.repository;

import android.content.Context;

import com.framgia.soundclound.data.model.Album;
import com.framgia.soundclound.data.model.Track;
import com.framgia.soundclound.data.source.AlbumDataSource;
import com.framgia.soundclound.data.source.local.sqlite.AlbumLocalDataSource;

import java.util.List;

/**
 * Created by Bui Danh Nam on 8/1/2018.
 */

public class AlbumRepository implements AlbumDataSource {
    private AlbumDataSource mLocalDataSource;

    public static AlbumRepository getInstance(Context context) {
        return new AlbumRepository(AlbumLocalDataSource.getInstance(context));
    }

    public AlbumRepository(AlbumDataSource localDataSource) {
        mLocalDataSource = localDataSource;
    }

    @Override
    public List<Album> getAllAlbum() {
        return mLocalDataSource.getAllAlbum();
    }

    @Override
    public boolean addAlbum(Album album) {
        return mLocalDataSource.addAlbum(album);
    }

    @Override
    public boolean deleteAlbum(Album album) {
        return mLocalDataSource.deleteAlbum(album);
    }

    @Override
    public Album getAlbumById(int idAlbum) {
        return mLocalDataSource.getAlbumById(idAlbum);
    }

    @Override
    public boolean updateAlbum(Album album) {
        return mLocalDataSource.updateAlbum(album);
    }

    @Override
    public Album getAlbumByName(String nameAlbum) {
        return mLocalDataSource.getAlbumByName(nameAlbum);
    }

    @Override
    public boolean addTrack(int idAlbum, Track track) {
        return mLocalDataSource.addTrack(idAlbum, track);
    }

    @Override
    public List<Track> getAllTrack(int idAlbum) {
        return mLocalDataSource.getAllTrack(idAlbum);
    }

    @Override
    public boolean removeTrack(int idAlbum, Track track) {
        return mLocalDataSource.removeTrack(idAlbum, track);
    }


    @Override
    public boolean renameAlbum(Album album) {
        return mLocalDataSource.renameAlbum(album);
    }
}
