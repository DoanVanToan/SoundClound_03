package com.framgia.soundclound.util;

import android.content.Context;
import android.databinding.BindingAdapter;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.widget.ImageView;

import com.framgia.soundclound.R;
import com.framgia.soundclound.util.designhelper.GridSpacingItem;


/**
 * Created by Bui Danh Nam on 29/12/2017.
 */

public class HomeBindingUtils {

    @BindingAdapter("src")
    public static void setImage(ImageView view, int resource) {
        view.setImageResource(resource);
    }

    @BindingAdapter("adapter")
    public static void setReccyclerAdapter(RecyclerView view, RecyclerView.Adapter adapter) {
        view.setAdapter(adapter);
        view.setItemAnimator(new DefaultItemAnimator());
    }

    @BindingAdapter("config")
    public static void configRecycleView(RecyclerView recyclerView, String c) {
        Context context = recyclerView.getContext();
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(context,
                Constant.NUM_COLUMN_GRID);
        recyclerView.setLayoutManager(layoutManager);

        int wItemPx = context.getResources().getDimensionPixelSize(R.dimen.item_genres_w);
        int hItemPx = context.getResources().getDimensionPixelSize(R.dimen.item_genres_h);
        int spacingItemWidth = calculatSpacingItemWidth(context, wItemPx);
        int spacingItemHight = calculatSpacingItemHight(context, hItemPx);

        recyclerView.addItemDecoration(new GridSpacingItem(Constant.NUM_COLUMN_GRID,
                spacingItemWidth, spacingItemWidth, true));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
    }

    public static int calculatSpacingItemWidth(Context context, int wItemPx) {
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        float displayWidthPx = displayMetrics.widthPixels;
        return (int)
                ((displayWidthPx
                        - (wItemPx * Constant.NUM_COLUMN_GRID)) / Constant.NUM_COLUMN_GRID);
    }

    public static int calculatSpacingItemHight(Context context, int hItemPx) {
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        float displayHightPx = displayMetrics.heightPixels;
        return (int)
                ((displayHightPx
                        - (hItemPx * Constant.NUM_ROW_GIRD)) / Constant.NUM_ROW_GIRD);
    }
}
