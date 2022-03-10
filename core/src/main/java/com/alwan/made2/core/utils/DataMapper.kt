package com.alwan.made2.core.utils

import com.alwan.made2.core.data.local.entity.AnimeEntity
import com.alwan.made2.core.data.remote.response.*
import com.alwan.made2.core.domain.model.Anime
import com.alwan.made2.core.domain.model.CoverImage
import com.alwan.made2.core.domain.model.PosterImage
import com.alwan.made2.core.domain.model.Titles
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

object DataMapper {
    fun mapResponsesToDomain(input: List<AnimeResponse>): Flow<List<Anime>> {
        val animeList = ArrayList<Anime>()
        input.map {
            val anime = Anime(
                id = it.id,
                synopsis = it.synopsis,
                titles = mapTitlesResponseToDomain(it.titlesResponse),
                canonicalTitle = it.canonicalTitle,
                averageRating = it.averageRating,
                userCount = it.userCount,
                favoritesCount = it.favoritesCount,
                popularityRank = it.popularityRank,
                ratingRank = it.ratingRank,
                status = it.status,
                posterImage = mapPosterImageResponseToDomain(it.posterImageResponse),
                coverImage = mapCoverImageResponseToDomain(it.coverImageResponse),
                episodeCount = it.episodeCount,
                youtubeVideoId = it.youtubeVideoId,
                showType = it.showType,
            )
            animeList.add(anime)
        }

        return flowOf(animeList)
    }

    fun mapResponseToDomain(input: AnimeResponse) = flowOf(
        Anime(
            id = input.id,
            synopsis = input.synopsis,
            titles = mapTitlesResponseToDomain(input.titlesResponse),
            canonicalTitle = input.canonicalTitle,
            averageRating = input.averageRating,
            userCount = input.userCount,
            favoritesCount = input.favoritesCount,
            popularityRank = input.popularityRank,
            ratingRank = input.ratingRank,
            status = input.status,
            posterImage = mapPosterImageResponseToDomain(input.posterImageResponse),
            coverImage = mapCoverImageResponseToDomain(input.coverImageResponse),
            episodeCount = input.episodeCount,
            youtubeVideoId = input.youtubeVideoId,
            showType = input.showType,
        )
    )

    fun mapEntitiesToDomain(input: List<AnimeEntity>): List<Anime> {
        val animeList = ArrayList<Anime>()
        input.map {
            val anime = Anime(
                id = it.id,
                synopsis = it.synopsis,
                titles = Titles(it.titleEn, it.titleEnJp, it.titleJaJp),
                canonicalTitle = it.canonicalTitle,
                averageRating = it.averageRating,
                userCount = it.userCount,
                favoritesCount = it.favoritesCount,
                popularityRank = it.popularityRank,
                ratingRank = it.ratingRank,
                status = it.status,
                posterImage = PosterImage(
                    it.posterImageMedium,
                    it.posterImageLarge,
                    it.posterImageOriginal
                ),
                coverImage = CoverImage(
                    it.coverImageSmall,
                    it.coverImageLarge,
                    it.coverImageOriginal
                ),
                episodeCount = it.episodeCount,
                youtubeVideoId = it.youtubeVideoId,
                showType = it.showType,
            )
            animeList.add(anime)
        }

        return animeList
    }

    fun mapDomainToEntity(input: Anime) = AnimeEntity(
        id = input.id.toString(),
        synopsis = input.synopsis,
        titleEn = input.titles?.en,
        titleEnJp = input.titles?.enJp,
        titleJaJp = input.titles?.jaJp,
        canonicalTitle = input.canonicalTitle,
        averageRating = input.averageRating,
        userCount = input.userCount,
        favoritesCount = input.favoritesCount,
        popularityRank = input.popularityRank,
        ratingRank = input.ratingRank,
        status = input.status,
        posterImageMedium = input.posterImage?.medium,
        posterImageLarge = input.posterImage?.large,
        posterImageOriginal = input.posterImage?.original,
        coverImageSmall = input.coverImage?.small,
        coverImageLarge = input.coverImage?.large,
        coverImageOriginal = input.coverImage?.original,
        episodeCount = input.episodeCount,
        youtubeVideoId = input.youtubeVideoId,
        showType = input.showType,
    )


    private fun mapPosterImageResponseToDomain(input: PosterImageResponse?) = input?.let {
        PosterImage(
            medium = input.medium,
            large = input.large,
            original = input.original
        )
    }

    private fun mapCoverImageResponseToDomain(input: CoverImageResponse?) = input?.let {
        CoverImage(
            small = input.small,
            large = input.large,
            original = input.original
        )
    }

    private fun mapTitlesResponseToDomain(input: TitlesResponse?) = input?.let {
        Titles(
            en = input.en,
            enJp = input.enJp,
            jaJp = input.jaJp,
        )
    }
}