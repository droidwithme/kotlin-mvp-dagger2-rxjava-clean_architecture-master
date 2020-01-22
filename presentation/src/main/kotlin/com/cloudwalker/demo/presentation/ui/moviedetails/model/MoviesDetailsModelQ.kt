package com.cloudwalker.demo.presentation.ui.moviedetails.model

import com.cloudwalker.demo.presentation.main.runtime.Variables

data class MoviesDetailsModelQ(val apiKey: String = Variables.apiKey,  val movieId: String)