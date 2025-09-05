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
import com.jnasif.moviegallery.utilities.FileHelper
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class MovieRepository(val app : Application) {
    val movieDetailsData = MutableLiveData<List<MovieDetails>>()
    init {
        val data = readDataFromCache()
        if (data.isEmpty()){
            refreshDataFromWeb()
        } else{
            movieDetailsData.value = data
            Log.i(LOG_TAG, "Using local data")
        }
    }
    suspend fun callWebService(){
        if (networkAvailable()){
            Log.i(LOG_TAG, "Calling web service")
            val retrofit = Retrofit.Builder().baseUrl(WEB_SERVICE_URL).addConverterFactory(
                MoshiConverterFactory.create()).build()
            val service = retrofit.create(ContentService::class.java)
            val serviceData = service.getMovieData(TOKEN, PAGE_COUNT).body()?.listOfMoviesWithDetails ?: emptyList()
            movieDetailsData.postValue(serviceData)
            saveMovieDetailDataToCache(serviceData)
        }
    }

    @Suppress("DEPRECATION")
    private fun networkAvailable(): Boolean {
        val connectivityManager = app.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo = connectivityManager.activeNetworkInfo
        return networkInfo?.isConnectedOrConnecting ?: false
    }

    fun refreshDataFromWeb() {
        CoroutineScope(Dispatchers.IO).launch {
            callWebService()
        }
    }

    private fun saveMovieDetailDataToCache(movieDetailData: List<MovieDetails>){
        val moshi = Moshi.Builder().build()
        val listType = Types.newParameterizedType(List::class.java, MovieDetails::class.java)
        val adapter: JsonAdapter<List<MovieDetails>> = moshi.adapter(listType)
        val json = adapter.toJson(movieDetailData)
        FileHelper.saveTextToFile(app, json)
    }

    private fun readDataFromCache() : List<MovieDetails>{
        val json= FileHelper.readTextFile(app)
        if (json == null){
            return emptyList()
        }
        val moshi= Moshi.Builder().build()
        val listType = Types.newParameterizedType(List::class.java, MovieDetails::class.java)
        val adapter: JsonAdapter<List<MovieDetails>> = moshi.adapter(listType)
        return adapter.fromJson(json)?: emptyList()
    }
}