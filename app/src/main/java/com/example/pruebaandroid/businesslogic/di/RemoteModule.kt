package com.example.pruebaandroid.businesslogic.di

import android.content.Context
import com.example.pruebaandroid.BuildConfig
import com.example.pruebaandroid.businesslogic.api.MovieDataBaseApiService
import com.example.pruebaandroid.businesslogic.database.MovieDatabase
import com.example.pruebaandroid.businesslogic.database.dao.PopularMovieDao
import com.example.pruebaandroid.businesslogic.respositories.PopularMovieApiRepo
import com.example.pruebaandroid.businesslogic.respositories.PopularMovieApiRepoImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RemoteModule {

    @Singleton
    @Provides
    fun providesRetrofitClient(): Retrofit {
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY

        val httpClient = OkHttpClient.Builder()

        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BuildConfig.SERVER_URL)
            .client(httpClient.build()).build()
    }

    @Provides
    fun providesApiService(retrofit: Retrofit): MovieDataBaseApiService =
        retrofit.create(MovieDataBaseApiService::class.java)

    @Provides
    fun providesPopularMovieApiRepo(remoteService: MovieDataBaseApiService): PopularMovieApiRepo =
        PopularMovieApiRepoImpl(remoteService)

    @Singleton
    @Provides
    fun providesRoomDatabase(@ApplicationContext appContext: Context): MovieDatabase =
        MovieDatabase.getDatabase(appContext)

    @Provides
    fun providesPopularMovieDao(database: MovieDatabase): PopularMovieDao =
        database.popularMovieDao()
}