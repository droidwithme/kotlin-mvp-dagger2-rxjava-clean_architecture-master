package com.cloudwalker.demo.domain.interactors

import io.reactivex.observers.DisposableObserver


/**
 * Default [DisposableObserver] base class to be used whenever you want default error handling.
 */
abstract class DefaultObserver : DisposableObserver<Any>()