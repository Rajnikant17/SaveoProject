package com.example.saveo.viewmodels

import android.app.Application
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.myapiservicesmodule.di.models.ResponseMovieDetails
import com.example.saveo.utils.DataState
import com.example.saveo.useCases.MoviesDetailUsecase
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import com.example.saveo.utils.MainStateEvent

class MovieDetailsViewModel @ViewModelInject
constructor(application: Application, private val moviesDetailUsecase: MoviesDetailUsecase) :
    AndroidViewModel(application) {
    private val movieDetailsMutaleLiveData: MutableLiveData<DataState<ResponseMovieDetails?>> = MutableLiveData()
    val movieDetailsLiveData:  LiveData<DataState<ResponseMovieDetails?>>
        get() = movieDetailsMutaleLiveData
    fun getMovieDetails(mainStateEvent: MainStateEvent, imdb_id: String?) {
        viewModelScope.launch {
            when (mainStateEvent) {
                is MainStateEvent.GetMovieDetailsResponse -> {
                    moviesDetailUsecase.executeMovieDetails(imdb_id).onEach { dataState ->
                        movieDetailsMutaleLiveData.value = dataState
                    }.launchIn(viewModelScope)
                }
            }
        }
    }
}