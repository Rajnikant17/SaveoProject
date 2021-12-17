package com.example.saveo.ui

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import com.example.myapiservicesmodule.di.models.ResponseMovieDetails
import com.example.saveo.R
import com.example.saveo.databinding.FragmentMovieDetailsBinding
import com.example.saveo.utils.MainStateEvent
import com.example.saveo.viewmodels.MovieDetailsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MovieDetailsFragment : BaseFragment<FragmentMovieDetailsBinding>() {
    override val fragmentLayoutResId: Int
        get() = R.layout.fragment_movie_details
    val movieDetailsViewModel: MovieDetailsViewModel by viewModels()
    var imdb_id: String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            imdb_id = it.getString("imdb_id")
        }
        movieDetailsViewModel.getMovieDetails(
            MainStateEvent.GetMovieDetailsResponse,
            imdb_id
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as AppCompatActivity?)!!.supportActionBar!!.title = getString(R.string.movie_detail)
        observeMovieDetailsDataFromApi()
    }

    fun observeMovieDetailsDataFromApi() {
        movieDetailsViewModel.movieDetailsLiveData.observe(viewLifecycleOwner, observer)
    }

    fun setDataInViews(movieDetails: ResponseMovieDetails) {
        Glide.with(requireActivity()).load(movieDetails.poster).error(R.drawable.placeholder)
            .into(binding.ivPoster)
        binding.tvMovieName.text = movieDetails.title
        binding.tvDirector.text = getString(R.string.directed_by)+movieDetails.director
        binding.tvGenre.text = movieDetails.genre
        binding.tvSynopsis.text=movieDetails.plot

    }

    override fun receivedResponse(item: Any?) {
        item?.let { response ->
            when (response) {
                is ResponseMovieDetails -> {
                    setDataInViews(response)
                }
                else -> {
                }
            }
        }
    }
}