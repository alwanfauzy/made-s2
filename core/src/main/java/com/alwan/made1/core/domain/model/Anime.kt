package com.alwan.made1.core.domain.model

data class Anime(
    var id: String?,
    val synopsis: String?,
    val titles: Titles?,
    val canonicalTitle: String?,
    val averageRating: String?,
    val userCount: Int?,
    val favoritesCount: Int?,
    val popularityRank: Int?,
    val ratingRank: Int?,
    val status: String?,
    val posterImage: PosterImage?,
    val coverImage: CoverImage?,
    val episodeCount: Int?,
    val youtubeVideoId: String?,
    val showType: String?,
)