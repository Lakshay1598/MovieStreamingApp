package com.example.moviestreaming.ui;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.example.moviestreaming.models.Movie;
import com.example.moviestreaming.adapters.MovieAdapter;
import com.example.moviestreaming.adapters.MovieItemClickListener;
import com.example.moviestreaming.R;
import com.example.moviestreaming.models.Slide;
import com.example.moviestreaming.adapters.SliderPageAdapter;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class  MainActivity extends AppCompatActivity implements MovieItemClickListener {

    private List<Slide> listSlide;
    private ViewPager sliderPage;
    private TabLayout indicator;
    private RecyclerView MoviesRV;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sliderPage = findViewById(R.id.slider_page);
        indicator = findViewById(R.id.indicator);
        MoviesRV = findViewById(R.id.Rv_movies);

        //Prepare a list of slides
        listSlide = new ArrayList<>();
        listSlide.add(new Slide(R.drawable.friends, "Friends"));
        listSlide.add(new Slide(R.drawable.dragon, "Mother of Dragons"));
        listSlide.add(new Slide(R.drawable.peaky, "Peaky Blinders"));
        listSlide.add(new Slide(R.drawable.darkknight, "The Dark Knight"));

        SliderPageAdapter adapter = new SliderPageAdapter(this, listSlide);
        sliderPage.setAdapter(adapter);

        //Setup Timer
        Timer timer =new Timer();
        timer.scheduleAtFixedRate(new MainActivity.SliderTimer(),4000,6000);

        indicator.setupWithViewPager(sliderPage,true);

        //RecyclerView Setup
        //ini data
        List<Movie> listMovies = new ArrayList<>();
        listMovies.add(new Movie("Toy Story", R.drawable.toystory,R.drawable.twoandhalf));
        listMovies.add(new Movie("Conjuring", R.drawable.conjuring,R.drawable.twoandhalf));
        listMovies.add(new Movie("Interstellar", R.drawable.interstellar,R.drawable.twoandhalf));
        listMovies.add(new Movie("Dangal", R.drawable.dangal,R.drawable.twoandhalf));
        listMovies.add(new Movie("Breaking Bad", R.drawable.breaking,R.drawable.twoandhalf));

        MovieAdapter movieAdapter = new MovieAdapter(this,listMovies,this);
        MoviesRV.setAdapter(movieAdapter);
        MoviesRV.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL,false));


    }

    @Override
    public void onMovieClick(Movie movie, ImageView movieImageView) {

        Intent intent = new Intent(this, MovieDetailActivity.class);
        intent.putExtra("title", movie.getTitle());
        intent.putExtra("imgURL",movie.getThumbnail());
        intent.putExtra("imgCover",movie.getCoverPhoto());
        startActivity(intent);

        ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(MainActivity.this,movieImageView,"sharedName");
        startActivity(intent,options.toBundle());

        Toast.makeText(this,"item clicked : "+movie.getTitle(),Toast.LENGTH_LONG).show();
    }

    class SliderTimer extends TimerTask{


        @Override
        public void run() {
            MainActivity.this.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    if(sliderPage.getCurrentItem()<listSlide.size()-1){
                        sliderPage.setCurrentItem(sliderPage.getCurrentItem()+1);
                    }
                    else{
                        sliderPage.setCurrentItem(0);
                    }
                }
            });
        }
    }




}