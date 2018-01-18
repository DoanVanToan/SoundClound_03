package com.framgia.soundclound.screen.playtrack;

import android.content.ComponentName;
import android.content.ServiceConnection;
import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.os.IBinder;
import android.view.View;
import android.widget.ImageView;
import android.widget.SeekBar;

import com.framgia.soundclound.R;
import com.framgia.soundclound.data.model.Track;
import com.framgia.soundclound.data.source.local.SharePreferences;
import com.framgia.soundclound.service.MusicService;
import com.framgia.soundclound.util.StringUtil;

import java.util.List;
import java.util.logging.Logger;

/**
 * Created by Sony on 1/11/2018.
 */

public class PlayTrackViewModel extends BaseObservable implements SeekBar.OnSeekBarChangeListener {
    private String mTotalDuration;
    private String mCurentDuration;
    private int mProgressSeekBar;
    private List<Track> mTracks;
    private int mIndexCurrentTrack;
    private boolean mPlay;
    private Track mTrack;
    private static MusicService mMusicService = null;
    private boolean mMusicBound = false;
    private OnClickTrackListener mOnClickTrackListener;
    private boolean mRuning = true;

    public void setOnClickTrackListener(OnClickTrackListener onClickTrackListener) {
        mOnClickTrackListener = onClickTrackListener;
    }

    private ServiceConnection mServiceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            MusicService.MusicBinder musicServiceBinder = (MusicService.MusicBinder) service;
            mMusicService = musicServiceBinder.getService();
            mMusicBound = true;
            boolean x = mMusicService.checkTrackPlay(mTracks.get(mIndexCurrentTrack));
            if (!mMusicService.isPlay()
                    || !mMusicService.checkTrackPlay(mTracks.get(mIndexCurrentTrack))) {
                startPlayMusic();
            } else if (mMusicService.checkTrackPlay(mTracks.get(mIndexCurrentTrack))) {
                updateProcess();
            }
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            mMusicBound = false;
        }
    };

    public PlayTrackViewModel(List<Track> tracks, int indexCurentTrack) {
        mTracks = tracks;
        mIndexCurrentTrack = indexCurentTrack;
        setTrack(mTracks.get(indexCurentTrack));
    }

    @Bindable
    public boolean isPlay() {
        return mPlay;
    }

    public void setPlay(boolean play) {
        mPlay = play;
        notifyPropertyChanged(0);
    }

    @Bindable
    public Track getTrack() {
        return mTrack;
    }

    public void setTrack(Track track) {
        mTrack = track;
        notifyPropertyChanged(0);
    }

    public String getArtist() {
        if (mTrack != null) {
            return mTrack.getPublisherMetadata() != null ? mTrack.getPublisherMetadata().getArtist()
                    : "";
        }
        return "";
    }

    public void setRuning(boolean runing) {
        mRuning = runing;
    }

    public String getImageTrack() {
        if (mTrack != null) {
            return mTrack.getArtworkUrl();
        }
        return null;
    }

    public String getTitle() {
        return mTrack != null ? mTrack.getTitle() : "";
    }

    @Bindable
    public String getTotalDuration() {
        return mTotalDuration;
    }

    public void setTotalDuration(String totalDuration) {
        mTotalDuration = totalDuration;
        notifyPropertyChanged(0);
    }

    @Bindable
    public String getCurentDuration() {
        return mCurentDuration;
    }

    public void setCurentDuration(String curentDuration) {
        mCurentDuration = curentDuration;
        notifyPropertyChanged(0);
    }

    @Bindable
    public int getProgressSeekBar() {
        return mProgressSeekBar;
    }

    public void setProgressSeekBar(int progressSeekBar) {
        mProgressSeekBar = progressSeekBar;
        notifyPropertyChanged(0);
    }

    public ServiceConnection getServiceConnection() {
        return mServiceConnection;
    }

    public boolean isMusicBound() {
        return mMusicBound;
    }

    public void startPlayMusic() {
        mMusicService.play();
        updateProcess();
    }

    private void updateProcess() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (mRuning) {
                    try {
                        Thread.sleep(1000);
                        long totalDuration = mMusicService.getDuration();
                        long currentDuration = mMusicService.getPosition();
                        setCurentDuration(StringUtil.milliSecondsToTimer(currentDuration));
                        setTotalDuration(StringUtil.milliSecondsToTimer(totalDuration));
                        int progress = (int) (StringUtil.getProgressPercentage(currentDuration,
                                totalDuration));
                        setProgressSeekBar(progress);
                    } catch (InterruptedException e) {
                        Logger.getLogger(e.toString());
                    }
                }
            }
        }).start();
    }

    public void onClickPause(View view) {
        ImageView imageView = (ImageView) view;
        if (mMusicService.isPlay()) {
            mMusicService.pause();
            imageView.setImageResource(R.drawable.ic_play_arrow_black_24dp);
        } else {
            mMusicService.start();
            imageView.setImageResource(R.drawable.ic_pause_black_24dp);
        }
    }

    public void onClickNext(View view) {
        mMusicService.next();
        updateUI();
    }

    public void onClickShuffle(View view) {
        mMusicService.changeSuffleSetting();
    }

    public void onClickLoop(View view) {
        mMusicService.changeRepeatSetting();
    }

    public void onClickPrevious(View view) {
        mMusicService.prev();
        updateUI();
    }

    public void updateUI() {
        List<Track> tracks = SharePreferences.getInstance().getListTrack();
        mIndexCurrentTrack = SharePreferences.getInstance().getIndex();
        if (tracks != null) {
            setTrack(tracks.get(mIndexCurrentTrack));
        }
    }

    public void onClickBack(View view) {
        mOnClickTrackListener.onClickBack(view);
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {
    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {
        int currentPosition = StringUtil.progressToTimer(
                seekBar.getProgress(), mMusicService.getDuration());
        mMusicService.seekto(currentPosition);
    }
}
