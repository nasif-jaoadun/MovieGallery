package com.jnasif.moviegallery.data

import androidx.room.Insert
import androidx.room.Query

interface MovieDetailsDao {

    @Query("SELECT * from movie_with_details")
    fun getAll() : List<MovieDetails>

    @Insert
    suspend fun insertMovie(movie : MovieDetails)

    @Insert
    suspend fun insertMovies(movies : List<MovieDetails>)

    @Query("DELETE from movie_with_details")
    suspend fun deleteAll()
}