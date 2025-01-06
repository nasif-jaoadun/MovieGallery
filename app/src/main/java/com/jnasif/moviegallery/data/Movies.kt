package com.jnasif.moviegallery.data

class Movies (
    val page : Int,
    val results : List<MovieDetails>,
    val total_pages : Long,
    val total_results : Long
)