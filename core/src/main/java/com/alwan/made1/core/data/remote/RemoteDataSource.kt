package com.alwan.made1.core.data.remote

import android.util.Log
import com.alwan.made1.core.data.remote.network.ApiResponse
import com.alwan.made1.core.data.remote.network.ApiService
import com.alwan.made1.core.data.remote.response.AnimeResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class RemoteDataSource(private val apiService: ApiService) {
    suspend fun getSearchAnime(query: String): Flow<ApiResponse<List<AnimeResponse>>> =
        flow {
            try {
                val response = apiService.getSearchAnime(query)
                val datas = response.data
                val anime = ArrayList<AnimeResponse>()

                for (data in datas) {
                    anime.add(
                        AnimeResponse(
                            id = data.id,
                            synopsis = data.attributes?.synopsis,
                            titlesResponse = data.attributes?.titles,
                            canonicalTitle = data.attributes?.canonicalTitle,
                            averageRating = data.attributes?.averageRating,
                            userCount = data.attributes?.userCount,
                            favoritesCount = data.attributes?.favoritesCount,
                            popularityRank = data.attributes?.popularityRank,
                            ratingRank = data.attributes?.ratingRank,
                            status = data.attributes?.status,
                            posterImageResponse = data.attributes?.posterImage,
                            coverImageResponse = data.attributes?.coverImage,
                            episodeCount = data.attributes?.episodeCount,
                            youtubeVideoId = data.attributes?.youtubeVideoId,
                            showType = data.attributes?.showType,
                        )
                    )
                }

                if (anime.isNullOrEmpty()) {
                    emit(ApiResponse.Empty)
                } else {
                    emit(ApiResponse.Success(anime))
                }
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
                Log.e(RemoteDataSource::class.java.simpleName, e.toString())
            }
        }.flowOn(Dispatchers.IO)

    suspend fun getDetailAnime(id: String): Flow<ApiResponse<AnimeResponse>> = flow {
        try {
            val response = apiService.getDetailAnime(id)
            val data = response.data
            val anime = AnimeResponse(
                id = data.id,
                synopsis = data.attributes?.synopsis,
                titlesResponse = data.attributes?.titles,
                canonicalTitle = data.attributes?.canonicalTitle,
                averageRating = data.attributes?.averageRating,
                userCount = data.attributes?.userCount,
                favoritesCount = data.attributes?.favoritesCount,
                popularityRank = data.attributes?.popularityRank,
                ratingRank = data.attributes?.ratingRank,
                status = data.attributes?.status,
                posterImageResponse = data.attributes?.posterImage,
                coverImageResponse = data.attributes?.coverImage,
                episodeCount = data.attributes?.episodeCount,
                youtubeVideoId = data.attributes?.youtubeVideoId,
                showType = data.attributes?.showType,
            )

            emit(ApiResponse.Success(anime))
        } catch (e: Exception) {
            emit(ApiResponse.Error(e.toString()))
            Log.e(RemoteDataSource::class.java.simpleName, e.message.toString())
        }
    }.flowOn(Dispatchers.IO)
}