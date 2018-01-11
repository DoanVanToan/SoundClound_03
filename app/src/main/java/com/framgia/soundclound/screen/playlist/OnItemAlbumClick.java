package com.framgia.soundclound.screen.playlist;

import android.widget.ImageView;

import com.framgia.soundclound.data.model.Album;

/**
 * Created by Bui Danh Nam on 8/1/2018.
 */

public interface OnItemAlbumClick {
    void onItemClick(Album album);

    void onItemMoreClick(ImageView imageView, Album album);
}
