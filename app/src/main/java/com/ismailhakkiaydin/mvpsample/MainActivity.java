package com.ismailhakkiaydin.mvpsample;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.ismailhakkiaydin.mvpsample.adapters.MoviesAdapter;
import com.ismailhakkiaydin.mvpsample.contract.IMovieContract;
import com.ismailhakkiaydin.mvpsample.model.Movie;
import com.ismailhakkiaydin.mvpsample.presenter.MoviePresenter;

import java.util.List;

public class MainActivity extends AppCompatActivity implements IMovieContract.View {

    private RecyclerView recyclerView;
    private MoviesAdapter moviesAdapter;
    private ProgressBar progressBar;

    private MoviePresenter moviePresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        moviePresenter = new MoviePresenter(MainActivity.this);
        moviePresenter.start();

    }

    @Override
    public void init() {

        recyclerView = findViewById(R.id.recyclerview);
        progressBar = findViewById(R.id.progressBar);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        moviePresenter.fetchMovies();

    }

    @Override
    public void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        if (progressBar != null && progressBar.isShown()) {
            progressBar.setVisibility(View.GONE);
        }
    }

    @Override
    public void showError(String errorMessage) {
        Toast.makeText(this, "Hata : " + errorMessage, Toast.LENGTH_LONG).show();
    }

    @Override
    public void loadList(List<Movie> movieList) {
        moviesAdapter = new MoviesAdapter(movieList, getApplicationContext());
        recyclerView.setAdapter(moviesAdapter);

    }
}
