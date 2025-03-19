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
import com.jnasif.moviegallery.databinding.FragmentMainBinding

class MainFragment : Fragment() {
    private lateinit var binding : FragmentMainBinding

    companion object{
        fun newInstance() = MainFragment()
    }

    private lateinit var viewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMainBinding.inflate(inflater, container, false)
        val root: View = binding.root
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        viewModel.movieDetailsData.observe(viewLifecycleOwner, Observer {
            val movieNames = StringBuilder()
            for (movie in it){
                movieNames.append(movie.title).append("  ").append(movie.vote_count).append("\n")
            }
            binding.message.text = movieNames
        })
        return root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        // TODO: Use the ViewModel
    }

}