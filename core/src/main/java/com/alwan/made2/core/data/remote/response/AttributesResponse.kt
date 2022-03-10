package com.alwan.made2.core.data.remote.response

import com.google.gson.annotations.SerializedName

data class AttributesResponse(
    @SerializedName("synopsis")
    val synopsis: String?,

    @SerializedName("titles")
    val titles: TitlesResponse?,

    @SerializedName("canonicalTitle")
    val canonicalTitle: String?,

    @SerializedName("averageRating")
    val averageRating: String?,

    @SerializedName("userCount")
    val userCount: Int?,

    @SerializedName("favoritesCount")
    val favoritesCount: Int?,

    @SerializedName("popularityRank")
    val popularityRank: Int?,

    @SerializedName("ratingRank")
    val ratingRank: Int?,

    @SerializedName("status")
    val status: String?,

    @SerializedName("posterImage")
    val posterImage: PosterImageResponse?,

    @SerializedName("coverImage")
    val coverImage: CoverImageResponse?,

    @SerializedName("episodeCount")
    val episodeCount: Int?,

    @SerializedName("youtubeVideoId")
    val youtubeVideoId: String?,

    @SerializedName("showType")
    val showType: String?,
)
