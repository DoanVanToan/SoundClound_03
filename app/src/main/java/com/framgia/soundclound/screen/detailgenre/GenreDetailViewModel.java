package com.framgia.soundclound.screen.detailgenre;

import android.content.Context;
import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.widget.Toast;

import com.framgia.soundclound.BR;
import com.framgia.soundclound.data.model.Track;
import com.framgia.soundclound.data.source.TrackDataSource;
import com.framgia.soundclound.data.source.TrackRepository;
import com.framgia.soundclound.data.source.remote.TrackRemoteDataSource;
import com.framgia.soundclound.util.Constant;

import java.util.List;

/**
 * Created by Sony on 1/5/2018.
 */

public class GenreDetailViewModel extends BaseObservable implements TrackClickListener,
        MoreInfoClickListener {

    private GenreDetailAdapter mGenreDetailAdapter;
    private String mGenre;
    private Context mContext;
    private TrackRepository mTrackRepository;

    public GenreDetailViewModel(Context context, String genre) {
        mContext = context;
        mGenre = genre;
        mTrackRepository = new TrackRepository(TrackRemoteDataSource.getInstance());
        mGenreDetailAdapter = new GenreDetailAdapter();
        mGenreDetailAdapter.setTrackClickListener(this);
        mGenreDetailAdapter.setMoreInfoClickListener(this);
        getData();
    }

    private void getData() {
        mTrackRepository.getListTrack(Constant.BASE_URL + Constant.PARA,
                mGenre, Constant.LIMIT_DEFAULT, Constant.OFF_SET_DEFAULT,
                new TrackDataSource.Callback<List<Track>>() {
                    @Override
                    public void onStartLoading() {

                    }

                    @Override
                    public void onGetSuccess(List<Track> data) {
                        mGenreDetailAdapter.addData(data);
                    }

                    @Override
                    public void onGetFailure(String message) {
                        Toast.makeText(mContext , Constant.ERROR_TEXT, Toast.LENGTH_SHORT).show();

                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }

    @Bindable
    public GenreDetailAdapter getGenreDetailAdapter() {
        return mGenreDetailAdapter;
    }

    public void setmGenreDetailAdapter(GenreDetailAdapter genreDetailAdapter) {
        this.mGenreDetailAdapter = genreDetailAdapter;
        notifyPropertyChanged(BR.genreDetailAdapter);
    }

    @Override
    public void onItemTrackClick(Track track) {
        // TODO: 1/10/2018 open playtrackactivity
    }

    @Override
    public void onClickMore(Track track) {
        // TODO: 1/10/2018  open dialog
    }
}
