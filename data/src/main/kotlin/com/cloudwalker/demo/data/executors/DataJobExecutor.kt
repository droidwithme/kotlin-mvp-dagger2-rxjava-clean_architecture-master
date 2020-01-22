package com.cloudwalker.demo.data.executors

import com.cloudwalker.demo.domain.executors.ThreadExecutor
import java.util.concurrent.*
import javax.inject.Inject

class DataJobExecutor @Inject
constructor() : ThreadExecutor {

    private val workQueue: BlockingQueue<Runnable>

    private val threadPoolExecutor: ThreadPoolExecutor

    private val threadFactory: ThreadFactory

    init {
        this.workQueue = LinkedBlockingQueue()
        this.threadFactory = JobThreadFactory()
        this.threadPoolExecutor = ThreadPoolExecutor(INITIAL_POOL_SIZE,
                MAX_POOL_SIZE,
                KEEP_ALIVE_TIME.toLong(),
                KEEP_ALIVE_TIME_UNIT,
                this.workQueue,
                this.threadFactory)
    }

    override fun execute(runnable: Runnable?) {
        if (runnable == null) {
            throw IllegalArgumentException("Provided runnable can't be null")
        }
        this.threadPoolExecutor.execute(runnable)
    }

    private class JobThreadFactory : ThreadFactory {
        private val counter = 0

        override fun newThread(runnable: Runnable): Thread {
            return Thread(runnable, THREAD_NAME + counter)
        }

        companion object {
            private const val THREAD_NAME = "android_"
        }
    }

    companion object {
        private const val INITIAL_POOL_SIZE = 3
        private const val MAX_POOL_SIZE = 5

        // Sets Amount Of Time An Idle Thread Waits Before Terminating
        private const val KEEP_ALIVE_TIME = 10

        // Set Time Unit To Seconds
        private val KEEP_ALIVE_TIME_UNIT = TimeUnit.SECONDS
    }
}
