package com.cloudwalker.demo.presentation.ui.nowplaying.transformer

import com.cloudwalker.demo.domain.modules.nowplaying.beans.NowPlayingMoviesBeanQ
import com.cloudwalker.demo.domain.modules.nowplaying.beans.NowPlayingMoviesBeanR
import com.cloudwalker.demo.presentation.ui.nowplaying.model.NowPlayingMoviesModelQ
import com.cloudwalker.demo.presentation.ui.nowplaying.model.NowPlayingMoviesModelR
import com.cloudwalker.utils.Utils
import com.google.gson.Gson
import javax.inject.Inject

private val TAG = NowPlayingMoviesConverter::class.java.simpleName

class NowPlayingMoviesConverter
@Inject
constructor(private val gson: Gson, private val utils: Utils) {
    // Convert Mobile Domain Bean to Presentation Model
    fun transformBean(nowPlayingMoviesBeanR: NowPlayingMoviesBeanR): NowPlayingMoviesModelR {
        utils.showLog(TAG, "transformBean($nowPlayingMoviesBeanR)")
        return gson.fromJson(gson.toJson(nowPlayingMoviesBeanR), NowPlayingMoviesModelR::class.java)
    }

    // Convert Mobile Presentation Model to Domain Bean
    fun transformModel(nowPlayingMoviesModelQ: NowPlayingMoviesModelQ): NowPlayingMoviesBeanQ {
        utils.showLog(TAG, "transformModel($nowPlayingMoviesModelQ)")
        return gson.fromJson(
            gson.toJson(nowPlayingMoviesModelQ),
            NowPlayingMoviesBeanQ::class.java
        )
    }
}