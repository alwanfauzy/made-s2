package com.alwan.made1.core.di

import androidx.room.Room
import com.alwan.made1.core.BuildConfig.DEBUG
import com.alwan.made1.core.data.local.room.FavoriteAnimeDatabase
import com.alwan.made1.core.data.remote.RemoteDataSource
import com.alwan.made1.core.data.remote.network.ApiService
import com.alwan.made1.core.domain.repository.IAnimeRepository
import com.alwan.made1.core.utils.NetworkInfo.BASE_URL
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val databaseModule = module {
    factory { get<FavoriteAnimeDatabase>().favoriteAnimeDao() }
    single {
        Room.databaseBuilder(
            androidContext(),
            FavoriteAnimeDatabase::class.java,
            "FavoriteAnime.db"
        ).fallbackToDestructiveMigration().build()
    }
}

val networkModule = module {
    single {
        val loggingInterceptor =
            if (DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE

        OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().setLevel(loggingInterceptor))
            .connectTimeout(120, TimeUnit.SECONDS)
            .readTimeout(120, TimeUnit.SECONDS)
            .build()
    }
    single {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(get())
            .build()
        retrofit.create(ApiService::class.java)
    }
}

val repositoryModule = module {
    single { com.alwan.made1.core.data.local.LocalDataSource(get()) }
    single { RemoteDataSource(get()) }
    single<IAnimeRepository> { com.alwan.made1.core.data.AnimeRepository(get(), get()) }
}