package com.cloudwalker.demo.presentation.ui.searchmovie.model

import com.cloudwalker.demo.presentation.main.runtime.Variables

data class SearchMoviesModelQ(
    val apiKey: String = Variables.apiKey,
    val language: String = "en-US",
    var searchKeyWord: String,
    var pageNumber: String
)