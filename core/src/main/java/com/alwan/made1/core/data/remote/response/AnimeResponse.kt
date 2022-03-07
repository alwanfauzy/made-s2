package com.alwan.made1.core.data.remote.response

data class AnimeResponse(
    var id: String?,
    val synopsis: String?,
    val titlesResponse: TitlesResponse?,
    val canonicalTitle: String?,
    val averageRating: String?,
    val userCount: Int?,
    val favoritesCount: Int?,
    val popularityRank: Int?,
    val ratingRank: Int?,
    val status: String?,
    val posterImageResponse: PosterImageResponse?,
    val coverImageResponse: CoverImageResponse?,
    val episodeCount: Int?,
    val youtubeVideoId: String?,
    val showType: String?,
)