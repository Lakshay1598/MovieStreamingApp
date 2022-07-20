package com.example.moviestreaming.adapters;

import android.widget.ImageView;

import com.example.moviestreaming.models.Movie;

public interface MovieItemClickListener {

    void onMovieClick(Movie movie, ImageView movieImageView);

}
