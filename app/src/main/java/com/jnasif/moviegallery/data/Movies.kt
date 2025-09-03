package com.jnasif.moviegallery.data

import com.squareup.moshi.Json

data class Movies (
    val page : Int,
    @field:Json(name = "results") val listOfMoviesWithDetails : List<MovieDetails>,
    val total_pages : Long,
    val total_results : Long
)