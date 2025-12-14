package com.jnasif.moviegallery.ui.shared

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.preference.PreferenceManager
import com.jnasif.moviegallery.data.MovieDetails
import com.jnasif.moviegallery.data.MovieRepository

class SharedViewModel(val app : Application) : AndroidViewModel(app) {

    private val dataRepo = MovieRepository(app)
    val movieDetailsData = dataRepo.movieDetailsData
    val selectedMovieDetails = MutableLiveData<MovieDetails>()
    val activityTitle = MutableLiveData<String>()

    init {
        updateActivityTitle()
    }
    fun refreshData() {
        dataRepo.refreshDataFromWeb()
    }

    fun updateActivityTitle(){
        val signature = PreferenceManager.getDefaultSharedPreferences(app).getString("signature", "Monster fan")
        activityTitle.value = signature
    }
}