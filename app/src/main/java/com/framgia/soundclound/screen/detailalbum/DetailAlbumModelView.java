package com.framgia.soundclound.screen.detailalbum;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.widget.ImageView;

import com.framgia.soundclound.R;
import com.framgia.soundclound.data.model.Track;
import com.framgia.soundclound.data.source.repository.AlbumRepository;
import com.framgia.soundclound.screen.BaseOnItemClick;
import com.framgia.soundclound.screen.addtracktoalbum.AddTrackActivity;
import com.framgia.soundclound.screen.playtrack.PlayTrackActivity;

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
        mTracks = AlbumRepository.getInstance(mContext).getAllTrack(idAlbum);
        mDetailAlbumAdapter = new DetailAlbumAdapter(mTracks);
        mIdAlbum = idAlbum;
        mDetailAlbumAdapter.setOnItemClick(this);
    }

    @Bindable
    public DetailAlbumAdapter getDetailAlbumAdapter() {
        return mDetailAlbumAdapter;
    }

    public void setDetailAlbumAdapter(DetailAlbumAdapter detailAlbumAdapter) {
        mDetailAlbumAdapter = detailAlbumAdapter;
        notifyPropertyChanged(0);
    }

    @Override
    public void onItemClick(Track track, int pos) {
        // // TODO: 17/1/2018 save data SharePreferences
        mContext.startActivity(PlayTrackActivity.getInstance(mContext));
    }

    public void onFabAddAlbumClick() {
        mContext.startActivity(AddTrackActivity.getInstance(mContext, mIdAlbum));
    }

    @Override
    public void onIconMoreClick(ImageView imageView, final Track track) {
    }

    private void handleAddFavorite(Track track, AlertDialog.Builder builder) {
    }

    private void handleRemoveTrack(final int idAlbum, final Track track,
                                   AlertDialog.Builder builder) {
        builder.setTitle(R.string.msg_remove_track_album);
        builder.setPositiveButton(R.string.action_ok,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        boolean resultRemoveTrack = AlbumRepository.getInstance(mContext)
                                .removeTrack(idAlbum, track);
                        if (resultRemoveTrack) {
                            mDetailAlbumAdapter.updateData(
                                    AlbumRepository.getInstance(mContext).getAllTrack(idAlbum));
                        }
                    }
                });
        builder.setNegativeButton(R.string.action_cancel, null);
    }
}
