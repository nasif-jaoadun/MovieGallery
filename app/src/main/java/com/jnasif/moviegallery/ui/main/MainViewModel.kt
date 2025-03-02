package com.jnasif.moviegallery.ui.main

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import com.jnasif.moviegallery.LOG_TAG
import com.jnasif.moviegallery.data.MovieRepository
import com.jnasif.moviegallery.data.Movies
import com.jnasif.moviegallery.utilities.FileHelper
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory

class MainViewModel(app : Application) : AndroidViewModel(app) {
    private val dataRepo = MovieRepository()
    init {
        val movieData = dataRepo.getMovieData(app)
        for (movie in movieData){
            Log.i(LOG_TAG, "${movie.title} (\$${movie.vote_count})")
        }
    }


}