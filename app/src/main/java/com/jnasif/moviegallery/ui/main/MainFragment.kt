package com.jnasif.moviegallery.ui.main

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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
        val movie = MovieDetails(false, "\"/6VoxDupaW2VXfLtJyeOoGCgXSjD.jpg\"",
            intArrayOf(1,2,3), 1, "en", "Venom : The last dance","no overview",500.12,"\"/6VoxDupaW2VXfLtJyeOoGCgXSjD.jpg\"","02-05-20024","Venom: The last dance",false,6.288,5000)
        Log.i("Monster Tag", movie.toString())
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        // TODO: Use the ViewModel
    }

}