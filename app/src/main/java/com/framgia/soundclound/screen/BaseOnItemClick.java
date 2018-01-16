package com.framgia.soundclound.screen;

import android.widget.ImageView;

/**
 * Created by Bui Danh Nam on 11/1/2018.
 */

public interface BaseOnItemClick<T> {
    void onItemClick(T t, int pos);

    void onIconMoreClick(ImageView imageView, T t);
}
