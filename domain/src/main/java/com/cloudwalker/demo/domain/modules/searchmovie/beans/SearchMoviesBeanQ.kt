package com.cloudwalker.demo.domain.modules.searchmovie.beans

data class SearchMoviesBeanQ(
    val apiKey: String,
    val language: String,
    val searchKeyWord: String,
    val pageNumber: String
)