package com.framgia.soundclound.screen.detailgenre;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.framgia.soundclound.R;
import com.framgia.soundclound.data.model.Track;
import com.framgia.soundclound.databinding.ItemGenreDetailBinding;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sony on 1/5/2018.
 */

public class GenreDetailAdapter extends
        RecyclerView.Adapter<GenreDetailAdapter.GenreDetailViewHolder> {

    private List<Track> mTracks;
    private TrackClickListener mTrackClickListener;
    private MoreInfoClickListener mMoreInfoClickListener;

    public GenreDetailAdapter() {
        mTracks = new ArrayList<>();
    }

    @Override
    public GenreDetailViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        ItemGenreDetailBinding itemGenreDetailBinding = DataBindingUtil.inflate(layoutInflater,
                R.layout.item_genre_detail , parent, false);
        return new GenreDetailViewHolder(itemGenreDetailBinding, mMoreInfoClickListener,
                mTrackClickListener);
    }

    @Override
    public void onBindViewHolder(GenreDetailViewHolder holder, int position) {
        holder.bindData(mTracks.get(position));
    }

    @Override
    public int getItemCount() {
        return mTracks != null ? mTracks.size() : 0;
    }

    public void setMoreInfoClickListener(MoreInfoClickListener moreInfoClickListener) {
        mMoreInfoClickListener = moreInfoClickListener;
    }

    public void setTrackClickListener(TrackClickListener trackClickListener) {
        mTrackClickListener = trackClickListener;
    }

    public List<Track> getData() {
        return mTracks;
    }

    public void addData(List<Track> tracks) {
        if (tracks == null) {
            return;
        }
        mTracks.addAll(tracks);
        notifyDataSetChanged();
    }

    /**
     * Created by Sony on 1/5/2018.
     */
    public class GenreDetailViewHolder extends RecyclerView.ViewHolder {
        private ItemGenreDetailBinding mBinding;
        private MoreInfoClickListener moreInfoClickListener;
        private TrackClickListener trackClickListener;

        public GenreDetailViewHolder(ItemGenreDetailBinding itemGenreDetailBinding,
                                     MoreInfoClickListener moreInfoClickListener,
                                     TrackClickListener trackClickListener) {
            super(itemGenreDetailBinding.getRoot());
            this.mBinding = itemGenreDetailBinding;
            this.moreInfoClickListener = moreInfoClickListener;
            this.trackClickListener = trackClickListener;
        }

        public void bindData(Track track) {
            if (track == null) {
                return;
            }
            mBinding.executePendingBindings();
            mBinding.setViewModel(new ItemGenreDetailViewModel(track, getAdapterPosition(),
                    trackClickListener, moreInfoClickListener));
        }

    }

}
