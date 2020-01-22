package com.cloudwalker.demo.data.fetchers

import com.cloudwalker.demo.data.webservices.restclient.RestClient
import com.cloudwalker.demo.domain.fetchers.DomainFetcher
import com.cloudwalker.demo.domain.modules.configuration.beans.ConfigurationBeanQ
import com.cloudwalker.demo.domain.modules.configuration.beans.ConfigurationBeanR
import com.cloudwalker.demo.domain.modules.moviesdetails.beans.MovieDetailsBeanQ
import com.cloudwalker.demo.domain.modules.moviesdetails.beans.MovieDetailsBeanR
import com.cloudwalker.demo.domain.modules.popularmovies.beans.PopularMoviesBeanQ
import com.cloudwalker.demo.domain.modules.popularmovies.beans.PopularMoviesBeanR
import com.google.gson.Gson
import io.reactivex.Observable
import javax.inject.Inject

class DataFetcher
@Inject
constructor(private val restClient: RestClient) : DomainFetcher {
    override fun getMovieDetails(
        movieDetailsBeanQ: MovieDetailsBeanQ,
        gson: Gson
    ): Observable<MovieDetailsBeanR > {
        return restClient.cloudWalkerAPI.getMovieDetails(movieDetailsBeanQ.apiKey)
    }

    override fun getConfiguration(
        configurationBeanQ: ConfigurationBeanQ,
        gson: Gson
    ): Observable<ConfigurationBeanR> {
        return restClient.cloudWalkerAPI.getConfiguration(configurationBeanQ.apiKey)
    }

    override fun getPopularMovies(
        popularMoviesBeanQ: PopularMoviesBeanQ,
        gson: Gson
    ): Observable<PopularMoviesBeanR> {
        return restClient.cloudWalkerAPI.getPopularMovies(popularMoviesBeanQ.apiKey)
    }
}