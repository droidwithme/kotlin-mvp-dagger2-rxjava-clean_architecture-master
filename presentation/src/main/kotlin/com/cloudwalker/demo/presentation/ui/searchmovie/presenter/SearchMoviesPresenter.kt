package com.cloudwalker.demo.presentation.ui.searchmovie.presenter

import com.cloudwalker.demo.domain.exceptions.DefaultErrorBundle
import com.cloudwalker.demo.domain.exceptions.ErrorBundle
import com.cloudwalker.demo.domain.interactors.DefaultObserver
import com.cloudwalker.demo.domain.interactors.Interactor
import com.cloudwalker.demo.domain.modules.nowplaying.beans.NowPlayingMoviesBeanR
import com.cloudwalker.demo.domain.modules.searchmovie.beans.SearchMoviesBeanR
import com.cloudwalker.demo.presentation.main.exceptions.ErrorMessageFactory
import com.cloudwalker.demo.presentation.main.presenter.MainPresenter
import com.cloudwalker.demo.presentation.main.runtime.Constants
import com.cloudwalker.demo.presentation.ui.moviespopular.model.PopularMoviesModelR
import com.cloudwalker.demo.presentation.ui.moviespopular.transformer.PopularMoviesConverter
import com.cloudwalker.demo.presentation.ui.nowplaying.model.NowPlayingMoviesModelR
import com.cloudwalker.demo.presentation.ui.nowplaying.transformer.NowPlayingMoviesConverter
import com.cloudwalker.demo.presentation.ui.nowplaying.views.NowPlayingMovieView
import com.cloudwalker.demo.presentation.ui.searchmovie.model.SearchMoviesModelR
import com.cloudwalker.demo.presentation.ui.searchmovie.transformer.SearchMoviesConverter
import com.cloudwalker.demo.presentation.ui.searchmovie.views.SearchMovieView
import com.cloudwalker.utils.Utils
import javax.inject.Inject
import javax.inject.Named

class SearchMoviesPresenter
@Inject
constructor(
    @Named(Constants.searchMovieInteractor)
    private val searchMoviesInteractor: Interactor,
    private val searchMoviesConverter: SearchMoviesConverter,
    private val utils: Utils
) : DefaultObserver(), MainPresenter {
    private lateinit var searcMovieView: SearchMovieView

    override fun resume() {}

    override fun pause() {}

    override fun destroy() {
        searchMoviesInteractor.dispose()
    }

    fun setView(view: SearchMovieView) {
        searcMovieView = view
    }

    fun getView() {
        initialize()
    }

    private fun initialize() {
        showViewLoading()
        getNowPlayingMovies()
    }

    private fun getNowPlayingMovies() {
        searchMoviesInteractor.execute(this)
    }

    private fun showViewLoading() {
        searcMovieView.showLoading()
    }

    private fun hideViewLoading() {
        searcMovieView.hideLoading()
    }

    private fun showErrorMessage(errorBundle: ErrorBundle) {
        val errorMessage = ErrorMessageFactory.create(utils, errorBundle.exception)
        searcMovieView.showError(errorMessage)
    }

    private fun transformData(nowPlayingMoviesBeanR: Any) {
        showDataInView(searchMoviesConverter.transformBean(nowPlayingMoviesBeanR as SearchMoviesBeanR))
    }

    private fun showDataInView(searchMoviesModelR: SearchMoviesModelR) {
        searcMovieView.renderData(searchMoviesModelR)
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