package com.jnasif.moviegallery.data

import retrofit2.Response
import retrofit2.http.GET

interface ContentService {
    @GET("/3/discover/movie")
    suspend fun getMovieData() : Response<Movies>
}