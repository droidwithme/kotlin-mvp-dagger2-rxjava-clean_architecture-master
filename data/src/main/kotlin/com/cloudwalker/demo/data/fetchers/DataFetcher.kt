package com.cloudwalker.demo.data.fetchers

import com.cloudwalker.demo.data.webservices.restclient.RestClient
import com.cloudwalker.demo.domain.fetchers.DomainFetcher
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
import javax.inject.Inject

class DataFetcher
@Inject
constructor(private val restClient: RestClient) : DomainFetcher {
    override fun searchMovieByName(
        searchMoviesBeanQ: SearchMoviesBeanQ,
        gson: Gson
    ): Observable<SearchMoviesBeanR> {
        return restClient.cloudWalkerAPI.getMoviesByName(
            searchMoviesBeanQ.apiKey,
            searchMoviesBeanQ.searchKeyWord,
            searchMoviesBeanQ.pageNumber
        )
    }

    override fun getNowPlaying(
        nowPlayingMoviesBeanQ: NowPlayingMoviesBeanQ,
        gson: Gson
    ): Observable<NowPlayingMoviesBeanR> {
        return restClient.cloudWalkerAPI.getNowPlaying(
            nowPlayingMoviesBeanQ.apiKey,
            nowPlayingMoviesBeanQ.pageNumber
        )
    }

    override fun getMovieDetails(
        movieDetailsBeanQ: MovieDetailsBeanQ,
        gson: Gson
    ): Observable<MovieDetailsBeanR> {

        return Observable.create { emitter ->
            try {
                val movieDetailsBeanR = restClient.cloudWalkerAPI.getMovieDetails(
                    movieDetailsBeanQ.movieId,
                    movieDetailsBeanQ.apiKey
                ).blockingSingle()

                val movieTrailersBeanR = restClient.cloudWalkerAPI.getTrailers(
                    movieDetailsBeanQ.movieId,
                    movieDetailsBeanQ.apiKey
                ).blockingSingle()
                movieDetailsBeanR.trailer = movieTrailersBeanR.results[0].key
                emitter.onNext(movieDetailsBeanR)
                emitter.onComplete()
            } catch (error: Exception) {
                emitter.onError(error)
            }
        }
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
        return restClient.cloudWalkerAPI.getPopularMovies(
            popularMoviesBeanQ.apiKey,
            popularMoviesBeanQ.pageNumber
        )
    }
}