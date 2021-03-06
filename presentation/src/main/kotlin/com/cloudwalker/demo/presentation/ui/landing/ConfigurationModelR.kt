package com.cloudwalker.demo.presentation.ui.landing

import com.google.gson.annotations.SerializedName

/**
 *  The response from getConfiguration API
 *  getConfiugation API emites this data class
 */
data class ConfigurationModelR(
    @SerializedName("change_keys")
    var changeKeys: List<String>,
    @SerializedName("images")
    var images: Images
)
data class Images(
    @SerializedName("backdrop_sizes")
    var backdropSizes: List<String>,
    @SerializedName("base_url")
    var baseUrl: String,
    @SerializedName("logo_sizes")
    var logoSizes: List<String>,
    @SerializedName("poster_sizes")
    var posterSizes: List<String>,
    @SerializedName("profile_sizes")
    var profileSizes: List<String>,
    @SerializedName("secure_base_url")
    var secureBaseUrl: String,
    @SerializedName("still_sizes")
    var stillSizes: List<String>
)