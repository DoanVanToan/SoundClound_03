package com.framgia.soundclound.screen.playtrack;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.framgia.soundclound.R;
import com.framgia.soundclound.data.model.Track;
import com.framgia.soundclound.data.source.local.SharePreferences;
import com.framgia.soundclound.databinding.ActivityPlayTrackBinding;
import com.framgia.soundclound.service.MusicService;
import com.framgia.soundclound.util.Constant;

import java.util.List;

/**
 * Created by Sony on 1/11/2018.
 */

public class PlayTrackActivity extends AppCompatActivity implements OnClickTrackListener {
    private PlayTrackViewModel mPlayTrackViewModel;
    private List<Track> mTracks;
    private int mIndexCurrent;
    private Broacast mBroacast;

    public static Intent getInstance(Context context) {
        return new Intent(context, PlayTrackActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityPlayTrackBinding activityPlayTrackBinding =
                DataBindingUtil.setContentView(this, R.layout.activity_play_track);
        getlistTrack();
        mPlayTrackViewModel = new PlayTrackViewModel(mTracks, mIndexCurrent);
        mPlayTrackViewModel.setOnClickTrackListener(this);
        activityPlayTrackBinding.setViewModel(mPlayTrackViewModel);
        getSupportActionBar().hide();
        IntentFilter filter = new IntentFilter(Constant.ACTION_NOTIFI_CHANGE);
        mBroacast = new Broacast();
        LocalBroadcastManager.getInstance(this).registerReceiver(
                mBroacast, filter);
    }

    @Override
    protected void onStart() {
        super.onStart();
        bindService(mPlayTrackViewModel.getServiceConnection());
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    public void bindService(ServiceConnection serviceConnection) {
        Intent playIntent = new Intent(this, MusicService.class);
        playIntent.setAction(Constant.ACTION_MAIN);
        bindService(playIntent, serviceConnection, Context.BIND_AUTO_CREATE);
        startService(playIntent);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mPlayTrackViewModel.isMusicBound()) {
            unbindService(mPlayTrackViewModel.getServiceConnection());
        }
        mPlayTrackViewModel.setRuning(false);
        if (mBroacast != null) {
            LocalBroadcastManager.getInstance(this).unregisterReceiver(mBroacast);
        }
    }

    public void getlistTrack() {
        mTracks = SharePreferences.getInstance().getListTrack();
        mIndexCurrent = SharePreferences.getInstance().getIndex();
    }

    @Override
    public void onClickBack(View view) {
        this.finish();
    }

    /**
     * broadcast
     */

    public class Broacast extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            mPlayTrackViewModel.updateUI();
        }
    }

}
