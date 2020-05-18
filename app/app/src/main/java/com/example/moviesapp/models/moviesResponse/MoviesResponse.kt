package com.example.moviesapp.models.moviesResponse

data class MoviesResponse(
    val page: Int,
    val results: List<Movie>,
    val total_pages: Int,
    val total_results: Int,
    val status_message: String

)