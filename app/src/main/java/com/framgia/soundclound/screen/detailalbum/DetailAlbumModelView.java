package com.framgia.soundclound.screen.detailalbum;

import android.content.Context;
import android.databinding.BaseObservable;
import android.widget.ImageView;

import com.framgia.soundclound.data.model.Track;
import com.framgia.soundclound.data.source.repository.AlbumRepository;
import com.framgia.soundclound.screen.BaseOnItemClick;

import java.util.List;

/**
 * Created by Bui Danh Nam on 11/1/2018.
 */

public class DetailAlbumModelView extends BaseObservable implements BaseOnItemClick<Track> {
    private DetailAlbumAdapter mDetailAlbumAdapter;
    private Context mContext;
    private List<Track> mTracks;
    private int mIdAlbum;

    public DetailAlbumModelView(Context context, int idAlbum) {
        mContext = context;
        mDetailAlbumAdapter = new DetailAlbumAdapter();
        mIdAlbum = idAlbum;
        onLoadData();
    }

    private void onLoadData() {
        mTracks = AlbumRepository.getInstance(mContext).getAllTrack(mIdAlbum);
        mDetailAlbumAdapter.addData(mTracks);
    }

    @Override
    public void onItemClick(Track track) {
        // TODO: 11/1/2018 handle start activity play track
    }

    @Override
    public void onIconMoreClick(ImageView imageView, Track track) {
        // TODO: 11/1/2018 handle onclick
    }
}
