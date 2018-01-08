package com.framgia.soundclound.screen.home;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.framgia.soundclound.BR;
import com.framgia.soundclound.data.model.Genre;
import com.framgia.soundclound.data.source.repository.GenresRepository;

/**
 * Created by Bui Danh Nam on 5/1/2018.
 */

public class HomeViewModel extends BaseObservable implements OnItemClickListener {
    private HomeAdapter mHomeAdapter;

    public HomeViewModel() {
        mHomeAdapter = new HomeAdapter(GenresRepository.getListGenres());
    }

    @Bindable
    public HomeAdapter getHomeAdapter() {
        return mHomeAdapter;
    }

    public void setHomeAdapter(HomeAdapter homeAdapter) {
        mHomeAdapter = homeAdapter;
        notifyPropertyChanged(BR.homeAdapter);
    }

    @Override
    public void onClick(Genre genres) {
        // handle click item
    }
}
