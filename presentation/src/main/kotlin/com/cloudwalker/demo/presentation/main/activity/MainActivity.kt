package com.cloudwalker.demo.presentation.main.activity

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.transition.TransitionInflater
import com.cloudwalker.demo.presentation.R
import com.cloudwalker.demo.presentation.main.appflow.BackPressReactor
import com.cloudwalker.demo.presentation.main.application.MainApplication
import com.cloudwalker.demo.presentation.main.dagger.components.ApplicationComponent
import com.cloudwalker.demo.presentation.main.fragment.FragmentEnum
import com.cloudwalker.demo.presentation.main.fragment.FragmentProvider
import com.cloudwalker.utils.Utils
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.view_main.*
import kotlinx.android.synthetic.main.view_toolbar.*


private val TAG: String = MainActivity::class.java.simpleName

class MainActivity : AppCompatActivity() {

    // To provide Application reference to BaseFragment
    private val mainApplication: MainApplication get() = (application as MainApplication)

    // To provide Application Component reference to BaseFragment
    val applicationComponent: ApplicationComponent get() = mainApplication.applicationComponent

    // To get instance of Utils at Fragment level
    val utils: Utils get() = mainApplication.utils

    // To get instance of Gson at Fragment level
    val gson: Gson get() = mainApplication.gson

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        //Entry fragment to the application
        replaceFragment(FragmentEnum.SPLASHSCREEN, null, null, null)

    }

    /**
     * Called when the activity will start interacting with the user.
     * At this point your activity is at the top of the activity stack, with user input going to it.
     */
    override fun onResume() {
        super.onResume()
        utils.showLog(TAG, "onResume()")
    }

    override fun onBackPressed() {
        BackPressReactor.backPressed(this, R.id.contentFrame, utils)
    }


    /**
     * To Change Fragment In Activity
     */
    fun replaceFragment(
        fragmentEnum: FragmentEnum,
        sharedElement: View?,
        transitionName: String?,
        bundle: Bundle?
    ) {
        utils.showLog(
            TAG,
            "Replacing Fragment :${fragmentEnum.code}, $sharedElement, $transitionName"
        )
        val fragment = FragmentProvider.getFragment(this, fragmentEnum, utils)
        fragment.arguments = bundle
        if (sharedElement != null) {
            utils.showLog(TAG, "Replacing Fragment With Shared Element")
            // Defines enter transition only for shared element
            val changeBoundsTransition =
                TransitionInflater.from(this).inflateTransition(android.R.transition.move)
            fragment.sharedElementEnterTransition = changeBoundsTransition
            supportFragmentManager.beginTransaction()
                .replace(R.id.contentFrame, fragment, fragment::class.java.simpleName)
                .addSharedElement(sharedElement, transitionName!!)
                .commitAllowingStateLoss()
        } else {
            utils.showLog(TAG, "Replacing Fragment Without Shared Element")
            supportFragmentManager.beginTransaction()
                .replace(R.id.contentFrame, fragment, fragment::class.java.simpleName)
                .commitAllowingStateLoss()
        }

    }

    /**
     * To Change Fragment In Activity
     */
    fun showLoading() {
        if (loadingRelativeLayout.visibility != View.VISIBLE)
            loadingRelativeLayout.visibility = View.VISIBLE
    }

    /**
     * To Change Fragment In Activity
     */
    fun hideLoading() {
        if (loadingRelativeLayout.visibility != View.GONE)
            loadingRelativeLayout.visibility = View.GONE
    }

    /**
     * To Show Error
     */
    fun showError(errorMessage: String) {
        // Hide Loading View
        if (loadingRelativeLayout.visibility != View.GONE)
            loadingRelativeLayout.visibility = View.GONE
        // Hide Displayed Content
        if (contentFrame.visibility != View.GONE)
            contentFrame.visibility = View.GONE
        // Show Error Content
        if (errorConstraintLayout.visibility != View.VISIBLE)
            errorConstraintLayout.visibility = View.VISIBLE
        if (errorMessage == getString(R.string.exception_message_unknown_host_exception)) {
            errorLottieAnimationView.setAnimation("something-went-wrong.json")
            errorLottieAnimationView.playAnimation()
        } else {
            errorLottieAnimationView.setAnimation("something-went-wrong.json")
            errorLottieAnimationView.playAnimation()
        }
        errorMessageTextView.text = "Reset"
        errorMessageTextView.setOnClickListener {
            hideError()
        }
    }

    /**
     * To Hide Error
     */
    private fun hideError() {
        // Hide Loading View
        if (loadingRelativeLayout.visibility != View.GONE)
            loadingRelativeLayout.visibility = View.GONE
        // Hide Displayed Content
        if (contentFrame.visibility != View.VISIBLE)
            contentFrame.visibility = View.VISIBLE
        // Show Error Content
        if (errorConstraintLayout.visibility != View.GONE)
            errorConstraintLayout.visibility = View.GONE
    }


    /**
     * To Set Application Background With White Color
     */
    @Suppress("unused")
    fun setWhiteBackground() {
        utils.showLog(TAG, "Setting white Background")
        window.setBackgroundDrawable(
            ContextCompat.getDrawable(
                this,
                android.R.color.white
            )
        )
    }
}
