package com.alwan.made2.core.data.local.entity

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorite_anime")
data class AnimeEntity(
    @PrimaryKey(autoGenerate = false)
    @NonNull
    @ColumnInfo(name = "id")
    var id: String,

    @ColumnInfo(name = "synopsis")
    val synopsis: String?,

    @ColumnInfo(name = "titleEn")
    val titleEn: String?,

    @ColumnInfo(name = "titleEnJp")
    val titleEnJp: String?,

    @ColumnInfo(name = "titleJaJp")
    val titleJaJp: String?,

    @ColumnInfo(name = "canonicalTitle")
    val canonicalTitle: String?,

    @ColumnInfo(name = "averageRating")
    val averageRating: String?,

    @ColumnInfo(name = "userCount")
    val userCount: Int?,

    @ColumnInfo(name = "favoritesCount")
    val favoritesCount: Int?,

    @ColumnInfo(name = "popularityRank")
    val popularityRank: Int?,

    @ColumnInfo(name = "ratingRank")
    val ratingRank: Int?,

    @ColumnInfo(name = "status")
    val status: String?,

    @ColumnInfo(name = "posterImageMedium")
    val posterImageMedium: String?,

    @ColumnInfo(name = "posterImageLarge")
    val posterImageLarge: String?,

    @ColumnInfo(name = "posterImageOriginal")
    val posterImageOriginal: String?,

    @ColumnInfo(name = "coverImageSmall")
    val coverImageSmall: String?,

    @ColumnInfo(name = "coverImageLarge")
    val coverImageLarge: String?,

    @ColumnInfo(name = "coverImageOriginal")
    val coverImageOriginal: String?,

    @ColumnInfo(name = "episodeCount")
    val episodeCount: Int?,

    @ColumnInfo(name = "youtubeVideoId")
    val youtubeVideoId: String?,

    @ColumnInfo(name = "showType")
    val showType: String?,
)