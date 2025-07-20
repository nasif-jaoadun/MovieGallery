package com.jnasif.moviegallery.data

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.jnasif.moviegallery.LOG_TAG
import com.jnasif.moviegallery.WEB_SERVICE_URL
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class MovieRepository(val app : Application) {
    val movieDetailsData = MutableLiveData<List<MovieDetails>>()
    init {
        CoroutineScope(Dispatchers.IO).launch {
            callWebService()
        }
    }
    suspend fun callWebService(){
        if (networkAvailable()){
            val retrofit = Retrofit.Builder().baseUrl(WEB_SERVICE_URL).addConverterFactory(
                MoshiConverterFactory.create()).build()
            val service = retrofit.create(ContentService::class.java)
            val serviceData = service.getMovieData().body()?.listOfMoviesWithDetails ?: emptyList()
            movieDetailsData.postValue(serviceData)
        }
    }

    @Suppress("DEPRECATION")
    private fun networkAvailable(): Boolean {
        val connectivityManager = app.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo = connectivityManager.activeNetworkInfo
        return networkInfo?.isConnectedOrConnecting ?: false
    }
}