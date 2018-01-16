package com.framgia.soundclound.service;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.IBinder;
import android.os.PowerManager;
import android.support.v4.app.NotificationCompat;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;
import android.widget.RemoteViews;

import com.framgia.soundclound.BaseMediaPlayer;
import com.framgia.soundclound.R;
import com.framgia.soundclound.data.model.Track;
import com.framgia.soundclound.data.source.local.SharePreferences;
import com.framgia.soundclound.screen.playtrack.PlayTrackActivity;
import com.framgia.soundclound.util.Constant;
import com.framgia.soundclound.util.StringUtil;

import java.util.List;
import java.util.Random;
import java.util.logging.Logger;

/**
 * service music
 */

public class MusicService extends Service implements BaseMediaPlayer {

    private final IBinder mMusicBind = new MusicBinder();
    private MediaPlayer mPlayer;
    private List<Track> mTracks;
    private int mCurentTrack;
    private boolean mShuffle;
    private boolean mRepeat;
    private Random mRandom;
    private String mTitleTrack;
    private String mArtist;
    private int mDuration;


    @Override
    public void onCreate() {
        super.onCreate();
        mCurentTrack = SharePreferences.getInstance().getIndex();
        mTracks = SharePreferences.getInstance().getListTrack();
        mRandom = new Random();
        mPlayer = new MediaPlayer();
        initMusicPlayer();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if (intent != null) {
            String action = intent.getAction();
            if (action == null) {
                return START_NOT_STICKY;
            }
            switch (action) {
                case Constant.ACTION_PLAY:
                    if (isPlay()) {
                        pause();
                        showNotifi();
                    } else {
                        start();
                        showNotifi();
                    }
                    break;
                case Constant.ACTION_NEXT:
                    next();
                    showNotifi();
                    break;
                case Constant.ACTION_PREV:
                    prev();
                    showNotifi();
                    break;
                case Constant.ACTION_DISMIS:
                    stopForeground(true);
                    stopSelf();
                    break;
                default:
                    break;
            }

        }
        return START_STICKY;
    }

