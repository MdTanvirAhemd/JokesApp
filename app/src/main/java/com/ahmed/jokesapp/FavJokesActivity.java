package com.ahmed.jokesapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.ahmed.jokesapp.fragments.FavJokesFragment;

public class FavJokesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fav_jokes);

        FavJokesFragment mFavJokesFragment = FavJokesFragment.newInstance();
        getSupportFragmentManager().beginTransaction().replace(R.id.fav_jokes_container, mFavJokesFragment).commit();
    }
}