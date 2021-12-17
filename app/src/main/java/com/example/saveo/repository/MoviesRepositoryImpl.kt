package com.example.saveo.repository

import com.example.myapiservicesmodule.di.models.ResponseMovieDetails
import com.example.myapiservicesmodule.di.models.ResponseMovieList
import com.example.saveo.utils.DataState

class MoviesRepositoryImpl
constructor(val moviesDataSource: MoviesDataSource)
    :MoviesRepository {
    override suspend fun getMovieList(
        key: String,
        movie_type: String,
        page: Int,
        apikey: String
    ): DataState<ResponseMovieList?> {
        return moviesDataSource.getMoviesList(key,movie_type,page,apikey)
    }

    override suspend fun getMovieDetails(
        id: String?,
        apikey: String
    ): DataState<ResponseMovieDetails?> {
        return moviesDataSource.getMoviesDeatail(id,apikey)
    }
}