package com.framgia.soundclound.screen.playlist;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.framgia.soundclound.R;
import com.framgia.soundclound.data.model.Album;
import com.framgia.soundclound.databinding.ItemAlbumBinding;

import java.util.List;

/**
 * Created by Bui Danh Nam on 8/1/2018.
 */

public class PlaylistAdapter extends RecyclerView.Adapter<PlaylistAdapter.ViewHolder> {
    private List<Album> mAlbums;
    private OnItemAlbumClick mOnItemAlbumClick;

    public PlaylistAdapter(List<Album> albums) {
        mAlbums = albums;
    }

    public List<Album> getAlbums() {
        return mAlbums;
    }

    public void updateAlbums(List<Album> albums) {
        if (albums == null) {
            return;
        }
        mAlbums = albums;
        notifyDataSetChanged();
    }

    public void setOnItemAlbumClick(OnItemAlbumClick onItemAlbumClick) {
        mOnItemAlbumClick = onItemAlbumClick;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ItemAlbumBinding itemAlbumBinding = DataBindingUtil.inflate(
                LayoutInflater.from(
                        parent.getContext()), R.layout.item_album, parent, false);

        return new ViewHolder(itemAlbumBinding);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.bindData(mAlbums.get(position));
    }

    @Override
    public int getItemCount() {
        return mAlbums != null ? mAlbums.size() : 0;
    }

    /**
     * Created by Bui Danh Nam on 8/1/2018.
     */

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ItemAlbumBinding mItemAlbumBinding;

        public ViewHolder(ItemAlbumBinding itemAlbumBinding) {
            super(itemAlbumBinding.getRoot());
            mItemAlbumBinding = itemAlbumBinding;
        }

        public void bindData(Album album) {
            mItemAlbumBinding.setAlbum(album);
            mItemAlbumBinding.setListener(mOnItemAlbumClick);
            mItemAlbumBinding.executePendingBindings();
        }
    }

}
