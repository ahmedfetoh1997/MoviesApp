package com.example.moviesapp.models.moviesResponse

data class moviesResponse(
    val page: Int,
    val results: List<Result>,
    val total_pages: Int,
    val total_results: Int
)