package com.jnasif.moviegallery.ui.shared

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.jnasif.moviegallery.data.MovieDetails
import com.jnasif.moviegallery.data.MovieRepository

class SharedViewModel(app : Application) : AndroidViewModel(app) {

    private val dataRepo = MovieRepository(app)
    val movieDetailsData = dataRepo.movieDetailsData
    val selectedMovieDetails = MutableLiveData<MovieDetails>()
    fun refreshData() {
        dataRepo.refreshData()
    }
}