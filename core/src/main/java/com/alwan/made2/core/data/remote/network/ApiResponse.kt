package com.alwan.made2.core.data.remote.network

sealed class ApiResponse<out T> {
    data class Success<out T> (val data: T): ApiResponse<T>()
    data class Error(val errorMessage: String?): ApiResponse<Nothing>()
    object Empty : ApiResponse<Nothing>()
}