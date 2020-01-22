package com.cloudwalker.demo.presentation.ui.moviedetails.fragments

import android.content.Context
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import com.cloudwalker.demo.presentation.R
import com.cloudwalker.demo.presentation.main.dagger.injector.EnumInjector
import com.cloudwalker.demo.presentation.main.fragment.MainFragment
import com.cloudwalker.demo.presentation.main.runtime.Variables
import com.cloudwalker.demo.presentation.ui.moviedetails.dagger.component.MoviesDetailsComponent
import com.cloudwalker.demo.presentation.ui.moviedetails.dagger.module.MoviesDetailsModule
import com.cloudwalker.demo.presentation.ui.moviedetails.model.MoviesDetailsModelQ
import com.cloudwalker.demo.presentation.ui.moviedetails.model.MoviesDetailsModelR
import com.cloudwalker.demo.presentation.ui.moviedetails.presenter.MoviesDetailsPresenter
import com.cloudwalker.demo.presentation.ui.moviedetails.views.MovieDetailsView
import io.reactivex.disposables.Disposable
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.view_toolbar.view.*
import javax.inject.Inject
import com.cloudwalker.demo.presentation.ui.commons.Result

private val TAG: String = MoviesDetailsFragment::class.java.simpleName

class MoviesDetailsFragment : MainFragment(),
    MovieDetailsView {
    // To Initialize Mobile Component For Injection
    private val moviesDetailsComponent: MoviesDetailsComponent?
        get() = (EnumInjector.INSTANCE.getMovieDetailsComponent(
            applicationComponent,
            moviesDetailsModule
        ))


    // Mobile module to be initialize after user interaction
    private lateinit var moviesDetailsModule: MoviesDetailsModule

    // To be injected by Dagger when injecting component.
    @Inject
    lateinit var moviesDetailsPresenter: MoviesDetailsPresenter

    // View to render UI
    private var movieView: View? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        if (movieView != null) {
            val parent = movieView!!.parent as ViewGroup
            parent.removeView(movieView)
            utils.showLog(TAG, "movieView != Null $movieView")
        }
        try {
            movieView = inflater.inflate(R.layout.fragment_movie_details, container, false)
            utils.showLog(TAG, "movieView == Null $movieView")
        } catch (exception: InflateException) {
            utils.showLog(TAG, "Exception $exception")
        }
        mainActivity.appbarLayout.visibility = View.VISIBLE
        mainActivity.appbarLayout.toolBarTitle.text = "Movie Details"

        return movieView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        utils.showLog(TAG, "onViewCreated()")
        val movie = gson.fromJson<Result>(arguments!!.getString("data"), Result::class.java)
        mainActivity.appbarLayout.toolBarTitle.text = movie.title
        initialize(MoviesDetailsModelQ(Variables.apiKey, movie.id.toString()))
    }

    override fun onResume() {
        super.onResume()
        utils.showLog(TAG, "onResume()")
    }

    override fun onPause() {
        super.onPause()
        utils.showLog(TAG, "onPause()")
        if (::moviesDetailsPresenter.isInitialized)
            moviesDetailsPresenter.destroy()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        utils.showLog(TAG, "onDestroyView")
        movieView = null
    }

    private fun initialize(moviesDetailsModelQ: MoviesDetailsModelQ) {
        utils.showLog(TAG, "initialize()")
        initializeLoginModule(moviesDetailsModelQ)
    }

    private fun initializeLoginModule(moviesDetailsModelQ: MoviesDetailsModelQ) {
        utils.showLog(TAG, "initializeModule($moviesDetailsModelQ)") // Logging
        moviesDetailsModule = MoviesDetailsModule(
            moviesDetailsModelQ, gson,
            utils
        ) // Initializing Module
        initializeInjector() // Calling method to Inject Dependencies (i.e Presenter)
    }

    private fun initializeInjector() {
        utils.showLog(TAG, "initializeInjector()") // Logging
        moviesDetailsComponent?.inject(this) // Injecting Dependencies
        initializePresenter() // Calling method to Initialize Presenter
    }

    private fun initializePresenter() {
        utils.showLog(TAG, "initializePresenter()") // Logging
        this.moviesDetailsPresenter.setView(this) // Initializing Presenter
        this.moviesDetailsPresenter.getView()
    }

    override fun renderData(moviesDetailsModelR: MoviesDetailsModelR) {
        utils.showLog(TAG, "renderData($moviesDetailsModelR)") // Logging

        openFragment(
            DetailsFragment().newInstance(gson.toJson(moviesDetailsModelR)),
            R.id.movieDetailsFragment
        )
        openFragment(
            YoutubeFragment().newInstance(
                moviesDetailsModelR.trailer,
                moviesDetailsModelR.title,
                moviesDetailsModelR.tagline
            ), R.id.youtubeplayerfragment
        )


    }

    override fun showLoading() {
        utils.showLog(TAG, "showLoading() method called")
        mainActivity.showLoading()
    }

    override fun hideLoading() {
        utils.showLog(TAG, "hideLoading() method called")
        mainActivity.hideLoading()
    }

    override fun showError(message: String) {
        utils.showLog(TAG, "showError($message) method called")
        mainActivity.showError(message)
    }

    override fun context(): Context {
        utils.showLog(TAG, "context() method called")
        return mainActivity.applicationContext
    }

    private fun openFragment(fragment: Fragment, layoutRes: Int) {
        val transaction = childFragmentManager.beginTransaction()
        transaction.replace(layoutRes, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }

}