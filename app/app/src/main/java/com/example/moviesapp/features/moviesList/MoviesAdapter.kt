package com.example.moviesapp.features.moviesList

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.moviesapp.R
import com.example.moviesapp.databinding.ViewMovieListitemBinding
import com.example.moviesapp.models.moviesResponse.Movie

class MoviesAdapter(val movies: MutableList<Movie>) :
    RecyclerView.Adapter<MoviesAdapter.MoviesViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesViewHolder {
        val holder: MoviesViewHolder

        val inflater = LayoutInflater.from(parent.context)
        val itemBinding: ViewMovieListitemBinding =
            DataBindingUtil.inflate(inflater, R.layout.view_movie_listitem, parent, false)
        holder = MoviesViewHolder(itemBinding)
        return holder
    }

    override fun getItemCount(): Int = movies.size

    class MoviesViewHolder(var itemBinding: ViewMovieListitemBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {
        fun bind(movie: Movie) {
            var imagePath= "https://image.tmdb.org/t/p/original"+movie.poster_path
            movie.poster_path=imagePath
            itemBinding.movie = movie
        }

    }

    override fun onBindViewHolder(holder: MoviesViewHolder, position: Int) {
        holder.bind(movies.get(position))
    }

    fun addMore(moviesList: List<Movie>?) {
        val startPosition: Int = movies.size
        if (movies.size == 0) {
            movies.addAll(moviesList!!)
            notifyDataSetChanged()
        } else {
            movies.addAll(moviesList!!)
            notifyItemRangeInserted(startPosition, movies.size)
        }
    }


}
