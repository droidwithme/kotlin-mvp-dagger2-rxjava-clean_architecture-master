package com.cloudwalker.demo.presentation.ui.moviedetails.presenter

import com.cloudwalker.demo.domain.exceptions.DefaultErrorBundle
import com.cloudwalker.demo.domain.exceptions.ErrorBundle
import com.cloudwalker.demo.domain.interactors.DefaultObserver
import com.cloudwalker.demo.domain.interactors.Interactor
import com.cloudwalker.demo.domain.modules.moviesdetails.beans.MovieDetailsBeanR
import com.cloudwalker.demo.domain.modules.moviesdetails.interactor.MovieDetailsInteractor
import com.cloudwalker.demo.domain.modules.popularmovies.beans.PopularMoviesBeanR
import com.cloudwalker.demo.presentation.main.exceptions.ErrorMessageFactory
import com.cloudwalker.demo.presentation.main.presenter.MainPresenter
import com.cloudwalker.demo.presentation.main.runtime.Constants
import com.cloudwalker.demo.presentation.ui.moviedetails.model.MoviesDetailsModelR
import com.cloudwalker.demo.presentation.ui.moviedetails.transformer.MoviesDetailsConverter
import com.cloudwalker.demo.presentation.ui.moviedetails.views.MovieDetailsView
import com.cloudwalker.demo.presentation.ui.moviespopular.model.PopularMoviesModelR
import com.cloudwalker.demo.presentation.ui.moviespopular.transformer.PopularMoviesConverter
import com.cloudwalker.demo.presentation.ui.moviespopular.views.PopularMovieView
import com.cloudwalker.utils.Utils
import javax.inject.Inject
import javax.inject.Named

class MoviesDetailsPresenter
@Inject
constructor(
    @Named(Constants.movieDetailsInteractor)
    private val movieDetailsInteractor: Interactor,
    private val movideDetailsConverter: MoviesDetailsConverter,
    private val utils: Utils
) : DefaultObserver(), MainPresenter {
    private lateinit var movieDetailsView: MovieDetailsView

    override fun resume() {}

    override fun pause() {}

    override fun destroy() {
        movieDetailsInteractor.dispose()
    }

    fun setView(view: MovieDetailsView) {
        movieDetailsView = view
    }

    fun getView() {
        initialize()
    }

    private fun initialize() {
        showViewLoading()
        getPopularMovies()
    }

    private fun getPopularMovies() {
        movieDetailsInteractor.execute(this)
    }

    private fun showViewLoading() {
        movieDetailsView.showLoading()
    }

    private fun hideViewLoading() {
        movieDetailsView.hideLoading()
    }

    private fun showErrorMessage(errorBundle: ErrorBundle) {
        val errorMessage = ErrorMessageFactory.create(utils, errorBundle.exception)
        movieDetailsView.showError(errorMessage)
    }

    private fun transformData(popularMoviesBeanR: Any) {
        showDataInView(movideDetailsConverter.transformBean(popularMoviesBeanR as MovieDetailsBeanR))
    }

    private fun showDataInView(moviesDetailsModelR: MoviesDetailsModelR) {
        movieDetailsView.renderData(moviesDetailsModelR)
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