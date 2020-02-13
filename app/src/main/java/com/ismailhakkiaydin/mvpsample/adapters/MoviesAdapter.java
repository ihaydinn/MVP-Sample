package com.ismailhakkiaydin.mvpsample.adapters;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.ismailhakkiaydin.mvpsample.R;
import com.ismailhakkiaydin.mvpsample.model.Movie;

import java.util.List;

public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.MoviesHolder>{

    List<Movie> movieList;
    Context context;

    public MoviesAdapter(List<Movie> movieList, Context context) {
        this.movieList = movieList;
        this.context = context;
    }

    @Override
    public MoviesHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.movie_item, parent,false);
        MoviesHolder moviesHolder = new MoviesHolder(view);

        return moviesHolder;
    }

    @Override
    public void onBindViewHolder(MoviesHolder holder, int position) {

        holder.movieName.setText(movieList.get(position).getTitle());
        Glide.with(context).load(movieList.get(position).getImage()).apply(new RequestOptions().centerCrop()).into(holder.movieImage);

    }

    @Override
    public int getItemCount() {
        return movieList.size();
    }

    public class MoviesHolder extends RecyclerView.ViewHolder {

        TextView movieName;
        ImageView movieImage;

        public MoviesHolder(View itemView) {
            super(itemView);

            movieName = itemView.findViewById(R.id.movieTxt);
            movieImage = itemView.findViewById(R.id.movieIcon);
        }
    }

}
