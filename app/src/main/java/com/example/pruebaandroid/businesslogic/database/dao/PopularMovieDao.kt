package com.example.pruebaandroid.businesslogic.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.pruebaandroid.model.PopularMovie
import kotlinx.coroutines.flow.Flow

@Dao
interface PopularMovieDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(movie: PopularMovie)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertList(list: List<PopularMovie>)

    @Query("SELECT * FROM popular_movies")
    fun getPopularMovies(): Flow<List<PopularMovie>>
}