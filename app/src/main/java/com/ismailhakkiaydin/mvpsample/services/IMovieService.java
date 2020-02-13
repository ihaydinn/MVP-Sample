package com.ismailhakkiaydin.mvpsample.services;

import com.ismailhakkiaydin.mvpsample.model.Movie;

import java.util.List;


import io.reactivex.Observable;
import retrofit2.http.GET;

public interface IMovieService {

    @GET("json/movies.json")
    Observable<List<Movie>> getMovieList();

}
