package com.jnasif.moviegallery.ui.main

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import com.jnasif.moviegallery.LOG_TAG
import com.jnasif.moviegallery.R
import com.jnasif.moviegallery.utilities.FileHelper

class MainViewModel(app : Application) : AndroidViewModel(app) {
    init {
//        val text = FileHelper.getTextFromResources(app,R.raw.tmdb_movie_discovery)
        val text = FileHelper.getTextFromAssets(app,"tmdb_movie_discovery.json")
        Log.i(LOG_TAG, text)
    }
}