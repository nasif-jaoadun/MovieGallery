package com.jnasif.moviegallery.ui.main

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import com.jnasif.moviegallery.LOG_TAG
import com.jnasif.moviegallery.data.MovieRepository

class MainViewModel(app : Application) : AndroidViewModel(app) {
    private val dataRepo = MovieRepository(app)
    val movieDetailsData = dataRepo.movieDetailsData
}