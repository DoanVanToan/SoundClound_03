package com.framgia.soundclound.util;

import com.framgia.soundclound.BuildConfig;

/**
 * Created by Sony on 1/5/2018.
 */

public class StringUtil {

    public static String  convertUrl(String url, String genre, int limit, int offSet) {
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
        return  stringBuffer.toString();
    }
}