    final MediaPlayer.OnErrorListener mErrorListener = new MediaPlayer.OnErrorListener() {
        @Override
        public boolean onError(MediaPlayer mp, int what, int extra) {
            return true;
        }
    };
    final MediaPlayer.OnCompletionListener mCompletion = new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mp) {
            if (mRepeat) {
                play();
            } else if (mShuffle) {
                mCurentTrack = mRandom.nextInt((mTracks.size() - 1));
                SharePreferences.getInstance().putIndex(mCurentTrack);
                play();
            } else {
                next();
            }
        }
    };
    final MediaPlayer.OnPreparedListener mOnPrepared = new MediaPlayer.OnPreparedListener() {
        @Override
        public void onPrepared(MediaPlayer mp) {
            mp.start();
            mDuration = mPlayer.getDuration();
            showNotifi();
        }
    };

    final void sendNotifiChange() {
        Intent intent = new Intent(Constant.ACTION_NOTIFI_CHANGE);
        LocalBroadcastManager.getInstance(this).sendBroadcast(intent);
    }

    public void initMusicPlayer() {
        mPlayer.setWakeMode(getApplicationContext(),
                PowerManager.PARTIAL_WAKE_LOCK);
        mPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
        mPlayer.setOnPreparedListener(mOnPrepared);
        mPlayer.setOnCompletionListener(mCompletion);
        mPlayer.setOnErrorListener(mErrorListener);
    }

    @Override
    public IBinder onBind(Intent intent) {
        return mMusicBind;
    }

    public int getPosition() {
        try {
            return mPlayer.getCurrentPosition();
        } catch (Exception position) {
            Log.i("", "getPosition: " + position.getMessage());
            return 0;
        }
    }

    public boolean isShuffle() {
        return mShuffle;
    }

    public boolean isRepeat() {
        return mRepeat;
    }

    @Override
    public boolean isPlay() {
        return mPlayer.isPlaying();
    }

    @Override
    public void play() {
        mCurentTrack = SharePreferences.getInstance().getIndex();
        mTracks = SharePreferences.getInstance().getListTrack();
        Track track = mTracks.get(mCurentTrack);
        if (track.getPublisherMetadata() != null) {
            mArtist = track.getPublisherMetadata().getArtist();
        }
        mTitleTrack = track.getTitle();
        String uri = null;
        if (track.getId() == 0) {
            uri = track.getUri();
        } else {
            uri = StringUtil.getUrlDownload(track.getUri());
        }
        try {
            mPlayer.reset();
            mPlayer.setDataSource(uri);
            mPlayer.prepareAsync();
        } catch (Exception e) {
            Logger.getLogger(e.toString());
        }
    }

    public boolean checkTrackPlay(Track track) {
        Track trackCurent = mTracks.get(mCurentTrack);
        return track.getUri().equals(trackCurent.getUri());
    }

    @Override
    public void start() {
        mPlayer.start();
    }

    @Override
    public void pause() {
        if (mPlayer.isPlaying()) {
            mPlayer.pause();
        }
    }

    @Override
    public void stop() {
        if (mPlayer != null) {
            mPlayer.stop();
        }
    }

    @Override
    public void release() {
        mPlayer.release();
    }

    @Override
    public void seekto(int position) {
        if (mPlayer != null) {
            mPlayer.seekTo(position);
        }
    }

    @Override
    public void next() {
        mCurentTrack = SharePreferences.getInstance().getIndex();
        mCurentTrack++;
        if (mCurentTrack > (mTracks.size() - 1)) {
            mCurentTrack = 0;
            SharePreferences.getInstance().putIndex(mCurentTrack);
            play();
        } else {
            SharePreferences.getInstance().putIndex(mCurentTrack);
            play();
        }
    }

    @Override
    public void prev() {
        mCurentTrack = SharePreferences.getInstance().getIndex();
        mCurentTrack--;
        if (mCurentTrack < 0) {
            mCurentTrack = mTracks.size() - 1;
            SharePreferences.getInstance().putIndex(mCurentTrack);
            play();
        } else {
            SharePreferences.getInstance().putIndex(mCurentTrack);
            play();
        }
        SharePreferences.getInstance().putIndex(mCurentTrack);
    }

    @Override
    public void onDestroy() {
        release();
        stopSelf();
        super.onDestroy();
    }

    public int getDuration() {
        return mDuration;
    }

    public void changeSuffleSetting() {
        mShuffle = !mShuffle;
    }

    public void changeRepeatSetting() {
        mRepeat = !mRepeat;
    }

    public void showNotifi() {
        Intent notIntent = new Intent(this, PlayTrackActivity.class);
        notIntent.setAction(Constant.ACTION_MAIN);
        notIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        PendingIntent pendInt = PendingIntent.getActivity(getApplicationContext(),
                0,
                notIntent, 0);

        Intent previousIntent = new Intent(getApplicationContext(), MusicService.class);
        previousIntent.setAction(Constant.ACTION_PREV);
        PendingIntent ppreviousIntent = PendingIntent.getService(getApplicationContext(),
                0,
                previousIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        Intent playIntent = new Intent(getApplicationContext(), MusicService.class);
        playIntent.setAction(Constant.ACTION_PLAY);
        PendingIntent pplayIntent = PendingIntent.getService(getApplicationContext(),
                0,
                playIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        Intent nextIntent = new Intent(getApplicationContext(), MusicService.class);
        nextIntent.setAction(Constant.ACTION_NEXT);
        PendingIntent pnextIntent = PendingIntent.getService(getApplicationContext(),
                0,
                nextIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        Intent dismissIntent = new Intent(getApplicationContext(), MusicService.class);
        dismissIntent.setAction(Constant.ACTION_DISMIS);
        PendingIntent pdismisIntent = PendingIntent.getService(getApplicationContext(),
                0,
                dismissIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        int iconPlayPause;
        if (isPlay()) {
            iconPlayPause = R.drawable.ic_pause_black_24dp;
        } else {
            iconPlayPause = R.drawable.ic_play_arrow_black_24dp;
        }
        RemoteViews remoteViews = new RemoteViews(getPackageName(), R.layout.custom_notifi);
        remoteViews.setTextViewText(R.id.tv_name_track, mTitleTrack);
        remoteViews.setTextViewText(R.id.tv_name_artist, mArtist);
        remoteViews.setImageViewResource(R.id.img_pause, iconPlayPause);
        remoteViews.setOnClickPendingIntent(R.id.img_prev, ppreviousIntent);
        remoteViews.setOnClickPendingIntent(R.id.img_pause, pplayIntent);
        remoteViews.setOnClickPendingIntent(R.id.img_next, pnextIntent);
        remoteViews.setOnClickPendingIntent(R.id.img_cancel, pdismisIntent);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(
                getApplicationContext(), null);
        builder.setSmallIcon(R.drawable.icon_music_all)
                .setContentTitle(mTitleTrack)
                .setContentText(mArtist)
                .setContentIntent(pendInt)
                .setContent(remoteViews);

        Notification not = builder.build();
        startForeground(Constant.ID_FOREGROUND_SERVICE, not);
        sendNotifiChange();

    }

    /**
     * Binder
     */

    public class MusicBinder extends Binder {
        public MusicService getService() {
            return MusicService.this;
        }
    }
}
