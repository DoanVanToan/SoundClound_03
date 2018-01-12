package com.framgia.soundclound.screen.moretrack;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.design.widget.BottomSheetDialogFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.framgia.soundclound.R;
import com.framgia.soundclound.data.model.Track;
import com.framgia.soundclound.databinding.FragmentMoreTrackBinding;
import com.framgia.soundclound.util.Constant;

/**
 * A simple {@link Fragment} subclass.
 */

public class MoreTrackFragment extends BottomSheetDialogFragment {

    public static MoreTrackFragment newInstance(Track track) {
        MoreTrackFragment fragment = new MoreTrackFragment();
        Bundle args = new Bundle();
        args.putParcelable(Constant.ARGUMENT_TRACK, track);
        fragment.setArguments(args);
        return fragment;
    }

    public MoreTrackFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        FragmentMoreTrackBinding moreTrackBinding = DataBindingUtil.inflate(inflater,
                R.layout.fragment_more_track, container, false);
        moreTrackBinding.setViewModel(new MoreTrackViewModel(getContext(),
                (Track) getArguments().getParcelable(Constant.ARGUMENT_TRACK)));
        return moreTrackBinding.getRoot();
    }

}
