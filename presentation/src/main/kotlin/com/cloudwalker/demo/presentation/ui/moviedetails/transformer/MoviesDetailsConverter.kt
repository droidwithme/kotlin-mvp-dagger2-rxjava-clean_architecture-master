package com.cloudwalker.demo.presentation.ui.moviedetails.transformer

import com.cloudwalker.demo.domain.modules.moviesdetails.beans.MovieDetailsBeanQ
import com.cloudwalker.demo.domain.modules.moviesdetails.beans.MovieDetailsBeanR
import com.google.gson.Gson
import com.cloudwalker.demo.domain.modules.popularmovies.beans.PopularMoviesBeanQ
import com.cloudwalker.demo.domain.modules.popularmovies.beans.PopularMoviesBeanR
import com.cloudwalker.demo.presentation.ui.moviedetails.model.MoviesDetailsModelQ
import com.cloudwalker.demo.presentation.ui.moviedetails.model.MoviesDetailsModelR
import com.cloudwalker.demo.presentation.ui.moviespopular.model.PopularMoviesModelQ
import com.cloudwalker.demo.presentation.ui.moviespopular.model.PopularMoviesModelR
import com.cloudwalker.utils.Utils
import javax.inject.Inject

private val TAG = MoviesDetailsConverter::class.java.simpleName

class MoviesDetailsConverter
@Inject
constructor(private val gson: Gson, private val utils: Utils) {
    // Convert Mobile Domain Bean to Presentation Model
    fun transformBean(movieDetailsBeanR: MovieDetailsBeanR): MoviesDetailsModelR {
        utils.showLog(TAG, "transformBean($movieDetailsBeanR)")
        return gson.fromJson(gson.toJson(movieDetailsBeanR), MoviesDetailsModelR::class.java)
    }

    // Convert Mobile Presentation Model to Domain Bean
    fun transformModel(moviesDetailsModelQ: MoviesDetailsModelQ): MovieDetailsBeanQ {
        utils.showLog(TAG, "transformModel($moviesDetailsModelQ)")
        return gson.fromJson(gson.toJson(moviesDetailsModelQ), MovieDetailsBeanQ::class.java)
    }
}