package com.cloudwalker.demo.presentation.ui.landing

import com.cloudwalker.demo.domain.exceptions.DefaultErrorBundle
import com.cloudwalker.demo.domain.exceptions.ErrorBundle
import com.cloudwalker.demo.domain.interactors.DefaultObserver
import com.cloudwalker.demo.domain.interactors.Interactor
import com.cloudwalker.demo.domain.modules.configuration.beans.ConfigurationBeanR
import com.cloudwalker.demo.presentation.main.exceptions.ErrorMessageFactory
import com.cloudwalker.demo.presentation.main.presenter.MainPresenter
import com.cloudwalker.demo.presentation.main.runtime.Constants
import com.cloudwalker.utils.Utils
import javax.inject.Inject
import javax.inject.Named

class ConfigurationPresenter
@Inject
constructor(
    @Named(Constants.configurationInteractor)
    private val configurationInteractor: Interactor,
    private val configurationConverter: ConfigurationConverter,
    private val utils: Utils
) : DefaultObserver(), MainPresenter {
    private lateinit var configurationView: ConfigurationView

    override fun resume() {}

    override fun pause() {}

    override fun destroy() {
        configurationInteractor.dispose()
    }

    fun setView(view: ConfigurationView) {
        configurationView = view
    }

    fun getView() {
        initialize()
    }

    private fun initialize() {
        showViewLoading()
        getConfiguration()
    }

    private fun getConfiguration() {
        configurationInteractor.execute(this)
    }

    private fun showViewLoading() {
        configurationView.showLoading()
    }

    private fun hideViewLoading() {
        configurationView.hideLoading()
    }

    private fun showErrorMessage(errorBundle: ErrorBundle) {
        val errorMessage = ErrorMessageFactory.create(utils, errorBundle.exception)
        configurationView.showError(errorMessage)
    }

    private fun transformData(configurationBeanR: Any) {
        showDataInView(configurationConverter.transformBean(configurationBeanR  as ConfigurationBeanR))
    }

    private fun showDataInView(configurationModelR: ConfigurationModelR) {
        configurationView.renderData(configurationModelR)
    }

    override fun onNext(any: Any) {
        transformData(any)
    }

    override fun onComplete() {
        hideViewLoading()
    }

    override fun onError(exception: Throwable) {
        hideViewLoading()
        showErrorMessage(DefaultErrorBundle(exception as Exception))
    }
}