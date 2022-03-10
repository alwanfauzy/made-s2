package com.alwan.made2.core.data.local.room

import androidx.room.*
import com.alwan.made2.core.data.local.entity.AnimeEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface FavoriteAnimeDao {
    @Query("SELECT * FROM favorite_anime")
    fun getFavoriteAnime(): Flow<List<AnimeEntity>>

    @Query("SELECT COUNT(*) FROM favorite_anime WHERE favorite_anime.id = :id")
    fun isFavoriteAnime(id: String): Int

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavoriteAnime(anime: AnimeEntity)

    @Delete
    suspend fun deleteFavoriteAnime(anime: AnimeEntity): Int
}