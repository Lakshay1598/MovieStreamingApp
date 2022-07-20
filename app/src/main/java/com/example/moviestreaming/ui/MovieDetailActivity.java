package com.example.moviestreaming.ui;

import android.os.Bundle;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.moviestreaming.R;
import com.example.moviestreaming.adapters.CastAdapter;
import com.example.moviestreaming.models.Cast;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class MovieDetailActivity extends AppCompatActivity {

    private ImageView MovieThumbnailImg, MovieCoverImg;
    private TextView tv_title,tv_description;
    private FloatingActionButton play_fab ;
    private RecyclerView rv_cast;
    private CastAdapter castAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);

        iniViews();

        setupRvCast();

    }

    void iniViews(){
        rv_cast = findViewById(R.id.rv_cast);
        play_fab = findViewById(R.id.play_fab);
        String movieTitle = getIntent().getExtras().getString("title");
        int imageResourceId = getIntent().getExtras().getInt("imgURL");
        int imagecover = getIntent().getExtras().getInt("imgCover");

        MovieThumbnailImg = findViewById(R.id.detail_movie_img);
        Glide.with(this).load(imageResourceId).into(MovieThumbnailImg);
        MovieThumbnailImg.setImageResource(imageResourceId);
        MovieCoverImg = findViewById(R.id.movie_detail_cover);
        Glide.with(this).load(imagecover).into(MovieCoverImg);
        tv_title = findViewById(R.id.detail_movie_title);
        tv_title.setText(movieTitle);
        getSupportActionBar().setTitle(movieTitle);
        tv_description = findViewById(R.id.detail_movie_dec);

        MovieCoverImg.setAnimation(AnimationUtils.loadAnimation(this,R.anim.scale_animation));
        play_fab.setAnimation(AnimationUtils.loadAnimation(this,R.anim.scale_animation));

    }

    void setupRvCast(){

        List<Cast> mData = new ArrayList<>();
        mData.add(new Cast("Matthew Perry",R.drawable.matthewpe));
        mData.add(new Cast("Courtney Cox",R.drawable.court));
        mData.add(new Cast("David Schwimmer",R.drawable.david));
        mData.add(new Cast("Jennifer Aniston",R.drawable.jen));
        mData.add(new Cast("Matt LeBlanc",R.drawable.matt));
        mData.add(new Cast("Lisa Kudrow",R.drawable.lisa));

        castAdapter = new CastAdapter(this,mData);
        rv_cast.setAdapter(castAdapter);
        rv_cast.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL,false));



    }


}