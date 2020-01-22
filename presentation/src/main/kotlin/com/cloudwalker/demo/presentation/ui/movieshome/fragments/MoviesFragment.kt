package com.cloudwalker.demo.presentation.ui.movieshome.fragments

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import com.cloudwalker.demo.presentation.R
import com.cloudwalker.demo.presentation.main.fragment.MainFragment
import com.cloudwalker.demo.presentation.ui.moviespopular.fragments.PopularMoviesFragment
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_movies.*
import me.ibrahimsn.lib.OnItemReselectedListener
import me.ibrahimsn.lib.OnItemSelectedListener


private val TAG: String = MoviesFragment::class.java.simpleName

class MoviesFragment : MainFragment() {


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
            movieView = inflater.inflate(R.layout.fragment_movies, container, false)
            utils.showLog(TAG, "movieView == Null $movieView")
        } catch (exception: InflateException) {
            utils.showLog(TAG, "Exception $exception")
        }
        mainActivity.appbarLayout.visibility = View.GONE
        return movieView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        openFragment(PopularMoviesFragment())

        bottomBar.onItemSelected = {
            utils.showLog(TAG, "Selected position $it")
            when (it) {
                0 -> {
                    openFragment(PopularMoviesFragment())
                }
                1 -> {
                    openFragment(PopularMoviesFragment())
                }
                2 -> {
                    openFragment(PopularMoviesFragment())
                }
            }
        }

        bottomBar.onItemReselected = {

        }
    }

    override fun onResume() {
        super.onResume()
        utils.showLog(TAG, "onResume()")
    }

    override fun onPause() {
        super.onPause()
        utils.showLog(TAG, "onPause()")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        utils.showLog(TAG, "onDestroyView")
        movieView = null
        mainActivity.window.clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN)
        mainActivity.window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
    }

    private fun openFragment(fragment: Fragment) {
        val transaction = childFragmentManager.beginTransaction()
        transaction.replace(R.id.container, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }


}