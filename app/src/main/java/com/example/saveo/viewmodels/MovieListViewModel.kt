package com.example.saveo.viewmodels

import android.app.Application
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.myapiservicesmodule.di.models.Movie
import com.example.myapiservicesmodule.di.models.ResponseMovieList
import com.example.saveo.useCases.MoviesListUsecase
import com.example.saveo.utils.DataState
import com.example.saveo.utils.MainStateEvent
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

class MovieListViewModel @ViewModelInject
constructor(application: Application, private val apiCallMoviesListUsecase: MoviesListUsecase) :
    AndroidViewModel(application) {
    var page_count = 0
    val max_page_count = 10
    var viewPagerAlreadySet=false
    val finalmovieList = ArrayList<Movie>()
    val moviesViewpagerList = ArrayList<String>()
    private val movieMutableListLiveData: MutableLiveData<DataState<ResponseMovieList?>> = MutableLiveData()
    val movieListLiveData: LiveData<DataState<ResponseMovieList?>>
        get() = movieMutableListLiveData

    fun getMovieList(mainStateEvent: MainStateEvent) {
        page_count++
        viewModelScope.launch {
            when (mainStateEvent) {
                is MainStateEvent.GetMovieListResponse -> {
                    if (page_count <= max_page_count) {
                        apiCallMoviesListUsecase.executeMovieList(page_count).onEach { dataState ->
                            movieMutableListLiveData.value = dataState
                        }.launchIn(viewModelScope)
                    }
                }
            }
        }
    }

    fun setDataForViewPager(){
        // only upto 4 data is shown in Viewpager.
        run loop@{
            finalmovieList.forEach {
                if(moviesViewpagerList.size>3) return@loop
                it.Poster?.let { it1 -> moviesViewpagerList.add(it1) }
            }
        }
    }

}