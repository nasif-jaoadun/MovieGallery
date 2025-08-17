package com.jnasif.moviegallery.data

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface ContentService {
    @GET("/3/discover/movie")
    suspend fun getMovieData(@Header("Authorization") token : String, @Query("page") page : Int) : Response<Movies>
}