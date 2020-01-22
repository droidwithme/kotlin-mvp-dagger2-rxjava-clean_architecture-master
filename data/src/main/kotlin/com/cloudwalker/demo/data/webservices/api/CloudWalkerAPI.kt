package com.cloudwalker.demo.data.webservices.api

import com.cloudwalker.demo.domain.modules.configuration.beans.ConfigurationBeanR
import com.cloudwalker.demo.domain.modules.moviesdetails.beans.MovieDetailsBeanR
import com.cloudwalker.demo.domain.modules.popularmovies.beans.PopularMoviesBeanR
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface CloudWalkerAPI {
    @POST("movie/popular")
    fun getPopularMovies(@Query("api_key") apiKey: String): Observable<PopularMoviesBeanR>

    @GET("configuration")
    fun getConfiguration(@Query("api_key") apiKey: String): Observable<ConfigurationBeanR>

    @GET("configuration")
    fun getMovieDetails(@Query("api_key") apiKey: String): Observable<MovieDetailsBeanR>
}
