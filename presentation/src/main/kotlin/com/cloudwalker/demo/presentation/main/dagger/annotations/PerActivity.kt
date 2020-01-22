package com.cloudwalker.demo.presentation.main.dagger.annotations

import javax.inject.Scope

/**
 * To create Singleton instance PerActivity.
 *
 * Created by Praveen on 06-08-2018.
 */
@Scope
@Retention(AnnotationRetention.RUNTIME)
annotation class PerActivity