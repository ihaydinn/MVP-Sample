package com.ismailhakkiaydin.mvpsample.contract;

import com.ismailhakkiaydin.mvpsample.model.Movie;

import java.util.List;

public interface IMovieContract {

    interface View{

        void init();
        void showProgress();
        void hideProgress();
        void showError(String errorMessage);
        void loadList(List<Movie> movieList);

    }

    interface Presenter{

        void start();
        void fetchMovies();

    }

}
