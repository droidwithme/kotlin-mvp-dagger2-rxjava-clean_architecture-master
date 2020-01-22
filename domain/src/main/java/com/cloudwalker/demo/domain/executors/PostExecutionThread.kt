package com.cloudwalker.demo.domain.executors

import io.reactivex.Scheduler

interface PostExecutionThread {
    val scheduler: Scheduler
}