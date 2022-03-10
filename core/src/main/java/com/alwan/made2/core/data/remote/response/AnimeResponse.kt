package com.alwan.made2.core.data.remote.response

import com.google.gson.annotations.SerializedName

data class AnimeResponse(
    @SerializedName("id")
    var id: String?,

    @SerializedName("synopsis")
    val synopsis: String?,

    @SerializedName("titlesResponse")
    val titlesResponse: TitlesResponse?,

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

    @SerializedName("posterImageResponse")
    val posterImageResponse: PosterImageResponse?,

    @SerializedName("coverImageResponse")
    val coverImageResponse: CoverImageResponse?,

    @SerializedName("episodeCount")
    val episodeCount: Int?,

    @SerializedName("youtubeVideoId")
    val youtubeVideoId: String?,

    @SerializedName("showType")
    val showType: String?,
)