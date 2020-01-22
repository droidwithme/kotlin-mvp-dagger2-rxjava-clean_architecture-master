package com.cloudwalker.demo.presentation.ui.moviespopular.fragments

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.view.InflateException
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.cloudwalker.demo.presentation.R
import com.cloudwalker.demo.presentation.main.dagger.injector.EnumInjector
import com.cloudwalker.demo.presentation.main.fragment.FragmentEnum
import com.cloudwalker.demo.presentation.main.fragment.MainFragment
import com.cloudwalker.demo.presentation.main.runtime.Variables
import com.cloudwalker.demo.presentation.ui.commons.OnLoadMore
import com.cloudwalker.demo.presentation.ui.commons.OnMovieClickListener
import com.cloudwalker.demo.presentation.ui.commons.Result
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


private val TAG: String = PopularMoviesFragment::class.java.simpleName

/**
 * This fragment will show listing of popular movies
 * Consists simple recyclerview
 */
class PopularMoviesFragment : MainFragment(),
    PopularMovieView, OnMovieClickListener, OnLoadMore {


    private lateinit var layoutManager: LinearLayoutManager // to provide layout manger to recyclerview
    private lateinit var adapterPopular: AdapterPopularMovies // Adapter declaration
    private var pageNumber = 1 // Page number holds reference of the current page

    // To Initialize popular Component For Injection
    private val popularMoviesComponent: PopularMoviesComponent?
        get() = (EnumInjector.INSTANCE.getPopularMovieComponent(
            applicationComponent,
            popularMoviesModule
        ))

    private lateinit var popularMoviesModule: PopularMoviesModule

    // To be injected by Dagger when injecting component.
    @Inject
    lateinit var popularMoviesPresenter: PopularMoviesPresenter

    // View to render UI
    private var popularMoviesView: View? = null

    @SuppressLint("SetTextI18n")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        if (popularMoviesView != null) {
            val parent = popularMoviesView!!.parent as ViewGroup
            parent.removeView(popularMoviesView)
            utils.showLog(TAG, "popularMoviesView != Null $popularMoviesView")
        }
        try {
            popularMoviesView = inflater.inflate(R.layout.fragment_popular_movies, container, false)
            utils.showLog(TAG, "popularMoviesView == Null $popularMoviesView")
        } catch (exception: InflateException) {
            utils.showLog(TAG, "Exception $exception")
        }
        mainActivity.appbarLayout.visibility = View.VISIBLE
        mainActivity.appbarLayout.toolBarTitle.text = "Popular Movies"
        return popularMoviesView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        utils.showLog(TAG, "onViewCreated()")
        setUpRecyclerView()
        initialize(PopularMoviesModelQ(Variables.apiKey, "en-US", pageNumber.toString()))
    }

    private fun setUpRecyclerView() { // applying the layout managers and item decoration
        layoutManager = LinearLayoutManager(context)
        val dividerItemDecoration =
            DividerItemDecoration(recyclerView.context, layoutManager.orientation)
        recyclerView.addItemDecoration(dividerItemDecoration)
        recyclerView.layoutManager = layoutManager
    }

    override fun onResume() {
        super.onResume()
        utils.showLog(TAG, "onResume()")
    }

    override fun onPause() {
        super.onPause()
        utils.showLog(TAG, "onPause()") //presenter needs to be destroy in onPause()
        if (::popularMoviesPresenter.isInitialized)
            popularMoviesPresenter.destroy()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        utils.showLog(TAG, "onDestroyView")
        popularMoviesView = null
    }

    private fun initialize(popularMoviesModelQ: PopularMoviesModelQ) {
        utils.showLog(TAG, "initialize()")
        initializePopularMoviesModule(popularMoviesModelQ)
    }

    private fun initializePopularMoviesModule(popularMoviesModelQ: PopularMoviesModelQ) {
        utils.showLog(TAG, "initializeModule($popularMoviesModelQ)") // Logging
        popularMoviesModule = PopularMoviesModule(
            PopularMoviesModelQ(Variables.apiKey, "HI", pageNumber.toString()), gson,
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

    /**
     * This method invokes when presenter gets the response
     */
    override fun renderData(popularMoviesModelR: PopularMoviesModelR) {
        utils.showLog(TAG, "renderData($popularMoviesModelR)") // Logging
        if (pageNumber == 1) {
            adapterPopular = AdapterPopularMovies(popularMoviesModelR.results, this, this)
            recyclerView.adapter = adapterPopular
        } else {
            adapterPopular.addMoreMovies(popularMoviesModelR.results)
        }
        pageNumber = popularMoviesModelR.page

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


    override fun loadMore() {
        pageNumber++
        initialize(PopularMoviesModelQ(Variables.apiKey, "HI", pageNumber.toString()))
    }

    override fun onMovieSelected(movie: Result) {
        val bundle = Bundle()
        bundle.putString("data", gson.toJson(movie))
        mainActivity.replaceFragment(
            FragmentEnum.MOVIEDETAILS,
            null,
            null,
            bundle
        )
    }
}