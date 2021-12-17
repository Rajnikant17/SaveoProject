package com.example.saveo.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.myapiservicesmodule.di.models.Movie
import com.example.saveo.BR
import com.example.saveo.R
import com.example.saveo.databinding.MovieListAdapterBinding

class MovieListAdapter(
    val context: Context,
    var movieListList: List<Movie>,
    val clickEventInterface: ClickEventInterface
) : RecyclerView.Adapter<MovieListAdapter.Myhandler>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Myhandler {
        val movieListAdapterBinding =
            MovieListAdapterBinding.inflate(LayoutInflater.from(context), parent, false)
        return Myhandler(movieListAdapterBinding)
    }

    override fun onBindViewHolder(holder: Myhandler, position: Int) {
        holder.binding.cvImage.setOnClickListener {
            clickEventInterface.clickEvent(position)
        }
        Glide.with(context).load(movieListList.get(position).Poster)
            .error(R.color.purple_light).into(holder.binding.iv1)
        holder.bind(movieListList.get(position))
    }

    override fun getItemCount(): Int {
        return movieListList.size
    }

    class Myhandler(val binding: MovieListAdapterBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(movie: Movie) {
            binding.movieList = movie
            binding.setVariable(BR.movieList, movie)
            binding.executePendingBindings()
        }
    }

    interface ClickEventInterface {
        fun clickEvent(position: Int)
    }
}