package com.cloudwalker.demo.presentation.ui.nowplaying.model

import com.cloudwalker.demo.presentation.main.runtime.Variables

data class NowPlayingMoviesModelQ(val apiKey: String = Variables.apiKey, val language: String = "HI", val pageNumber: String)