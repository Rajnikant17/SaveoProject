package com.example.saveo.repository

import com.example.saveo.utils.BaseDataSource
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MoviesDataSource
@Inject
constructor(val apiServices: ApiServices) : BaseDataSource() {
    suspend fun getMoviesList(key: String, movie_type: String, page: Int, apikey: String) = invoke { apiServices.getMovieList(key,movie_type,page,apikey) }
    suspend fun getMoviesDeatail( id: String?,apikey: String) = invoke { apiServices.getMovieDetails(id,apikey) }
}