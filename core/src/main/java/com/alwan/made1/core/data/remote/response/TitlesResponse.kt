package com.alwan.made1.core.data.remote.response

import com.google.gson.annotations.SerializedName

data class TitlesResponse(
    val en: String?,
    @SerializedName("en_jp")
    val enJp: String?,
    @SerializedName("ja_jp")
    val jaJp: String?
)

