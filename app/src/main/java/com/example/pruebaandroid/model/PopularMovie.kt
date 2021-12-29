package com.example.pruebaandroid.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.pruebaandroid.businesslogic.utils.IMAGE_URL
import com.google.gson.annotations.SerializedName

@Entity(tableName = "popular_movies")
data class PopularMovie(
    @SerializedName("id") @PrimaryKey val id: Int,
    @SerializedName("title") @ColumnInfo(name = "title") val title: String,
    @SerializedName("poster_path") @ColumnInfo(name = "poster_path") val posterPath: String
)

fun PopularMovie.posterUrl(): String = IMAGE_URL.POSTER_W500.url + posterPath
