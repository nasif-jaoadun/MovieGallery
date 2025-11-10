package com.jnasif.moviegallery.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface MovieDetailsDao {

    @Query("SELECT * from movies")
    fun getAll() : List<MovieDetails>

    @Insert
    suspend fun insertMovie(movie : MovieDetails)

    @Insert
    suspend fun insertMovies(movies : List<MovieDetails>)

    @Query("DELETE from movies")
    suspend fun deleteAll()
}