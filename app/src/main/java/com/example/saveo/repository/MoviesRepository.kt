package com.example.saveo.repository

import com.example.myapiservicesmodule.di.models.ResponseMovieDetails
import com.example.myapiservicesmodule.di.models.ResponseMovieList
import com.example.saveo.utils.DataState

interface MoviesRepository {
    suspend fun getMovieList(key: String, movie_type: String, page: Int, apikey: String): DataState<ResponseMovieList?>
    suspend fun getMovieDetails(id: String?, apikey: String): DataState<ResponseMovieDetails?>
}