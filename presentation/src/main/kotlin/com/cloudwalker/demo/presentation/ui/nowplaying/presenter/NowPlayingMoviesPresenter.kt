package com.cloudwalker.demo.presentation.ui.nowplaying.presenter

import com.cloudwalker.demo.domain.exceptions.DefaultErrorBundle
import com.cloudwalker.demo.domain.exceptions.ErrorBundle
import com.cloudwalker.demo.domain.interactors.DefaultObserver
import com.cloudwalker.demo.domain.interactors.Interactor
import com.cloudwalker.demo.domain.modules.nowplaying.beans.NowPlayingMoviesBeanR
import com.cloudwalker.demo.presentation.main.exceptions.ErrorMessageFactory
import com.cloudwalker.demo.presentation.main.presenter.MainPresenter
import com.cloudwalker.demo.presentation.main.runtime.Constants
import com.cloudwalker.demo.presentation.ui.moviespopular.model.PopularMoviesModelR
import com.cloudwalker.demo.presentation.ui.moviespopular.transformer.PopularMoviesConverter
import com.cloudwalker.demo.presentation.ui.nowplaying.model.NowPlayingMoviesModelR
import com.cloudwalker.demo.presentation.ui.nowplaying.transformer.NowPlayingMoviesConverter
import com.cloudwalker.demo.presentation.ui.nowplaying.views.NowPlayingMovieView
import com.cloudwalker.utils.Utils
import javax.inject.Inject
import javax.inject.Named

class NowPlayingMoviesPresenter
@Inject
constructor(
    @Named(Constants.nowPlayingMoviesInteractor)
    private val nowPlayingMoviesInteractor: Interactor,
    private val nowPlayingConverter: NowPlayingMoviesConverter,
    private val utils: Utils
) : DefaultObserver(), MainPresenter {
    private lateinit var nowPlayingMovie: NowPlayingMovieView

    override fun resume() {}

    override fun pause() {}

    override fun destroy() {
        nowPlayingMoviesInteractor.dispose()
    }

    fun setView(view: NowPlayingMovieView) {
        nowPlayingMovie = view
    }

    fun getView() {
        initialize()
    }

    private fun initialize() {
        showViewLoading()
        getNowPlayingMovies()
    }

    private fun getNowPlayingMovies() {
        nowPlayingMoviesInteractor.execute(this)
    }

    private fun showViewLoading() {
        nowPlayingMovie.showLoading()
    }

    private fun hideViewLoading() {
        nowPlayingMovie.hideLoading()
    }

    private fun showErrorMessage(errorBundle: ErrorBundle) {
        val errorMessage = ErrorMessageFactory.create(utils, errorBundle.exception)
        nowPlayingMovie.showError(errorMessage)
    }

    private fun transformData(nowPlayingMoviesBeanR: Any) {
        showDataInView(nowPlayingConverter.transformBean(nowPlayingMoviesBeanR as NowPlayingMoviesBeanR))
    }

    private fun showDataInView(nowPlayingMoviesModelR: NowPlayingMoviesModelR) {
        nowPlayingMovie.renderData(nowPlayingMoviesModelR)
    }

    override fun onNext(any: Any) {
        transformData(any)
    }

    override fun onComplete() {
        hideViewLoading()
    }

    override fun onError(exception: Throwable) {
        hideViewLoading()
        showErrorMessage(DefaultErrorBundle(exception as Exception))
    }
}