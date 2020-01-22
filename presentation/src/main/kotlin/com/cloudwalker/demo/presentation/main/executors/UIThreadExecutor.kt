package com.cloudwalker.demo.presentation.main.executors

import com.cloudwalker.demo.domain.executors.PostExecutionThread
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import javax.inject.Inject
import javax.inject.Singleton

/**
 * This Is A Main Thread (UI) Implementation Based On {@Link rx.Scheduler}
 * Which Will Execute Actions On THe Android UI Thread.
 */
@Singleton
class UIThreadExecutor
@Inject
constructor() : PostExecutionThread {
    override val scheduler: Scheduler get() = AndroidSchedulers.mainThread()
}
