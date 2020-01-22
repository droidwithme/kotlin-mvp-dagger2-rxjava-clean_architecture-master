package com.cloudwalker.demo.presentation.ui.searchmovie.fragments

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
import com.cloudwalker.demo.presentation.ui.searchmovie.adapter.AdapterSearchMovies
import com.cloudwalker.demo.presentation.ui.searchmovie.dagger.component.SearchMoviesComponent
import com.cloudwalker.demo.presentation.ui.searchmovie.dagger.module.SearchMoviesModule
import com.cloudwalker.demo.presentation.ui.searchmovie.model.SearchMoviesModelQ
import com.cloudwalker.demo.presentation.ui.searchmovie.model.SearchMoviesModelR
import com.cloudwalker.demo.presentation.ui.searchmovie.presenter.SearchMoviesPresenter
import com.cloudwalker.demo.presentation.ui.searchmovie.views.SearchMovieView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_popular_movies.recyclerView
import kotlinx.android.synthetic.main.fragment_search_movie.*
import kotlinx.android.synthetic.main.view_toolbar.view.*
import javax.inject.Inject


private val TAG: String = SearchMoviesFragment::class.java.simpleName

class SearchMoviesFragment : MainFragment(), SearchMovieView, OnMovieClickListener, OnLoadMore,
    View.OnClickListener {
    override fun onClick(v: View?) {
        searchKeyWord = itemSearchBox.editText!!.text.toString()
        initialize(
            SearchMoviesModelQ(
                Variables.apiKey,
                "en-US",
                searchKeyWord,
                pageNumber.toString()
            )
        )
    }

    override fun loadMore() {

        pageNumber++
        if (totalPage >= pageNumber) {
            initialize(
                SearchMoviesModelQ(
                    Variables.apiKey,
                    "HI",
                    searchKeyWord, pageNumber.toString()
                )
            )
        } else {
            utils.showToast("No more page to show.")
        }

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

    //Get next page data
    private var pageNumber = 1
    private var totalPage = 0
    private var searchKeyWord = ""
    private lateinit var layoutManager: LinearLayoutManager
    private lateinit var adapterSearchMovie: AdapterSearchMovies
    // To Initialize Mobile Component For Injection
    private val searchMovieComponent: SearchMoviesComponent?
        get() = (EnumInjector.INSTANCE.getSearchMovieComponent(
            applicationComponent,
            searchMoviesModule
        ))


    // Mobile module to be initialize after user interaction
    private lateinit var searchMoviesModule: SearchMoviesModule

    // To be injected by Dagger when injecting component.
    @Inject
    lateinit var searchMoviesPresenter: SearchMoviesPresenter

    // View to render UI
    private var searchMovieView: View? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        if (searchMovieView != null) {
            val parent = searchMovieView!!.parent as ViewGroup
            parent.removeView(searchMovieView)
            utils.showLog(TAG, "searchMovieView != Null $searchMovieView")
        }
        try {
            searchMovieView = inflater.inflate(R.layout.fragment_search_movie, container, false)
            utils.showLog(TAG, "searchMovieView == Null $searchMovieView")
        } catch (exception: InflateException) {
            utils.showLog(TAG, "Exception $exception")
        }
        mainActivity.appbarLayout.visibility = View.VISIBLE
        mainActivity.appbarLayout.toolBarTitle.text = "Search Movie"

        return searchMovieView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        utils.showLog(TAG, "onViewCreated()")
        itemSearchBtn.setOnClickListener(this)
        setupRecyclerView()
    }

    private fun setupRecyclerView() {
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
        utils.showLog(TAG, "onPause()")
        if (::searchMoviesPresenter.isInitialized)
            searchMoviesPresenter.destroy()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        utils.showLog(TAG, "onDestroyView")
        searchMovieView = null
    }

    private fun initialize(searchMoviesModelQ: SearchMoviesModelQ) {
        utils.showLog(TAG, "initialize()")
        initializeNowPlayingModule(searchMoviesModelQ)
    }

    private fun initializeNowPlayingModule(searchMoviesModelQ: SearchMoviesModelQ) {
        utils.showLog(TAG, "initializeModule($searchMoviesModelQ)") // Logging
        searchMoviesModule = SearchMoviesModule(
            searchMoviesModelQ, gson,
            utils
        ) // Initializing Module
        initializeInjector() // Calling method to Inject Dependencies (i.e Presenter)
    }

    private fun initializeInjector() {
        utils.showLog(TAG, "initializeInjector()") // Logging
        searchMovieComponent?.inject(this) // Injecting Dependencies
        initializePresenter() // Calling method to Initialize Presenter
    }

    private fun initializePresenter() {
        utils.showLog(TAG, "initializePresenter()") // Logging
        this.searchMoviesPresenter.setView(this) // Initializing Presenter
        this.searchMoviesPresenter.getView()
    }

    override fun renderData(searchMoviesModelR: SearchMoviesModelR) {
        utils.showLog(TAG, "renderData($searchMoviesModelR)") // Logging
        totalPage = searchMoviesModelR.totalPages
        adapterSearchMovie = AdapterSearchMovies(searchMoviesModelR.results, this, this)
        recyclerView.adapter = adapterSearchMovie

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