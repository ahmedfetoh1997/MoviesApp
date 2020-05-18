package com.example.moviesapp.Remote

import com.example.moviesapp.features.MoviesNetwork

class ApiUtil {
companion object {
    fun getMoviesNetwork(): MoviesNetwork? {
        return RetrofitClient.getClient()?.create(MoviesNetwork::class.java)
    }

}
}