package com.alwan.made2.core.data.remote.response

import com.google.gson.annotations.SerializedName

data class DataResponse(
    @SerializedName("id")
    val id: String?,

    @SerializedName("attributes")
    val attributes: AttributesResponse?,
)
