package com.example.moviesapp.features

import com.example.moviesapp.models.moviesResponse.MoviesResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface MoviesNetwork {

    @GET("movie/popular")
    fun getMovies(
        @Query("api_key") api_key: String?,
        @Query("page") page: Int?
    ): Call<MoviesResponse>?

}