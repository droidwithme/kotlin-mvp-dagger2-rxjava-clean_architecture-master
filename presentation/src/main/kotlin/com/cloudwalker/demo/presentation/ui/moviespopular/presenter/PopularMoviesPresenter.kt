package com.cloudwalker.demo.presentation.ui.moviespopular.presenter

import com.cloudwalker.demo.domain.exceptions.DefaultErrorBundle
import com.cloudwalker.demo.domain.exceptions.ErrorBundle
import com.cloudwalker.demo.domain.interactors.DefaultObserver
import com.cloudwalker.demo.domain.interactors.Interactor
import com.cloudwalker.demo.domain.modules.popularmovies.beans.PopularMoviesBeanR
import com.cloudwalker.demo.presentation.main.exceptions.ErrorMessageFactory
import com.cloudwalker.demo.presentation.main.presenter.MainPresenter
import com.cloudwalker.demo.presentation.main.runtime.Constants
import com.cloudwalker.demo.presentation.ui.moviespopular.model.PopularMoviesModelR
import com.cloudwalker.demo.presentation.ui.moviespopular.transformer.PopularMoviesConverter
import com.cloudwalker.demo.presentation.ui.moviespopular.views.PopularMovieView
import com.cloudwalker.utils.Utils
import javax.inject.Inject
import javax.inject.Named

/**
 * This is the implementation  of presenter
 *  this is observing the PopularMoviesModelR as response
 */
class PopularMoviesPresenter
@Inject
constructor(
    @Named(Constants.popularMoviesInteractor)
    private val popularMoviesInteractor: Interactor,
    private val popularMoviesConverter: PopularMoviesConverter,
    private val utils: Utils
) : DefaultObserver(), MainPresenter {
    private lateinit var popularMovieView: PopularMovieView

    override fun resume() {}

    override fun pause() {}

    override fun destroy() {
        popularMoviesInteractor.dispose()
    }

    fun setView(view: PopularMovieView) {
        popularMovieView = view
    }

    fun getView() {
        initialize()
    }

    private fun initialize() {
        showViewLoading()
        getPopularMovies()
    }

    private fun getPopularMovies() {
        popularMoviesInteractor.execute(this)
    }

    private fun showViewLoading() {
        popularMovieView.showLoading()
    }

    private fun hideViewLoading() {
        popularMovieView.hideLoading()
    }

    private fun showErrorMessage(errorBundle: ErrorBundle) {
        val errorMessage = ErrorMessageFactory.create(utils, errorBundle.exception)
        popularMovieView.showError(errorMessage)
    }

    private fun transformData(popularMoviesBeanR: Any) {
        showDataInView(popularMoviesConverter.transformBean(popularMoviesBeanR as PopularMoviesBeanR))
    }

    private fun showDataInView(popularMoviesModelR: PopularMoviesModelR) {
        popularMovieView.renderData(popularMoviesModelR)
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