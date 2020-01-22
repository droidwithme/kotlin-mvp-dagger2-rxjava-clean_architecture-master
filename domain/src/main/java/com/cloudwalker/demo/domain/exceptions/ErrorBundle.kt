package com.cloudwalker.demo.domain.exceptions

interface ErrorBundle {
    val exception: Exception

    val errorMessage: String
}
