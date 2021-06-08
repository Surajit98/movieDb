package com.sd.moviedb.ui.favourites

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.sd.moviedb.R
import com.sd.moviedb.callback.MovieItemClickListener
import com.sd.moviedb.databinding.ListItemMoviesBinding
import com.sd.moviedb.model.Movies

class FavMoviesAdapter(private val mListener: MovieItemClickListener) :
    RecyclerView.Adapter<FavMoviesAdapter.ViewHolder>() {

    private val movies = arrayListOf<Movies>()

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.movie = movies[position]
        holder.binding.chkLike.setOnClickListener {
            mListener.toggleLike(movies[position])
        }
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


    override fun getItemCount(): Int {
        return movies.size
    }


    fun updateList(list: List<Movies>) {
        val diffUtil = DiffCallback(movies, list)
        val diffResult = DiffUtil.calculateDiff(diffUtil)
        movies.clear()
        movies.addAll(list)
        diffResult.dispatchUpdatesTo(this)
    }

    inner class ViewHolder(val binding: ListItemMoviesBinding) :
        RecyclerView.ViewHolder(binding.root)


}

private class DiffCallback(private val oldList: List<Movies>, private val newList: List<Movies>) :
    DiffUtil.Callback() {

    override fun getOldListSize(): Int = oldList.size

    override fun getNewListSize(): Int = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].id == newList[newItemPosition].id
    }

    override fun areContentsTheSame(oldPosition: Int, newPosition: Int): Boolean {
        val old = oldList[oldPosition]
        val new = newList[newPosition]
        return old.title == new.title && old.favourite == new.favourite
    }
}