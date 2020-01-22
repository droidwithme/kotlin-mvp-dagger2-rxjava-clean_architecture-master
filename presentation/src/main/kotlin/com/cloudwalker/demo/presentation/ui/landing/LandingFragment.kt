package com.cloudwalker.demo.presentation.ui.landing

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.view.*
import com.cloudwalker.demo.presentation.R
import com.cloudwalker.demo.presentation.main.dagger.injector.EnumInjector
import com.cloudwalker.demo.presentation.main.fragment.FragmentEnum
import com.cloudwalker.demo.presentation.main.fragment.MainFragment
import com.cloudwalker.demo.presentation.ui.landing.dagger.ConfigurationComponent
import com.cloudwalker.demo.presentation.ui.landing.dagger.ConfigurationModule
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_landing.*
import javax.inject.Inject

private val TAG: String = LandingFragment::class.java.simpleName

class LandingFragment : MainFragment(), ConfigurationView, View.OnClickListener {
    // To Initialize Mobile Component For Injection
    private val configurationComponent: ConfigurationComponent?
        get() = (EnumInjector.INSTANCE.getConfigurationComponent(
            applicationComponent,
            configurationModule
        ))

    // Mobile module to be initialize after user interaction
    private lateinit var configurationModule: ConfigurationModule

    // To be injected by Dagger when injecting component.
    @Inject
    lateinit var configurationPresenter: ConfigurationPresenter

    // View to render UI
    private var landingView: View? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        if (landingView != null) {
            val parent = landingView!!.parent as ViewGroup
            parent.removeView(landingView)
            utils.showLog(TAG, "landingView != Null $landingView")
        }
        try {
            landingView = inflater.inflate(R.layout.fragment_landing, container, false)
            utils.showLog(TAG, "landingView == Null $landingView")
        } catch (exception: InflateException) {
            utils.showLog(TAG, "Exception $exception")
        }
        mainActivity.appbarLayout.visibility = View.GONE
        return landingView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btnProceed.setOnClickListener(this)
        btnProceed.isEnabled = false
        btnProceed.setBackgroundColor(Color.parseColor("#d3d3d3"))
        initilizePresenter()
    }

    override fun showLoading() {
        mainActivity.showLoading()
    }

    override fun hideLoading() {
        mainActivity.hideLoading()
    }

    override fun showError(message: String) {
        mainActivity.showError(message)
    }

    override fun context(): Context {
        return mainActivity
    }

    override fun renderData(configurationModelR: ConfigurationModelR) {
        utils.showLog(TAG, "Configuration data is $configurationModelR")
        btnProceed.isEnabled = true
        btnProceed.background = resources.getDrawable(R.drawable.button_background)
    }

    override fun onResume() {
        super.onResume()
        utils.showLog(TAG, "onResume()")
    }

    override fun onPause() {
        super.onPause()
        utils.showLog(TAG, "onPause()")
        if (::configurationPresenter.isInitialized)
            configurationPresenter.destroy()
    }

    private fun initilizePresenter() {
        configurationModule = ConfigurationModule(
            ConfigurationModelQ(),
            gson,
            utils
        ) // Initializing Module  Calling method to Inject Dependencies (i.e Presenter)
        configurationComponent?.inject(this) // Injecting Dependencies
        this.configurationPresenter.setView(this) // Initializing Presenter
        this.configurationPresenter.getView()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        utils.showLog(TAG, "onDestroyView")
        landingView = null
        mainActivity.window.clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN)
        mainActivity.window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
    }

    override fun onClick(view: View?) {
        mainActivity.replaceFragment(FragmentEnum.MOVIESHOME, null, null)
    }

}