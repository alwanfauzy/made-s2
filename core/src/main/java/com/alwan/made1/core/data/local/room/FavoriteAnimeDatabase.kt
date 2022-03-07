package com.alwan.made1.core.data.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.alwan.made1.core.data.local.entity.AnimeEntity

@Database(entities = [AnimeEntity::class], version = 1, exportSchema = false)
abstract class FavoriteAnimeDatabase : RoomDatabase() {

    abstract fun favoriteAnimeDao(): FavoriteAnimeDao
}