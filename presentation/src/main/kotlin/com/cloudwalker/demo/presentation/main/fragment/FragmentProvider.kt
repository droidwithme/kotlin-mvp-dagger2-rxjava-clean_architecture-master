package com.cloudwalker.demo.presentation.main.fragment

import androidx.fragment.app.Fragment
import androidx.transition.AutoTransition
import com.cloudwalker.demo.presentation.main.activity.MainActivity
import com.cloudwalker.demo.presentation.ui.dashboard.fragments.DashboardFragment
import com.cloudwalker.demo.presentation.ui.landing.LandingFragment
import com.cloudwalker.demo.presentation.ui.moviedetails.fragments.MoviesDetailsFragment
import com.cloudwalker.demo.presentation.ui.movieshome.fragments.MoviesFragment
import com.cloudwalker.demo.presentation.ui.moviespopular.fragments.PopularMoviesFragment
import com.cloudwalker.demo.presentation.ui.splash.fragments.SplashFragment
import com.cloudwalker.utils.Utils


private val TAG = FragmentProvider::class.java.simpleName

/**
 * Fragment Provider To Get Fragments
 *
 * Created by Praveen on 06-08-2018.
 */
object FragmentProvider {
    /**
     * To Get Fragment
     */
    fun getFragment(mainActivity: MainActivity, fragmentEnum: FragmentEnum, utils: Utils): Fragment {
        // Finding committed fragments by TAG
        var fragment: Fragment? = findFragmentByTag(mainActivity, fragmentEnum.code, utils)
        when (fragmentEnum) {
            FragmentEnum.SPLASHSCREEN -> {
                when (fragment) {
                    null -> fragment = SplashFragment()
                    else -> fragment as SplashFragment
                }
            }
            FragmentEnum.LANDSCREEN -> {
                when (fragment) {
                    null -> fragment = LandingFragment()
                    else -> fragment as LandingFragment
                }
            }
            FragmentEnum.MOVIESHOME -> {
                when (fragment) {
                    null -> fragment = MoviesFragment()
                    else -> fragment as MoviesFragment
                }
            }
            FragmentEnum.MOVIEDETAILS -> {
                when (fragment) {
                    null -> fragment = MoviesDetailsFragment()
                    else -> fragment as MoviesDetailsFragment
                }
            }

            FragmentEnum.POPULARMOVIESSCREEN -> {
                when (fragment) {
                    null -> fragment = PopularMoviesFragment()
                    else -> fragment as MoviesFragment
                }
            }

            FragmentEnum.NOWPLAYINGMOVIESSCREEN -> {
                when (fragment) {
                    null -> fragment = PopularMoviesFragment()
                    else -> fragment as MoviesFragment
                }
            }

            FragmentEnum.SEARCHMOVIES -> { // TODO
                when (fragment) {
                    null -> fragment = PopularMoviesFragment()
                    else -> fragment as MoviesFragment
                }
            }
        }
        utils.showLog(TAG, "getFragment($mainActivity, $fragmentEnum): $fragment")
        fragment.enterTransition = AutoTransition()
        return fragment
    }

    private fun findFragmentByTag(mainActivity: MainActivity, tag: String, utils: Utils): Fragment? {
        utils.showLog(TAG, "findFragmentByTag($mainActivity, $tag)")
        return mainActivity.supportFragmentManager.findFragmentByTag(tag)
    }
}