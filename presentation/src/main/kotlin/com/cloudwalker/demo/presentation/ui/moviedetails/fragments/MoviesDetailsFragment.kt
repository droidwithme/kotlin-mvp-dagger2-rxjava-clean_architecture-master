package com.cloudwalker.demo.presentation.ui.moviedetails.fragments

import android.content.Context
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.cloudwalker.demo.presentation.R
import com.cloudwalker.demo.presentation.main.dagger.injector.EnumInjector
import com.cloudwalker.demo.presentation.main.fragment.MainFragment
import com.cloudwalker.demo.presentation.main.runtime.Variables
import com.cloudwalker.demo.presentation.ui.moviespopular.adapter.AdapterPopularMovies
import com.cloudwalker.demo.presentation.ui.moviespopular.dagger.component.PopularMoviesComponent
import com.cloudwalker.demo.presentation.ui.moviespopular.dagger.module.PopularMoviesModule
import com.cloudwalker.demo.presentation.ui.moviespopular.model.PopularMoviesModelQ
import com.cloudwalker.demo.presentation.ui.moviespopular.model.PopularMoviesModelR
import com.cloudwalker.demo.presentation.ui.moviespopular.presenter.PopularMoviesPresenter
import com.cloudwalker.demo.presentation.ui.moviespopular.views.PopularMovieView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_popular_movies.*
import kotlinx.android.synthetic.main.view_toolbar.view.*
import javax.inject.Inject
import androidx.recyclerview.widget.DividerItemDecoration
import com.cloudwalker.demo.presentation.ui.moviedetails.dagger.component.MoviesDetailsComponent
import com.cloudwalker.demo.presentation.ui.moviedetails.dagger.module.MoviesDetailsModule
import com.cloudwalker.demo.presentation.ui.moviedetails.model.MoviesDetailsModelQ
import com.cloudwalker.demo.presentation.ui.moviedetails.model.MoviesDetailsModelR
import com.cloudwalker.demo.presentation.ui.moviedetails.presenter.MoviesDetailsPresenter
import com.cloudwalker.demo.presentation.ui.moviedetails.views.MovieDetailsView
import com.google.android.youtube.player.YouTubeInitializationResult
import com.google.android.youtube.player.YouTubePlayer
import com.google.android.youtube.player.YouTubePlayerFragment
import com.google.android.youtube.player.YouTubePlayerSupportFragment


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




        initialize(PopularMoviesModelQ(Variables.apiKey, "HI"))
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

    private fun initialize(popularMoviesModelQ: PopularMoviesModelQ) {
        utils.showLog(TAG, "initialize()")
        initializeLoginModule(popularMoviesModelQ)
    }

    private fun initializeLoginModule(popularMoviesModelQ: PopularMoviesModelQ) {
        utils.showLog(TAG, "initializeModule($popularMoviesModelQ)") // Logging
        moviesDetailsModule = MoviesDetailsModule(
            MoviesDetailsModelQ(Variables.apiKey, "HI"), gson,
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


}

open class VideoFragment : YouTubePlayerSupportFragment() {

    private fun init(time: Int) {
        initialize(DEVELOPER_ANDROID_KEY, object : YouTubePlayer.OnInitializedListener {

            override fun onInitializationFailure(
                arg0: YouTubePlayer.Provider,
                arg1: YouTubeInitializationResult
            ) {
            }

            override fun onInitializationSuccess(
                provider: YouTubePlayer.Provider,
                player: YouTubePlayer,
                wasRestored: Boolean
            ) {
                player.setPlayerStyle(YouTubePlayer.PlayerStyle.DEFAULT)

                if (!wasRestored) {
                    player.loadVideo("2Oy4HpUJSgE", time)
                }
            }
        })
    }

    companion object {
        val DEVELOPER_ANDROID_KEY = Variables.youtubeApiKey

        fun newInstance(url: String): VideoFragment {
            val videoFragment = VideoFragment()
            val bundle = Bundle()
            bundle.putString("url", url)
            videoFragment.init(0)
            return videoFragment
        }
    }
}