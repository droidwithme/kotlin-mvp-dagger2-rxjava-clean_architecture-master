package com.cloudwalker.demo.domain.interactors

import com.cloudwalker.demo.domain.executors.PostExecutionThread
import com.cloudwalker.demo.domain.executors.ThreadExecutor
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers


/**
 * Abstract class for a Use Case (Interactor in terms of Clean Architecture).
 * This interface represents a execution unit for different use cases (this means any use case
 * in the application should implement this contract).
 *
 * By convention each Interactor implementation will return the result using a [DisposableObserver]
 * that will execute its job in a background thread and will post the result in the UI thread.
 */
abstract class Interactor
internal constructor(private val threadExecutor: ThreadExecutor,
                     private val postExecutionThread: PostExecutionThread) {
    private val disposableContainer: CompositeDisposable = CompositeDisposable()

    /**
     * Builds an [Observable] which will be used when executing the current [Interactor].
     */
    internal abstract fun buildInteractorObservable(): Observable<*>

    /**
     * Executes the current Interactor.
     *
     * @param observer [DisposableObserver]
     * Which will be listening to the observable build by [.buildInteractorObservable] ()} method.
     */
    fun execute(observer: DisposableObserver<Any>) {
        checkNotNull(observer)
        val observable = this.buildInteractorObservable()
                .subscribeOn(Schedulers.from(threadExecutor))
                .observeOn(postExecutionThread.scheduler)
        addDisposable(observable.subscribeWith(observer))
    }

    /**
     * Dispose from current [CompositeDisposable].
     */
    fun dispose() {
        checkNotNull(disposableContainer)
        if (!disposableContainer.isDisposed) {
            disposableContainer.dispose()
        }
    }

    /**
     * Dispose from current [CompositeDisposable].
     */
    private fun addDisposable(disposable: Disposable) {
        checkNotNull(disposableContainer)
        checkNotNull(disposable)
        disposableContainer.add(disposable)
    }
}