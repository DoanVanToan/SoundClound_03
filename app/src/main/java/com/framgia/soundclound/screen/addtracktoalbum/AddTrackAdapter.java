package com.framgia.soundclound.screen.addtracktoalbum;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.framgia.soundclound.R;
import com.framgia.soundclound.data.model.Track;
import com.framgia.soundclound.databinding.ItemAddTrackBinding;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ADMIN on 1/14/2018.
 */

public class AddTrackAdapter extends RecyclerView.Adapter<AddTrackAdapter.AddTrackViewHolder> {

    private List<Track> mTracks;
    private CheckCliclListener mCheckCliclListener;
    private boolean mIsChecked;

    public AddTrackAdapter() {
        mTracks = new ArrayList<>();
    }

    @Override
    public AddTrackViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        ItemAddTrackBinding itemAddTrackBinding = DataBindingUtil.inflate(layoutInflater,
                R.layout.item_add_track , parent, false);
        return new AddTrackViewHolder(itemAddTrackBinding, mCheckCliclListener);
    }

    @Override
    public void onBindViewHolder(AddTrackViewHolder holder, int position) {
        holder.bindData(mTracks.get(position));
    }

    @Override
    public int getItemCount() {
        return mTracks != null ? mTracks.size() : 0;
    }

    public void setCheckClickListener(CheckCliclListener checkClickListener) {
        mCheckCliclListener = checkClickListener;
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
     * Created by Sony on 1/13/2018.
     */
    public class AddTrackViewHolder extends RecyclerView.ViewHolder {
        private ItemAddTrackBinding mBinding;
        private CheckCliclListener mCheckCliclListener;

        public AddTrackViewHolder(ItemAddTrackBinding itemAddTrackBinding,
                                     CheckCliclListener checkCliclListener) {
            super(itemAddTrackBinding.getRoot());
            mBinding = itemAddTrackBinding;
            mCheckCliclListener = checkCliclListener;
        }

        public void bindData(Track track) {
            if (track == null) {
                return;
            }
            mBinding.executePendingBindings();
            mBinding.setViewModel(new ItemAddTrackViewModel(track, mCheckCliclListener,
                    getAdapterPosition(), false));
        }

    }
}
