package com.alwan.made2.core.data.remote.response

import com.google.gson.annotations.SerializedName

data class TitlesResponse(
    @SerializedName("en")
    val en: String?,
    @SerializedName("en_jp")
    val enJp: String?,
    @SerializedName("ja_jp")
    val jaJp: String?
)

