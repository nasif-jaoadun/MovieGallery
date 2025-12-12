package com.jnasif.moviegallery.ui.main

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.jnasif.moviegallery.LAYOUT_TYPE_GRID
import com.jnasif.moviegallery.LAYOUT_TYPE_LIST
import com.jnasif.moviegallery.LOG_TAG
import com.jnasif.moviegallery.PAGE_COUNT
import com.jnasif.moviegallery.R
import com.jnasif.moviegallery.data.MovieDetails
import com.jnasif.moviegallery.databinding.FragmentMainBinding
import com.jnasif.moviegallery.ui.shared.SharedViewModel
import com.jnasif.moviegallery.utilities.PrefsHelper

class MainFragment : Fragment(), MainRecyclerAdapter.MovieItemListener {
    private lateinit var binding : FragmentMainBinding

    companion object{
        fun newInstance() = MainFragment()
    }

    private lateinit var viewModel: SharedViewModel
    private lateinit var navController: NavController
    private lateinit var adapter : MainRecyclerAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        (requireActivity() as AppCompatActivity).run {
            supportActionBar?.setDisplayHomeAsUpEnabled(false)
        }
        binding = FragmentMainBinding.inflate(inflater, container, false)
        val root: View = binding.root
        val layoutStyle = PrefsHelper.getItemType(requireContext())
        binding.recyclerView.layoutManager =
            if(layoutStyle == LAYOUT_TYPE_GRID){
                GridLayoutManager(requireContext(),2)
            } else {
                LinearLayoutManager(requireContext())
            }
        navController = findNavController()
        binding.refreshLayout.setOnRefreshListener {
            viewModel.refreshData()
        }
        viewModel = ViewModelProvider(requireActivity()).get(SharedViewModel::class.java)
        viewModel.movieDetailsData.observe(viewLifecycleOwner, Observer {
            adapter = MainRecyclerAdapter(requireActivity(), it, this)
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

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.options_main , menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId){
            R.id.action_view_grid -> {
                PrefsHelper.setItemType(requireContext(), LAYOUT_TYPE_GRID)
                binding.recyclerView.layoutManager = GridLayoutManager(requireContext(), 2)
                binding.recyclerView.adapter = adapter
            }
            R.id.action_view_list -> {
                PrefsHelper.setItemType(requireContext(), LAYOUT_TYPE_LIST)
                binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
                binding.recyclerView.adapter = adapter
            }
            R.id.action_settings -> {
                navController.navigate(R.id.settingsActivity)
            }
        }
        return true
    }

}