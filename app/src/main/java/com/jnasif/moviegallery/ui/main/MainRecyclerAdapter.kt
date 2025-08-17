package com.jnasif.moviegallery.ui.main

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.jnasif.moviegallery.R
import com.jnasif.moviegallery.data.MovieDetails

class MainRecyclerAdapter (val context : Context, val moviesDetail : List<MovieDetails>, val movieItemListener: MovieItemListener) : RecyclerView.Adapter<MainRecyclerAdapter.ViewHolder>() {

    inner class ViewHolder(itemview : View) : RecyclerView.ViewHolder(itemview){
        val nameText = itemview.findViewById<TextView>(R.id.nameText)
        val moviePosterImage = itemview.findViewById<ImageView>(R.id.moviePosterImage)
        val ratingBar = itemview.findViewById<RatingBar>(R.id.ratingBar)
    }

    override fun getItemCount(): Int = moviesDetail.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.movie_grid_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val movieDetails = moviesDetail[position]
        with(holder) {
            nameText?.let {
                it.text = movieDetails.title
                it.contentDescription = movieDetails.original_title
            }
            ratingBar?.rating = movieDetails.vote_average/2
            Glide.with(context).load(movieDetails.posterUrl).into(moviePosterImage)
            holder.itemView.setOnClickListener{
                movieItemListener.onMovieItemClick(movieDetails)
            }
        }
    }

    interface MovieItemListener{
        fun onMovieItemClick(movieDetails: MovieDetails)
    }
}