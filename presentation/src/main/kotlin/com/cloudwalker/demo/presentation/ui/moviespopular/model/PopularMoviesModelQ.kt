package com.cloudwalker.demo.presentation.ui.moviespopular.model

import com.cloudwalker.demo.presentation.main.runtime.Variables

data class PopularMoviesModelQ(val apiKey: String = Variables.apiKey, val language: String = "HI")