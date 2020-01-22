package com.cloudwalker.demo.presentation.ui.nowplaying.fragments

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
import com.cloudwalker.demo.presentation.ui.nowplaying.adapter.AdapterNowPlayingMovies
import com.cloudwalker.demo.presentation.ui.nowplaying.dagger.component.NowPlayingMoviesComponent
import com.cloudwalker.demo.presentation.ui.nowplaying.dagger.module.NowPlayingMoviesModule
import com.cloudwalker.demo.presentation.ui.nowplaying.model.NowPlayingMoviesModelQ
import com.cloudwalker.demo.presentation.ui.nowplaying.model.NowPlayingMoviesModelR
import com.cloudwalker.demo.presentation.ui.nowplaying.presenter.NowPlayingMoviesPresenter
import com.cloudwalker.demo.presentation.ui.nowplaying.views.NowPlayingMovieView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_popular_movies.*
import kotlinx.android.synthetic.main.view_toolbar.view.*
import javax.inject.Inject


private val TAG: String = NowPlayingMoviesFragment::class.java.simpleName

class NowPlayingMoviesFragment : MainFragment(),
    NowPlayingMovieView, OnMovieClickListener, OnLoadMore {
    override fun loadMore() {
        pageNumber++
        initialize(
            NowPlayingMoviesModelQ(
                Variables.apiKey,
                "HI",
                pageNumber.toString()
            )
        )
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

    private lateinit var layoutManager: LinearLayoutManager
    private lateinit var adapterPopular: AdapterNowPlayingMovies
    // To Initialize Mobile Component For Injection
    private val nowPlayingMoviesComponent: NowPlayingMoviesComponent?
        get() = (EnumInjector.INSTANCE.getNowPlayingMovieComponent(
            applicationComponent,
            nowPlayingMoviesModule
        ))

    //Get next page data
    private var pageNumber = 1
    // Mobile module to be initialize after user interaction
    private lateinit var nowPlayingMoviesModule: NowPlayingMoviesModule

    // To be injected by Dagger when injecting component.
    @Inject
    lateinit var nowPlayingMoviesPresenter: NowPlayingMoviesPresenter

    // View to render UI
    private var nowPlayingView: View? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        if (nowPlayingView != null) {
            val parent = nowPlayingView!!.parent as ViewGroup
            parent.removeView(nowPlayingView)
            utils.showLog(TAG, "nowPlayingView != Null $nowPlayingView")
        }
        try {
            nowPlayingView = inflater.inflate(R.layout.fragment_popular_movies, container, false)
            utils.showLog(TAG, "nowPlayingView == Null $nowPlayingView")
        } catch (exception: InflateException) {
            utils.showLog(TAG, "Exception $exception")
        }
        mainActivity.appbarLayout.visibility = View.VISIBLE
        mainActivity.appbarLayout.toolBarTitle.text = "Now Playing"
        return nowPlayingView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        utils.showLog(TAG, "onViewCreated()")
        setupRecyclerView()
        initialize(NowPlayingMoviesModelQ(Variables.apiKey, "HI", pageNumber.toString()))
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
        if (::nowPlayingMoviesPresenter.isInitialized)
            nowPlayingMoviesPresenter.destroy()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        utils.showLog(TAG, "onDestroyView")
        nowPlayingView = null
    }

    private fun initialize(nowPlayingMoviesModelQ: NowPlayingMoviesModelQ) {
        utils.showLog(TAG, "initialize()")
        initializeNowPlayingModule(nowPlayingMoviesModelQ)
    }

    private fun initializeNowPlayingModule(nowPlayingMoviesModelQ: NowPlayingMoviesModelQ) {
        utils.showLog(TAG, "initializeModule($nowPlayingMoviesModelQ)") // Logging
        nowPlayingMoviesModule = NowPlayingMoviesModule(
            NowPlayingMoviesModelQ(Variables.apiKey, "HI", pageNumber.toString()), gson,
            utils
        ) // Initializing Module
        initializeInjector() // Calling method to Inject Dependencies (i.e Presenter)
    }

    private fun initializeInjector() {
        utils.showLog(TAG, "initializeInjector()") // Logging
        nowPlayingMoviesComponent?.inject(this) // Injecting Dependencies
        initializePresenter() // Calling method to Initialize Presenter
    }

    private fun initializePresenter() {
        utils.showLog(TAG, "initializePresenter()") // Logging
        this.nowPlayingMoviesPresenter.setView(this) // Initializing Presenter
        this.nowPlayingMoviesPresenter.getView()
    }

    override fun renderData(nowPlayingMoviesModelR: NowPlayingMoviesModelR) {
        utils.showLog(TAG, "renderData($nowPlayingMoviesModelR)") // Logging
        if (pageNumber == 1) {
            adapterPopular = AdapterNowPlayingMovies(nowPlayingMoviesModelR.results, this, this)
            recyclerView.adapter = adapterPopular
        } else {
            adapterPopular.addMoreMovies(nowPlayingMoviesModelR.results)
        }
        pageNumber = nowPlayingMoviesModelR.page
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