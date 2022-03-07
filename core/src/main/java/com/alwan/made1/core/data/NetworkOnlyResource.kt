package com.alwan.made1.core.data

import android.content.res.Resources
import com.alwan.made1.core.R
import com.alwan.made1.core.data.remote.network.ApiResponse
import kotlinx.coroutines.flow.*

abstract class NetworkOnlyResource<ResultType, RequestType> {

    private val result: Flow<Resource<ResultType>> = flow {
        emit(Resource.Loading())
        when (val apiResponse = createCall().first()) {
            is ApiResponse.Success -> {
                emitAll(loadFromNetwork(apiResponse.data).map {
                    Resource.Success(it)
                })
            }
            is ApiResponse.Error -> emit(Resource.Error(apiResponse.errorMessage))
            is ApiResponse.Empty -> emit(
                Resource.Error(
                    Resources.getSystem().getString(R.string.empty_data)
                )
            )
        }
    }


    protected abstract fun loadFromNetwork(data: RequestType): Flow<ResultType>

    protected abstract suspend fun createCall(): Flow<ApiResponse<RequestType>>

    fun asFlow(): Flow<Resource<ResultType>> = result
}