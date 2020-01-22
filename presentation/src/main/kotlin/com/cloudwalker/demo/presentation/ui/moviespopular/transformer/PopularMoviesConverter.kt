package com.cloudwalker.demo.presentation.ui.moviespopular.transformer

import com.google.gson.Gson
import com.cloudwalker.demo.domain.modules.popularmovies.beans.PopularMoviesBeanQ
import com.cloudwalker.demo.domain.modules.popularmovies.beans.PopularMoviesBeanR
import com.cloudwalker.demo.presentation.ui.moviespopular.model.PopularMoviesModelQ
import com.cloudwalker.demo.presentation.ui.moviespopular.model.PopularMoviesModelR
import com.cloudwalker.utils.Utils
import javax.inject.Inject

private val TAG = PopularMoviesConverter::class.java.simpleName

class PopularMoviesConverter
@Inject
constructor(private val gson: Gson, private val utils: Utils) {
    // Convert Mobile Domain Bean to Presentation Model
    fun transformBean(popularMoviesBeanR: PopularMoviesBeanR): PopularMoviesModelR {
        utils.showLog(TAG, "transformBean($popularMoviesBeanR)")
        return gson.fromJson(gson.toJson(popularMoviesBeanR), PopularMoviesModelR::class.java)
    }

    // Convert Mobile Presentation Model to Domain Bean
    fun transformModel(popularMoviesModelQ: PopularMoviesModelQ): PopularMoviesBeanQ {
        utils.showLog(TAG, "transformModel($popularMoviesModelQ)")
        return gson.fromJson(gson.toJson(popularMoviesModelQ), PopularMoviesBeanQ::class.java)
    }
}