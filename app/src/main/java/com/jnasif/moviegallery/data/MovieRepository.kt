package com.jnasif.moviegallery.data

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.jnasif.moviegallery.LOG_TAG
import com.jnasif.moviegallery.utilities.FileHelper
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory

class MovieRepository(val app : Application) {
    val movieDetailsData = MutableLiveData<List<MovieDetails>>()
    init {
        getMovieData()
        Log.i(LOG_TAG, "Network Available: ${networkAvailable()}")
    }
    fun getMovieData(){
//        val text = FileHelper.getTextFromResources(app,R.raw.tmdb_movie_discovery)
        val text = FileHelper.getTextFromAssets(app,"tmdb_movie_discovery.json")
        val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
        val jsonAdapter = moshi.adapter(Movies::class.java)
        val movieJson = jsonAdapter.fromJson(text)
        movieDetailsData.value = movieJson?.listOfMoviesWithDetails ?: emptyList()
    }

    @Suppress("DEPRECATION")
    private fun networkAvailable(): Boolean {
        val connectivityManager = app.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo = connectivityManager.activeNetworkInfo
        return networkInfo?.isConnectedOrConnecting ?: false
    }
}