package com.cloudwalker.demo.presentation.ui.dashboard.fragments

import android.content.Context
import android.os.Bundle
import android.view.InflateException
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.transition.TransitionManager
import com.cloudwalker.demo.presentation.R
import com.cloudwalker.demo.presentation.main.fragment.MainFragment
import com.cloudwalker.demo.presentation.ui.dashboard.views.DashboardView
import kotlinx.android.synthetic.main.activity_main.*

private val TAG: String = DashboardFragment::class.java.simpleName

class DashboardFragment : MainFragment(), DashboardView {
    private var dashboardView: View? = null
    private var dashboardViewContainer: ViewGroup? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        utils.showLog(TAG, "onCreateView")
        if (dashboardView != null) {
            val parent = dashboardView!!.parent as ViewGroup
            parent.removeView(dashboardView)
        }
        try {
            dashboardView = inflater.inflate(R.layout.fragment_dashboard, container, false)
            dashboardViewContainer = container

        } catch (e: InflateException) {

        }
        TransitionManager.beginDelayedTransition(mainActivity.mainLayout)
        mainActivity.appbarLayout.visibility = View.VISIBLE
        // Setting title @Toolbar
        mainActivity.title = "Dashboard"
        mainActivity.window.setBackgroundDrawable(ContextCompat.getDrawable(context!!, android.R.color.white))
        return dashboardView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        utils.showLog(TAG, "onViewCreated")
    }

    /**
     * Here We Can Play With Graphical Object As They Are Initialized In onCreateView Method
     */
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        utils.showLog(TAG, "onActivityCreated")
    }

    override fun onPause() {
        super.onPause()
        utils.showLog(TAG, "onPause")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        utils.showLog(TAG, "onDestroyView")
        dashboardView = null
        dashboardViewContainer = null
    }

    override fun showLoading() {
        utils.showLog(TAG, "showLoading() method called")
    }

    override fun hideLoading() {
        utils.showLog(TAG, "hideLoading() method called")
    }

    override fun showError(message: String) {
        utils.showLog(TAG, "showError($message) method called")
    }

    override fun context(): Context {
        utils.showLog(TAG, "context() method called")
        return mainActivity.applicationContext
    }
}