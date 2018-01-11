package com.framgia.soundclound.screen.main;

import android.databinding.DataBindingUtil;
import android.os.Bundle;

import android.support.v7.app.AppCompatActivity;

import com.framgia.soundclound.R;
import com.framgia.soundclound.databinding.ActivityMainBinding;

/**
 * Created by Sony on 1/4/2018.
 */


public class MainActivity extends AppCompatActivity {

    private MainViewModel mViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mViewModel = new MainViewModel(this);
        ActivityMainBinding binding = DataBindingUtil
                .setContentView(this, R.layout.activity_main);
        binding.setViewModel((MainViewModel) mViewModel);
        getSupportActionBar().hide();
    }

}
