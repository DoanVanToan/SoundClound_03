package com.framgia.soundclound.screen.playlist;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.Toast;

import com.framgia.soundclound.BR;
import com.framgia.soundclound.R;
import com.framgia.soundclound.data.model.Album;
import com.framgia.soundclound.data.source.repository.AlbumRepository;
import com.framgia.soundclound.screen.addtracktoalbum.AddTrackActivity;
import com.framgia.soundclound.screen.detailalbum.DetailAlbumActivity;

import java.util.List;

/**
 * Created by Bui Danh Nam on 8/1/2018.
 */

public class PlaylistModelView extends BaseObservable implements OnItemAlbumClick {
    private PlaylistAdapter mPlaylistAdapter;
    private Context mContext;
    private List<Album> mAlbums;

    public PlaylistModelView(Context context) {
        mContext = context;
        mPlaylistAdapter = new PlaylistAdapter(mAlbums);
        mPlaylistAdapter.setOnItemAlbumClick(this);
    }

    @Bindable
    public PlaylistAdapter getPlaylistAdapter() {
        return mPlaylistAdapter;
    }

    public void setPlaylistAdapter(PlaylistAdapter playlistAdapter) {
        mPlaylistAdapter = playlistAdapter;
        notifyPropertyChanged(BR.playlistAdapter);
    }

    public void onFabAddAlbumClick() {
        showDialogAddAlbum();
    }

    private void showDialogAddAlbum() {
        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
        builder.setTitle(R.string.title_add_album);
        builder.setMessage(R.string.msg_name_album);
        final EditText input = new EditText(builder.getContext());
        builder.setView(input);
        builder.setPositiveButton(R.string.action_add, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String nameAlbum = input.getText().toString().trim();
                if (nameAlbum.isEmpty()) {
                    Toast.makeText(
                            mContext, R.string.msg_err_name_null, Toast.LENGTH_SHORT).show();
                    dialog.cancel();
                }
                Album albumTemp = new Album();
                albumTemp.setName(nameAlbum);
                boolean result = AlbumRepository.getInstance(mContext).addAlbum(albumTemp);
                if (result) {
                    updateUI();
                    int id = AlbumRepository.getInstance(mContext).getAlbumByName(
                            albumTemp.getName()).getId();
                    mContext.startActivity(AddTrackActivity.getInstance(mContext, id));
                } else {
                    Toast.makeText(
                            mContext, R.string.msg_err_name_exist, Toast.LENGTH_SHORT).show();
                }
            }
        });
        builder.setNegativeButton(R.string.action_cancel, null);
        builder.show();


    }

    private void updateUI() {
        mPlaylistAdapter.updateAlbums(
                AlbumRepository.getInstance(mContext).getAllAlbum());
    }

    @Override
    public void onItemClick(Album album) {
        if (album == null) {
            return;
        }
        mContext.startActivity(DetailAlbumActivity.getInstance(mContext, album.getId()));
    }

    @Override
    public void onItemMoreClick(ImageView imageView, final Album album) {
        if (album == null) {
            return;
        }
        final AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
        PopupMenu popupMenu = new PopupMenu(mContext, imageView);
        popupMenu.getMenuInflater().inflate(R.menu.popup_menu_album, popupMenu.getMenu());
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.delete_album:
                        handleDeleteAlbum(album, builder);
                        break;
                    case R.id.rename_album:
                        handleRenameAlbum(album, builder);
                        break;
                }
                builder.show();
                return true;
            }
        });
        popupMenu.show();
    }

    private void handleRenameAlbum(final Album album, AlertDialog.Builder builder) {
        builder.setTitle(R.string.msg_rename_album);
        builder.setMessage(R.string.msg_name_album);
        final EditText inputNameAlbum = new EditText(builder.getContext());
        builder.setView(inputNameAlbum);
        inputNameAlbum.setText(album.getName());
        builder.setPositiveButton(
                R.string.action_ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String nameAlbum = inputNameAlbum.getText().toString().trim();
                        if (nameAlbum.isEmpty()) {
                            Toast.makeText(mContext,
                                    R.string.msg_err_name_null,
                                    Toast.LENGTH_SHORT).show();
                            dialog.cancel();
                        }
                        if (nameAlbum.equals(album.getName())) {
                            dialog.cancel();
                        }
                        album.setName(nameAlbum);
                        boolean resultRename = AlbumRepository
                                .getInstance(mContext).renameAlbum(album);
                        if (resultRename) {
                            updateUI();
                        }
                    }
                });
        builder.setNegativeButton(R.string.action_cancel, null);
    }

    private void handleDeleteAlbum(final Album album, AlertDialog.Builder builder) {
        builder.setTitle(R.string.msg_delele_album);
        builder.setPositiveButton(R.string.action_ok,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        boolean resultDelAlbum = AlbumRepository.getInstance(mContext)
                                .deleteAlbum(album);
                        if (resultDelAlbum) {
                            updateData();
                        }
                    }
                });
        builder.setNegativeButton(R.string.action_cancel, null);
    }

    public void updateData() {
        mPlaylistAdapter.updateAlbums(
                AlbumRepository.getInstance(mContext).getAllAlbum());
    }
}
