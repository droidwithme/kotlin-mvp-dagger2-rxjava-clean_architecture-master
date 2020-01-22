package com.cloudwalker.demo.presentation.ui.searchmovie.model

import com.cloudwalker.demo.presentation.ui.commons.Result
import com.google.gson.annotations.SerializedName

data class SearchMoviesModelR(
    @SerializedName("page")
    var page: Int,
    @SerializedName("results")
    var results: List<Result>,
    @SerializedName("total_pages")
    var totalPages: Int,
    @SerializedName("total_results")
    var totalResults: Int
)

