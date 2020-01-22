package com.cloudwalker.demo.presentation.main.fragment

import android.content.Context
import android.os.Bundle
import android.view.InflateException
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.gson.Gson
import com.cloudwalker.demo.presentation.main.dagger.components.ApplicationComponent
import com.cloudwalker.demo.presentation.main.activity.MainActivity
import com.cloudwalker.utils.Utils

private val TAG: String = MainFragment::class.java.simpleName

/**
 * BaseFragment Which has to be implemented by all UI Fragment
 *
 * Created by Praveen on 06-08-2018.
 */
abstract class MainFragment : Fragment() {

    // To get instance of MainActivity at Fragment level
    val mainActivity: MainActivity get() = activity as MainActivity

    // To get instance of ApplicationComponent at Fragment level
    val applicationComponent: ApplicationComponent get() = mainActivity.applicationComponent

    // To get instance of Utils at Fragment level
    val utils: Utils get() = mainActivity.utils

    // To get instance of Gson at Fragment level
    val gson: Gson get() = mainActivity.gson

    // View to render UI
    private var baseView: View? = null

    /**
     * This method will be called first, even before onCreate(),
     * letting us know that your fragment has been attached to an activity.
     * You are passed the Activity that will host your fragment
     */
    override fun onAttach(context: Context) {
        super.onAttach(context)
        utils.showLog(TAG, "onAttach($context)")
    }

    /**
     * The system calls this callback when it’s time for the fragment to draw its UI for the first time.
     * To draw a UI for the fragment,
     * A View component must be returned from this method which is the root of the fragment’s layout.
     * We can return null if the fragment does not provide a UI
     */
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        if (baseView != null) {
            val parent = baseView!!.parent as ViewGroup
            parent.removeView(baseView)
            utils.showLog(TAG, "BaseView != Null $baseView")
        }
        try {
            baseView = super.onCreateView(inflater, container, savedInstanceState)
            utils.showLog(TAG, "BaseView == Null $baseView")

        } catch (exception: InflateException) {
            utils.showLog(TAG, "Exception $exception")
        }
        return baseView
    }

    /**
     * This will be called after onCreateView().
     * This is particularly useful when inheriting the onCreateView() implementation
     * but we need to configure the resulting views, such as with a ListFragment and when to set up an adapter
     */
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        utils.showLog(TAG, "onViewCreated($view, $savedInstanceState)")
    }

    /**
     * This will be called after onCreate() and onCreateView(),
     * To indicate that the activity’s onCreate() has completed.
     * If there is something that’s needed to be initialised in the fragment
     * That depends upon the activity’s onCreate() having completed its work then
     * onActivityCreated() can be used for that initialisation work
     */
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        utils.showLog(TAG, "onActivityCreated($savedInstanceState)")
    }

    /**
     * The onStart() method is called once the fragment gets visible
     */
    override fun onStart() {
        super.onStart()
        utils.showLog(TAG, "onStart()")
    }

    /**
     * The system calls this method as the first indication that the user is leaving the fragment.
     * This is usually where you should commit any changes that should be persisted beyond the current user session
     */
    override fun onPause() {
        super.onPause()
        utils.showLog(TAG, "onPause()")
    }

    /**
     * Fragment going to be stopped by calling onStop()
     */
    override fun onStop() {
        super.onStop()
        utils.showLog(TAG, "onStop()")
    }

    /**
     * It’s called before onDestroy().
     * This is the counterpart to onCreateView() where we set up the UI.
     * If there are things that are needed to be cleaned up specific to the UI,
     * then that logic can be put up in onDestroyView()
     */
    override fun onDestroyView() {
        super.onDestroyView()
        utils.showLog(TAG, "onDestroyView()")
    }

    /**
     * onDestroy() called to do final clean up of the fragment’s state,
     * but Not guaranteed to be called by the Android platform.
     */
    override fun onDestroy() {
        super.onDestroy()
        utils.showLog(TAG, "onDestroy()")
    }

    /**
     * It’s called after onDestroy(),
     * to notify that the fragment has been disassociated from its hosting activity
     */
    override fun onDetach() {
        super.onDetach()
        utils.showLog(TAG, "onDetach()")
    }
}