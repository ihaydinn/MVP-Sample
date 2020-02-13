package com.ismailhakkiaydin.mvpsample.presenter;

import com.ismailhakkiaydin.mvpsample.contract.IMovieContract;
import com.ismailhakkiaydin.mvpsample.model.Movie;
import com.ismailhakkiaydin.mvpsample.services.Const;
import com.ismailhakkiaydin.mvpsample.services.IMovieService;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class MoviePresenter implements IMovieContract.Presenter {

    IMovieContract.View mView;

    public MoviePresenter(IMovieContract.View mView) {
        this.mView = mView;
    }

    @Override
    public void start() {

        mView.init();

    }

    @Override
    public void fetchMovies() {

        IMovieService iMovieService = new Retrofit.Builder()
                .baseUrl(Const.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build().create(IMovieService.class);

        iMovieService.getMovieList()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<List<Movie>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                        mView.showProgress();
                    }

                    @Override
                    public void onNext(List<Movie> movieList) {

                        mView.hideProgress();
                        mView.loadList(movieList);
                    }

                    @Override
                    public void onError(Throwable e) {

                        mView.showError(e.toString());
                    }

                    @Override
                    public void onComplete() {

                    }
                });


    }
}
