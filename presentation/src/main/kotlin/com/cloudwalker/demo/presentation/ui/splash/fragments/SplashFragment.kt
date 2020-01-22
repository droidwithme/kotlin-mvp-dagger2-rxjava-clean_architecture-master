package com.cloudwalker.demo.presentation.ui.splash.fragments

import android.content.Context
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.view.*
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import com.cloudwalker.demo.presentation.R
import com.cloudwalker.demo.presentation.main.fragment.FragmentEnum
import com.cloudwalker.demo.presentation.main.fragment.MainFragment
import com.cloudwalker.demo.presentation.ui.splash.views.SplashView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_splash.*

private val TAG: String = SplashFragment::class.java.simpleName

class SplashFragment : MainFragment(), SplashView {
    private var splashView: View? = null
    private var splashViewContainer: ViewGroup? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        utils.showLog(TAG, "onCreateView")
        if (splashView != null) {
            val parent = splashView!!.parent as ViewGroup
            parent.removeView(splashView)
        }
        try {
            splashView = inflater.inflate(R.layout.fragment_splash, container, false)
            splashViewContainer = container

        } catch (e: InflateException) {

        }
        mainActivity.appbarLayout.visibility = View.GONE
        mainActivity.setWhiteBackground()
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            utils.showLog(TAG, "SplashScreen on Pie or greater")
            mainActivity.window.addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN)
            mainActivity.window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
            // To support notch display
            val attributes = mainActivity.window.attributes
            attributes.layoutInDisplayCutoutMode =
                WindowManager.LayoutParams.LAYOUT_IN_DISPLAY_CUTOUT_MODE_SHORT_EDGES
        } else {
            utils.showLog(TAG, "SplashScreen below Pie")
            mainActivity.window.addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN)
            mainActivity.window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        }
        return splashView
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
        animateLogo()
    }

    override fun onPause() {
        super.onPause()
        utils.showLog(TAG, "onPause")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        utils.showLog(TAG, "onDestroyView")
        splashView = null
        splashViewContainer = null
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

    private fun animateLogo() {
        utils.showLog(TAG, "Animating Logo")
        // Getting Animation
        val animation = AnimationUtils.loadAnimation(activity, R.anim.bottom_middle)
        // Setting Listener To Animation
        animation.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationRepeat(animation: Animation?) {
                utils.showLog(TAG, "onAnimationRepeat")
            }

            override fun onAnimationEnd(animation: Animation?) {
                utils.showLog(TAG, "onAnimationEnd")
                val handler = Handler()
                handler.postDelayed({
                    mainActivity.replaceFragment(
                        FragmentEnum.LANDSCREEN,
                        logoImageView,
                        context!!.getString(R.string.collection_logo_transition)
                    )
                }, 2000) // Hold for 2 Seconds
            }

            override fun onAnimationStart(animation: Animation?) {
                utils.showLog(TAG, "onAnimationStart")
            }
        })
        logoImageView!!.startAnimation(animation)

    }
}