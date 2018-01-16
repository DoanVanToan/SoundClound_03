package com.framgia.soundclound.screen.playlist;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.framgia.soundclound.R;
import com.framgia.soundclound.databinding.FragmentPlaylistBinding;

/**
 * Created by Bui Danh Nam on 8/1/2018.
 */

public class PlaylistFragment extends Fragment {
    public PlaylistFragment() {
    }

    private PlaylistModelView mPlaylistModelView;

    public static PlaylistFragment newInstance() {
        return new PlaylistFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             Bundle savedInstanceState) {
        FragmentPlaylistBinding fragmentPlaylistBinding = DataBindingUtil.inflate(inflater,
                R.layout.fragment_playlist, container, false);
        mPlaylistModelView = new PlaylistModelView(this.getContext());
        fragmentPlaylistBinding.setPlaylistModelview(mPlaylistModelView);
        return fragmentPlaylistBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onResume() {
        super.onResume();
        mPlaylistModelView.updateData();
    }

}
