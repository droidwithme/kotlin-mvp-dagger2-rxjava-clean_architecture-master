package com.cloudwalker.demo.domain.modules.configuration.beans

import com.google.gson.annotations.SerializedName

data class ConfigurationBeanR(
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