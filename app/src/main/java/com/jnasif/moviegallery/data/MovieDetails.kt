package com.jnasif.moviegallery.data

import com.jnasif.moviegallery.IMAGE_BASE_URL

data class MovieDetails(
    val adult: Boolean,
    val backdrop_path: String,
    val genre_ids: IntArray,
    val id: Int,
    val original_language: String,
    val original_title: String,
    val overview: String,
    val popularity: Double,
    val poster_path: String,
    val release_date: String,
    val title: String,
    val video: Boolean,
    val vote_average: Float,
    val vote_count: Long
){
    val backdropUrl
        get() = "$IMAGE_BASE_URL/$backdrop_path.jpg"
    val posterUrl
        get() = "$IMAGE_BASE_URL/$poster_path.jpg"
}