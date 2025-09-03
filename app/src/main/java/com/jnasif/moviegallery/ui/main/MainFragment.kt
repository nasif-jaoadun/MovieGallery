package com.jnasif.moviegallery.ui.main

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.jnasif.moviegallery.LOG_TAG
import com.jnasif.moviegallery.PAGE_COUNT
import com.jnasif.moviegallery.R
import com.jnasif.moviegallery.data.MovieDetails
import com.jnasif.moviegallery.databinding.FragmentMainBinding
import com.jnasif.moviegallery.ui.shared.SharedViewModel

class MainFragment : Fragment(), MainRecyclerAdapter.MovieItemListener {
    private lateinit var binding : FragmentMainBinding

    companion object{
        fun newInstance() = MainFragment()
    }

    private lateinit var viewModel: SharedViewModel
    private lateinit var navController: NavController

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        (requireActivity() as AppCompatActivity).run {
            supportActionBar?.setDisplayHomeAsUpEnabled(false)
        }
        binding = FragmentMainBinding.inflate(inflater, container, false)
        val root: View = binding.root
        navController = findNavController()
        binding.refreshLayout.setOnRefreshListener {
            viewModel.refreshData()
        }
        viewModel = ViewModelProvider(requireActivity()).get(SharedViewModel::class.java)
        viewModel.movieDetailsData.observe(viewLifecycleOwner, Observer {
            val adapter = MainRecyclerAdapter(requireActivity(), it, this)
            binding.recyclerView.adapter = adapter
            binding.refreshLayout.isRefreshing = false
            PAGE_COUNT += 1
        })
        return root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        // TODO: Use the ViewModel
    }

    override fun onMovieItemClick(movieDetails: MovieDetails) {
        Log.i(LOG_TAG, "Selected Movie: ${movieDetails.title}")
        viewModel.selectedMovieDetails.value = movieDetails
        navController.navigate(R.id.action_nav_detail)
    }

}