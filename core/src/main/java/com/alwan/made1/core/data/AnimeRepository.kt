package com.alwan.made1.core.data

import com.alwan.made1.core.data.local.LocalDataSource
import com.alwan.made1.core.data.remote.RemoteDataSource
import com.alwan.made1.core.data.remote.network.ApiResponse
import com.alwan.made1.core.data.remote.response.AnimeResponse
import com.alwan.made1.core.domain.model.Anime
import com.alwan.made1.core.domain.repository.IAnimeRepository
import com.alwan.made1.core.utils.DataMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class AnimeRepository(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource
) : IAnimeRepository {

    override fun getSearchAnime(query: String): Flow<Resource<List<Anime>>> {
        return object : com.alwan.made1.core.data.NetworkOnlyResource<List<Anime>, List<AnimeResponse>>() {
            override fun loadFromNetwork(data: List<AnimeResponse>): Flow<List<Anime>> {
                return DataMapper.mapResponsesToDomain(data)
            }

            override suspend fun createCall(): Flow<ApiResponse<List<AnimeResponse>>> {
                return remoteDataSource.getSearchAnime(query)
            }

        }.asFlow()
    }

    override fun getDetailAnime(id: String): Flow<Resource<Anime>> {
        return object : com.alwan.made1.core.data.NetworkOnlyResource<Anime, AnimeResponse>() {
            override fun loadFromNetwork(data: AnimeResponse): Flow<Anime> {
                return DataMapper.mapResponseToDomain(data)
            }

            override suspend fun createCall(): Flow<ApiResponse<AnimeResponse>> {
                return remoteDataSource.getDetailAnime(id)
            }
        }.asFlow()
    }

    override suspend fun insertFavoriteAnime(anime: Anime) {
        val domainAnime = DataMapper.mapDomainToEntity(anime)
        return localDataSource.insertFavoriteAnime(domainAnime)
    }

    override suspend fun deleteFavoriteAnime(anime: Anime): Int {
        val domainAnime = DataMapper.mapDomainToEntity(anime)
        return localDataSource.deleteFavoriteAnime(domainAnime)
    }

    override fun isFavoriteAnime(id: String) = localDataSource.isFavoriteAnime(id)

    override fun getFavoriteAnime(): Flow<List<Anime>> {
        return localDataSource.getFavoriteAnime().map {
            DataMapper.mapEntitiesToDomain(it)
        }
    }
}
