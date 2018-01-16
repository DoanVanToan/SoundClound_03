package com.framgia.soundclound;

/**
 * Created by Bui Danh Nam on 11/1/2018.
 */

public interface BaseMediaPlayer {

    void play();

    void start();

    void pause();

    void stop();

    void release();

    void seekto(int position);

    void next();

    void prev();

    int getDuration();

    int getPosition();

    boolean isPlay();

}
