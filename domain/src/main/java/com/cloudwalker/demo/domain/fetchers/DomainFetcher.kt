package com.cloudwalker.demo.domain.fetchers

import com.cloudwalker.demo.domain.modules.configuration.beans.ConfigurationBeanQ
import com.cloudwalker.demo.domain.modules.configuration.beans.ConfigurationBeanR
import com.cloudwalker.demo.domain.modules.moviesdetails.beans.MovieDetailsBeanQ
import com.cloudwalker.demo.domain.modules.moviesdetails.beans.MovieDetailsBeanR
import com.cloudwalker.demo.domain.modules.popularmovies.beans.PopularMoviesBeanQ
import com.cloudwalker.demo.domain.modules.popularmovies.beans.PopularMoviesBeanR
import com.google.gson.Gson
import io.reactivex.Observable

interface DomainFetcher {

    fun getPopularMovies(
        popularMoviesBeanQ: PopularMoviesBeanQ,
        gson: Gson
    ): Observable<PopularMoviesBeanR>

    fun getConfiguration(
        configurationBeanQ: ConfigurationBeanQ,
        gson: Gson
    ): Observable<ConfigurationBeanR>

    fun getMovieDetails(
        movieDetailsBeanQ: MovieDetailsBeanQ,
        gson: Gson
    ): Observable<MovieDetailsBeanR>
}