package com.jnasif.moviegallery.data

import android.content.Context
import com.jnasif.moviegallery.utilities.FileHelper
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory

class MovieRepository {

    fun getMovieData(context : Context) : List<MovieDetails>{
//        val text = FileHelper.getTextFromResources(app,R.raw.tmdb_movie_discovery)
        val text = FileHelper.getTextFromAssets(context,"tmdb_movie_discovery.json")
        val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
        val jsonAdapter = moshi.adapter(Movies::class.java)
        val movieJson = jsonAdapter.fromJson(text)
        return movieJson?.listOfMoviesWithDetails ?: emptyList()
    }
}