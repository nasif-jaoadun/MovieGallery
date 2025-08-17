package com.jnasif.moviegallery.data

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.jnasif.moviegallery.LOG_TAG
import com.jnasif.moviegallery.PAGE_COUNT
import com.jnasif.moviegallery.TOKEN
import com.jnasif.moviegallery.WEB_SERVICE_URL
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class MovieRepository(val app : Application) {
    val movieDetailsData = MutableLiveData<List<MovieDetails>>()
    init {
        refreshData()
    }
    suspend fun callWebService(){
        if (networkAvailable()){
            Log.i(LOG_TAG, "Calling web service")
            val retrofit = Retrofit.Builder().baseUrl(WEB_SERVICE_URL).addConverterFactory(
                MoshiConverterFactory.create()).build()
            val service = retrofit.create(ContentService::class.java)
            val serviceData = service.getMovieData(TOKEN, PAGE_COUNT).body()?.listOfMoviesWithDetails ?: emptyList()
            movieDetailsData.postValue(serviceData)
        }
    }

    @Suppress("DEPRECATION")
    private fun networkAvailable(): Boolean {
        val connectivityManager = app.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo = connectivityManager.activeNetworkInfo
        return networkInfo?.isConnectedOrConnecting ?: false
    }

    fun refreshData() {
        CoroutineScope(Dispatchers.IO).launch {
            callWebService()
        }
    }
}