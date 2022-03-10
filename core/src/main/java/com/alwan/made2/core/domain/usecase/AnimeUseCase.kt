package com.alwan.made2.core.domain.usecase

import com.alwan.made2.core.data.Resource
import com.alwan.made2.core.domain.model.Anime
import kotlinx.coroutines.flow.Flow

interface AnimeUseCase {
    fun getSearchAnime(query: String): Flow<Resource<List<Anime>>>

    fun getDetailAnime(id: String): Flow<Resource<Anime>>

    fun getFavoriteAnime(): Flow<List<Anime>>

    fun isFavoriteAnime(id: String): Int

    suspend fun insertFavoriteAnime(anime: Anime)

    suspend fun deleteFavoriteAnime(anime: Anime): Int
}