package com.framgia.soundclound.screen;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.framgia.soundclound.util.Constant;

/**
 * Created by Sony on 1/16/2018.
 */

public abstract class EndSrcollListenner extends RecyclerView.OnScrollListener {
    private int mVisibleThreshold = Constant.LIMITT;
    private int mOffSet = Constant.ZERO;
    private int mPreviousTotalItemCount = Constant.ZERO;
    private boolean mLoading = true;
    private int mStartingOffSet = Constant.ZERO;

    public EndSrcollListenner() {
    }

    @Override
    public void onScrolled(RecyclerView view, int dx, int dy) {
        int lastVisibleItemPosition = Constant.ZERO;
        RecyclerView.LayoutManager layoutManager = view.getLayoutManager();
        int totalItemCount = layoutManager.getItemCount();
        
        lastVisibleItemPosition = ((LinearLayoutManager)
                layoutManager).findLastVisibleItemPosition();

        if (totalItemCount < mPreviousTotalItemCount) {
            this.mOffSet = this.mStartingOffSet;
            this.mPreviousTotalItemCount = totalItemCount;
            if (totalItemCount == Constant.ZERO) {
                this.mLoading = true;
            }
        }

        if (mLoading && (totalItemCount > mPreviousTotalItemCount)) {
            mLoading = false;
            mPreviousTotalItemCount = totalItemCount;
        }

        if (!mLoading && (lastVisibleItemPosition + mVisibleThreshold) > totalItemCount) {
            mOffSet = mOffSet + Constant.LIMIT_DEFAULT;
            onLoadMore(mOffSet, totalItemCount);
            mLoading = true;
        }
    }

    public void resetState() {
        this.mOffSet = this.mStartingOffSet;
        this.mPreviousTotalItemCount = Constant.ZERO;
        this.mLoading = true;
    }

    public abstract void onLoadMore(int offSet, int totalItemsCount);

}
