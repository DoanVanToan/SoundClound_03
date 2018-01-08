package com.framgia.soundclound.util.designhelper;


import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by Bui Danh Nam on 6/1/2018.
 */

public class GridSpacingItem extends RecyclerView.ItemDecoration {

    private int mSpanCount;
    private int mSpacingWidth;
    private int mSpacingHight;
    private boolean mIncludeEdge;

    public GridSpacingItem(int spanCount, int spacingW, int spacingH, boolean includeEdge) {
        this.mSpanCount = spanCount;
        this.mSpacingWidth = spacingW;
        this.mSpacingHight = spacingH;
        this.mIncludeEdge = includeEdge;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent,
                               RecyclerView.State state) {
        int position = parent.getChildAdapterPosition(view);
        int column = position % mSpanCount;

        if (mIncludeEdge) {
            outRect.left = mSpacingWidth - column * mSpacingWidth / mSpanCount;
            outRect.right = (column + 1) * mSpacingWidth / mSpanCount;

            if (position < mSpanCount) {
                outRect.top = mSpacingHight;
            }
            outRect.bottom = mSpacingHight;
        } else {
            outRect.left = column * mSpacingWidth / mSpanCount;
            outRect.right = mSpacingWidth - (column + 1) * mSpacingWidth / mSpanCount;
            if (position >= mSpanCount) {
                outRect.top = mSpacingHight;
            }
        }
    }
}
