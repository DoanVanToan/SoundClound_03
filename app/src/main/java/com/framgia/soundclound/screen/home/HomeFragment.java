package com.framgia.soundclound.screen.home;


import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.framgia.soundclound.R;
import com.framgia.soundclound.databinding.FragmentHomeBinding;

/**
 * Created by Bui Danh Nam on 5/1/2018.
 */

public class HomeFragment extends Fragment {

    public HomeFragment() {
    }

    public static HomeFragment newInstance() {
        return new HomeFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             Bundle savedInstanceState) {
        FragmentHomeBinding fragmentHomeBinding = DataBindingUtil.inflate(inflater,
                R.layout.fragment_home, container, false);
        HomeViewModel homeViewModel = new HomeViewModel(getContext());
        fragmentHomeBinding.setViewmodelHome(homeViewModel);
        return fragmentHomeBinding.getRoot();
    }
}
