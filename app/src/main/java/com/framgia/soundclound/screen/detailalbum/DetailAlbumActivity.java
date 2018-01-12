package com.framgia.soundclound.screen.detailalbum;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.framgia.soundclound.R;
import com.framgia.soundclound.databinding.ActivityAlbumDetailBinding;
import com.framgia.soundclound.util.Constant;

/**
 * Created by Bui Danh Nam on 11/1/2018.
 */

public class DetailAlbumActivity extends AppCompatActivity {
    public static Intent getInstance(Context context, int idAlbum) {
        Intent intent = new Intent(context, DetailAlbumActivity.class);
        intent.putExtra(Constant.EXTRA_ID_ALBUM, idAlbum);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityAlbumDetailBinding activityAlbumDetailBinding = DataBindingUtil
                .setContentView(this, R.layout.activity_album_detail);
        Intent intent = getIntent();
        int idAlbum = intent.getExtras().getInt(
                Constant.EXTRA_ID_ALBUM,
                Constant.VALUE_ID_ALBUM_NULL);
        DetailAlbumModelView mViewModel = new DetailAlbumModelView(this, idAlbum);
        activityAlbumDetailBinding.setViewModelAlbum(mViewModel);
    }
}
