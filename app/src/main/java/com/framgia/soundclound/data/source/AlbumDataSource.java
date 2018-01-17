package com.framgia.soundclound.data.source;

import com.framgia.soundclound.data.model.Album;
import com.framgia.soundclound.data.model.Track;

import java.util.List;

/**
 * Created by Bui Danh Nam on 8/1/2018.
 */

public interface AlbumDataSource {
    List<Album> getAllAlbum();

    boolean addAlbum(Album album);

    boolean deleteAlbum(Album album);

    Album getAlbumById(int idAlbum);

    boolean updateAlbum(Album album);

    Album getAlbumByName(String nameAlbum);

    boolean addTrack(int idAlbum, Track track);

    List<Track> getAllTrack(int idAlbum);

    boolean removeTrack(int idAlbum, Track track);

    boolean renameAlbum(Album album);
}
