package com.cloudwalker.demo.presentation.ui.searchmovie.transformer

import com.cloudwalker.demo.domain.modules.searchmovie.beans.SearchMoviesBeanQ
import com.cloudwalker.demo.domain.modules.searchmovie.beans.SearchMoviesBeanR
import com.cloudwalker.demo.presentation.ui.searchmovie.model.SearchMoviesModelQ
import com.cloudwalker.demo.presentation.ui.searchmovie.model.SearchMoviesModelR
import com.cloudwalker.utils.Utils
import com.google.gson.Gson
import javax.inject.Inject

private val TAG = SearchMoviesConverter::class.java.simpleName

class SearchMoviesConverter
@Inject
constructor(private val gson: Gson, private val utils: Utils) {
    // Convert Mobile Domain Bean to Presentation Model
    fun transformBean(searchMoviesBeanR: SearchMoviesBeanR): SearchMoviesModelR {
        utils.showLog(TAG, "transformBean($searchMoviesBeanR)")
        return gson.fromJson(gson.toJson(searchMoviesBeanR), SearchMoviesModelR::class.java)
    }

    // Convert Mobile Presentation Model to Domain Bean
    fun transformModel(searchMoviesModelQ: SearchMoviesModelQ): SearchMoviesBeanQ {
        utils.showLog(TAG, "transformModel($searchMoviesModelQ)")
        return gson.fromJson(
            gson.toJson(searchMoviesModelQ),
            SearchMoviesBeanQ::class.java
        )
    }
}