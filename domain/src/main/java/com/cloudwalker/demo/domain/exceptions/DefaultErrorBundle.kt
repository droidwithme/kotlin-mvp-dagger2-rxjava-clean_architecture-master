package com.cloudwalker.demo.domain.exceptions

/**
 * Wrapper around Exceptions used to manage default errors.
 */
class DefaultErrorBundle(override val exception: Exception) : ErrorBundle {

    override val errorMessage: String
        get() {
            return this.exception.message!!
        }
}
