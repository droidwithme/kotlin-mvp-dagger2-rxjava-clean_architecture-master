package com.cloudwalker.demo.presentation.ui.moviespopular.fragments

import android.content.Context
import android.os.Bundle
import android.view.*
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
import com.cloudwalker.demo.presentation.main.fragment.FragmentEnum
import com.cloudwalker.demo.presentation.ui.moviespopular.adapter.OnMovieClickListener
import com.cloudwalker.demo.presentation.ui.moviespopular.model.Result


private val TAG: String = PopularMoviesFragment::class.java.simpleName

class PopularMoviesFragment : MainFragment(),
    PopularMovieView, OnMovieClickListener {
    override fun onMovieSelected(movie: Result) {
        mainActivity.replaceFragment(FragmentEnum.MOVIEDETAILS, null, null)
    }

    // To Initialize Mobile Component For Injection
    private val popularMoviesComponent: PopularMoviesComponent?
        get() = (EnumInjector.INSTANCE.getPopularMovieComponent(
            applicationComponent,
            popularMoviesModule
        ))

    // Mobile module to be initialize after user interaction
    private lateinit var popularMoviesModule: PopularMoviesModule

    // To be injected by Dagger when injecting component.
    @Inject
    lateinit var popularMoviesPresenter: PopularMoviesPresenter

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
            movieView = inflater.inflate(R.layout.fragment_popular_movies, container, false)
            utils.showLog(TAG, "movieView == Null $movieView")
        } catch (exception: InflateException) {
            utils.showLog(TAG, "Exception $exception")
        }
        mainActivity.appbarLayout.visibility = View.VISIBLE
        mainActivity.appbarLayout.toolBarTitle.text = "Popular Movies"
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
        if (::popularMoviesPresenter.isInitialized)
            popularMoviesPresenter.destroy()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        utils.showLog(TAG, "onDestroyView")
        movieView = null
        mainActivity.window.clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN)
        mainActivity.window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
    }

    private fun initialize(popularMoviesModelQ: PopularMoviesModelQ) {
        utils.showLog(TAG, "initialize()")
        initializeLoginModule(popularMoviesModelQ)
    }

    private fun initializeLoginModule(popularMoviesModelQ: PopularMoviesModelQ) {
        utils.showLog(TAG, "initializeModule($popularMoviesModelQ)") // Logging
        popularMoviesModule = PopularMoviesModule(
            PopularMoviesModelQ(Variables.apiKey, "HI"), gson,
            utils
        ) // Initializing Module
        initializeInjector() // Calling method to Inject Dependencies (i.e Presenter)
    }

    private fun initializeInjector() {
        utils.showLog(TAG, "initializeInjector()") // Logging
        popularMoviesComponent?.inject(this) // Injecting Dependencies
        initializePresenter() // Calling method to Initialize Presenter
    }

    private fun initializePresenter() {
        utils.showLog(TAG, "initializePresenter()") // Logging
        this.popularMoviesPresenter.setView(this) // Initializing Presenter
        this.popularMoviesPresenter.getView()
    }

    override fun renderData(popularMoviesModelR: PopularMoviesModelR) {
        utils.showLog(TAG, "renderData($popularMoviesModelR)") // Logging

        val layoutManager = LinearLayoutManager(context)
        val dividerItemDecoration =
            DividerItemDecoration(recyclerView.context, layoutManager.orientation)
        recyclerView.addItemDecoration(dividerItemDecoration)
        recyclerView.layoutManager = layoutManager
        val adapterPopular = AdapterPopularMovies(popularMoviesModelR.results, this)
        recyclerView.adapter = adapterPopular
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