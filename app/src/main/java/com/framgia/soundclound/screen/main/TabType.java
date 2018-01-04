package com.framgia.soundclound.screen.main;

import android.support.annotation.IntDef;

import static com.framgia.soundclound.screen.main.TabType.LOCAL;
import static com.framgia.soundclound.screen.main.TabType.FAVORITE;
import static com.framgia.soundclound.screen.main.TabType.HOME;
import static com.framgia.soundclound.screen.main.TabType.PLAY_LIST;

/**
 * TabType in Tablayout*/
@IntDef({HOME, PLAY_LIST, LOCAL, FAVORITE})
public @interface TabType {
    int HOME = 0;
    int PLAY_LIST = 1;
    int LOCAL = 2;
    int FAVORITE = 3;
}
