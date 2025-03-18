package com.jnasif.moviegallery.ui.main

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.jnasif.moviegallery.LOG_TAG
import com.jnasif.moviegallery.R
import com.jnasif.moviegallery.data.MovieDetails

class MainFragment : Fragment() {

    companion object{
        fun newInstance() = MainFragment()
    }

    private lateinit var viewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        viewModel.movieDetailsData.observe(this, Observer {
            for (movie in it){
                Log.i(LOG_TAG, "${movie.title} (\$${movie.vote_count})")
            }
        })
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        // TODO: Use the ViewModel
    }

}