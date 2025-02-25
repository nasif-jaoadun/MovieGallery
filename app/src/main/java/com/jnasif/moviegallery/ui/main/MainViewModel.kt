package com.jnasif.moviegallery.ui.main

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import com.jnasif.moviegallery.LOG_TAG
import com.jnasif.moviegallery.data.MovieDetails
import com.jnasif.moviegallery.data.Movies
import com.jnasif.moviegallery.utilities.FileHelper
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types

class MainViewModel(app : Application) : AndroidViewModel(app) {
    init {
//        val text = FileHelper.getTextFromResources(app,R.raw.tmdb_movie_discovery)
        val text = FileHelper.getTextFromAssets(app,"tmdb_movie_discovery.json")
        parseText(text)
    }

    fun parseText(text : String){
        val moshi = Moshi.Builder().build()
        val jsonAdapter = moshi.adapter(Movies::class.java)
        val movieJson = jsonAdapter.fromJson(text)
        val movieData = movieJson?.results
        for (movie in movieData ?: emptyList()){
            Log.i(LOG_TAG, "${movie.title} (\$${movie.vote_count})")
        }
    }
}