package com.jnasif.moviegallery.data

import android.app.Application
import android.content.Context
import androidx.lifecycle.MutableLiveData
import com.jnasif.moviegallery.utilities.FileHelper
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory

class MovieRepository(val app : Application) {
    val movieDetailsData = MutableLiveData<List<MovieDetails>>()
    init {
        getMovieData()
    }
    fun getMovieData(){
//        val text = FileHelper.getTextFromResources(app,R.raw.tmdb_movie_discovery)
        val text = FileHelper.getTextFromAssets(app,"tmdb_movie_discovery.json")
        val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
        val jsonAdapter = moshi.adapter(Movies::class.java)
        val movieJson = jsonAdapter.fromJson(text)
        movieDetailsData.value = movieJson?.listOfMoviesWithDetails ?: emptyList()
    }
}