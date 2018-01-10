package com.framgia.soundclound.screen.home;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.framgia.soundclound.R;
import com.framgia.soundclound.data.model.Genre;
import com.framgia.soundclound.databinding.ItemHomeGenresBinding;

import java.util.List;

/**
 * Created by Bui Danh Nam on 5/1/2018.
 */

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.ViewHolder> {
    private List<Genre> mGenres;
    private OnItemClickListener mOnItemClickListener;

    public HomeAdapter(List<Genre> genres) {
        mGenres = genres;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ItemHomeGenresBinding itemHomeGenresBinding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.getContext()),
                R.layout.item_home_genres, parent, false);
        return new ViewHolder(itemHomeGenresBinding);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.bindingdata(mGenres.get(position));
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        mOnItemClickListener = onItemClickListener;
    }

    @Override
    public int getItemCount() {
        return mGenres != null ? mGenres.size() : 0;
    }

    /**
     * Created by Bui Danh Nam on 5/1/2018.
     */

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ItemHomeGenresBinding mBinding;

        public ViewHolder(ItemHomeGenresBinding itemHomeGenresBinding) {
            super(itemHomeGenresBinding.getRoot());
            mBinding = itemHomeGenresBinding;
        }

        public void bindingdata(Genre genres) {
            if (genres != null) {
                mBinding.setGenre(genres);
                mBinding.setListener(mOnItemClickListener);
                mBinding.executePendingBindings();
            }
        }
    }
}
