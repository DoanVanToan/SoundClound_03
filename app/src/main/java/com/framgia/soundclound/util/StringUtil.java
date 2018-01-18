package com.framgia.soundclound.util;

import com.framgia.soundclound.BuildConfig;

/**
 * Created by Sony on 1/5/2018.
 */

public class StringUtil {

    public static String convertUrl(String url, String genre, int limit, int offSet) {
        StringBuffer stringBuffer = new StringBuffer(url);
        stringBuffer.append(genre)
                .append('&')
                .append(Constant.CLIENT_ID)
                .append('=')
                .append(BuildConfig.API_KEY)
                .append('&')
                .append(Constant.LIMIT)
                .append('=')
                .append(limit)
                .append('&')
                .append(Constant.OFF_SET)
                .append('=').append(offSet);
        return stringBuffer.toString();

    }

    public static String getUrlDownload(String url) {
        StringBuffer stringBuffer = new StringBuffer(url);
        stringBuffer.append("/stream?")
                .append(Constant.CLIENT_ID)
                .append('=')
                .append(BuildConfig.API_KEY);
        return stringBuffer.toString();
    }

    public static String milliSecondsToTimer(long milliseconds) {
        String finalTimerString = "";
        String secondsString = "";

        int hours = (int) (milliseconds / (1000 * 60 * 60));
        int minutes = (int) (milliseconds % (1000 * 60 * 60)) / (1000 * 60);
        int seconds = (int) ((milliseconds % (1000 * 60 * 60)) % (1000 * 60) / 1000);
        if (hours > 0) {
            finalTimerString = hours + ":";
        }
        if (seconds < 10) {
            secondsString = "0" + seconds;
        } else {
            secondsString = "" + seconds;
        }
        finalTimerString = finalTimerString + minutes + ":" + secondsString;
        return finalTimerString;
    }

    public static int getProgressPercentage(long currentDuration, long totalDuration) {
        Double percentage = (double) 0;
        long currentSeconds = (int) (currentDuration / 1000);
        long totalSeconds = (int) (totalDuration / 1000);
        percentage = (((double) currentSeconds) / totalSeconds) * 100;
        return percentage.intValue();
    }

    public static int progressToTimer(int progress, int totalDuration) {
        int currentDuration = 0;
        totalDuration = (int) (totalDuration / 1000);
        currentDuration = (int) ((((double) progress) / 100) * totalDuration);
        return currentDuration * 1000;
    }
}
