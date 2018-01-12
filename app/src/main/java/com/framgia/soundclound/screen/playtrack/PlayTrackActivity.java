package com.framgia.soundclound.screen.playtrack;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.framgia.soundclound.R;
import com.framgia.soundclound.data.source.local.SharePreferences;
import com.framgia.soundclound.databinding.ActivityPlayTrackBinding;


/**
 * Created by Sony on 1/11/2018.
 */
public class PlayTrackActivity extends AppCompatActivity {



    public static Intent getInstance(Context context) {
        return new Intent(context, PlayTrackActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityPlayTrackBinding activityPlayTrackBinding = DataBindingUtil.setContentView(
                this , R.layout.activity_play_track);

        PlayTrackViewModel mPlayTrackViewModel = new PlayTrackViewModel
                (SharePreferences.getInstance().getListTrack(),
                SharePreferences.getInstance().getTrack());
        activityPlayTrackBinding.setViewModel(mPlayTrackViewModel);
        getSupportActionBar().hide();
    }

}
