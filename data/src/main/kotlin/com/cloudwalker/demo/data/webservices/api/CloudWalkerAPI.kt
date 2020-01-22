package com.cloudwalker.demo.data.webservices.api

import com.cloudwalker.demo.domain.modules.configuration.beans.ConfigurationBeanR
import com.cloudwalker.demo.domain.modules.moviesdetails.beans.MovieDetailsBeanR
import com.cloudwalker.demo.domain.modules.moviesdetails.beans.MovieTrailer
import com.cloudwalker.demo.domain.modules.nowplaying.beans.NowPlayingMoviesBeanR
import com.cloudwalker.demo.domain.modules.popularmovies.beans.PopularMoviesBeanR
import com.cloudwalker.demo.domain.modules.searchmovie.beans.SearchMoviesBeanR
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query


interface CloudWalkerAPI {
    //https://api.themoviedb.org/3/movie/{movie_id}?api_key=<<api_key>>&language=en-US
    //https://api.themoviedb.org/3/movie/popular?api_key=<<api_key>>&language=en-US&page=1
    //https://api.themoviedb.org/3/movie/{movie_id}/videos?api_key=<<api_key>>&language=en-US
    //https://api.themoviedb.org/3/search/movie?api_key=7cf7266ab986643cce284d321ab6ea44&language=en-US&query=Sholey&page=1&include_adult=true

    @GET("configuration")
    fun getConfiguration(@Query("api_key") apiKey: String): Observable<ConfigurationBeanR>

    @GET("movie/popular")
    fun getPopularMovies(@Query("api_key") apiKey: String, @Query("page") page: String): Observable<PopularMoviesBeanR>

    @GET("movie/{movie_id}?")
    fun getMovieDetails(@Path("movie_id") movieId: String, @Query("api_key") apiKey: String): Observable<MovieDetailsBeanR>

    @GET("movie/{movie_id}/videos?")
    fun getTrailers(@Path("movie_id") movieId: String, @Query("api_key") apiKey: String): Observable<MovieTrailer>

    @GET("movie/top_rated")
    fun getNowPlaying(@Query("api_key") apiKey: String, @Query("page") page: String): Observable<NowPlayingMoviesBeanR>

    @GET("search/movie")
    fun getMoviesByName(@Query("api_key") apiKey: String, @Query("query") query: String, @Query("page") page: String): Observable<SearchMoviesBeanR>
}
