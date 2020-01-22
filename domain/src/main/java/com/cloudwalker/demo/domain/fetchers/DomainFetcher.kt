package com.cloudwalker.demo.domain.fetchers

import com.cloudwalker.demo.domain.modules.configuration.beans.ConfigurationBeanQ
import com.cloudwalker.demo.domain.modules.configuration.beans.ConfigurationBeanR
import com.cloudwalker.demo.domain.modules.moviesdetails.beans.MovieDetailsBeanQ
import com.cloudwalker.demo.domain.modules.moviesdetails.beans.MovieDetailsBeanR
import com.cloudwalker.demo.domain.modules.nowplaying.beans.NowPlayingMoviesBeanQ
import com.cloudwalker.demo.domain.modules.nowplaying.beans.NowPlayingMoviesBeanR
import com.cloudwalker.demo.domain.modules.popularmovies.beans.PopularMoviesBeanQ
import com.cloudwalker.demo.domain.modules.popularmovies.beans.PopularMoviesBeanR
import com.cloudwalker.demo.domain.modules.searchmovie.beans.SearchMoviesBeanQ
import com.cloudwalker.demo.domain.modules.searchmovie.beans.SearchMoviesBeanR
import com.google.gson.Gson
import io.reactivex.Observable

interface DomainFetcher {


    fun getConfiguration(
        configurationBeanQ: ConfigurationBeanQ,
        gson: Gson
    ): Observable<ConfigurationBeanR>

    fun getPopularMovies(
        popularMoviesBeanQ: PopularMoviesBeanQ,
        gson: Gson
    ): Observable<PopularMoviesBeanR>

    fun getNowPlaying(
        nowPlayingMoviesBeanQ: NowPlayingMoviesBeanQ,
        gson: Gson
    ): Observable<NowPlayingMoviesBeanR>

    fun getMovieDetails(
        movieDetailsBeanQ: MovieDetailsBeanQ,
        gson: Gson
    ): Observable<MovieDetailsBeanR>

    fun searchMovieByName(
        searchMoviesBeanQ: SearchMoviesBeanQ,
        gson: Gson
    ): Observable<SearchMoviesBeanR>
}