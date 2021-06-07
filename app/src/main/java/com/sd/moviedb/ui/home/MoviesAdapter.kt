package com.sd.moviedb.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.sd.moviedb.R
import com.sd.moviedb.databinding.ListItemMoviesBinding
import com.sd.moviedb.model.Movies

class MoviesAdapter() : PagingDataAdapter<Movies, MoviesAdapter.ViewHolder>(DiffCallback()) {


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.movie = getItem(position)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.list_item_movies,
                parent,
                false
            )
        )
    }

    inner class ViewHolder(val binding: ListItemMoviesBinding) :
        RecyclerView.ViewHolder(binding.root)


}

private class DiffCallback : DiffUtil.ItemCallback<Movies>() {

    override fun areItemsTheSame(oldItem: Movies, newItem: Movies): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Movies, newItem: Movies): Boolean {
        return oldItem == newItem
    }
}