package com.example.pruebaandroid.businesslogic.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.pruebaandroid.businesslogic.database.dao.PopularMovieDao
import com.example.pruebaandroid.model.PopularMovie

@Database(entities = [PopularMovie::class], version = 1, exportSchema = false)
abstract class MovieDatabase : RoomDatabase() {
    abstract fun popularMovieDao(): PopularMovieDao

    companion object {
        @Volatile
        private var INSTANCE: MovieDatabase? = null

        fun getDatabase(context: Context): MovieDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context,
                    MovieDatabase::class.java,
                    "movie_database"
                ).build()

                INSTANCE = instance
                instance
            }
        }
    }
}