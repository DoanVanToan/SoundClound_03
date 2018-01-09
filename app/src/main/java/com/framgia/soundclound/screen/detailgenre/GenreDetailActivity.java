package com.framgia.soundclound.screen.detailgenre;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.framgia.soundclound.R;
import com.framgia.soundclound.databinding.ActivityGenreDetailBinding;
import com.framgia.soundclound.util.Constant;

/**
 * Created by Sony on 1/5/2018.
 */

public class GenreDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActivityGenreDetailBinding activityGenreDetailBinding = DataBindingUtil
                .setContentView(this, R.layout.activity_genre_detail);
        GenreDetailViewModel mViewModel = new GenreDetailViewModel(this, getIntent().getExtras()
                .getString(Constant.EXTRA_GENRE));
        activityGenreDetailBinding.setViewModel(mViewModel);
    }
}
