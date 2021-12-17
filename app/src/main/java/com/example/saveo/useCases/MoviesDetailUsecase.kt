package com.example.saveo.useCases

import com.example.myapiservicesmodule.di.Constants
import com.example.myapiservicesmodule.di.models.ResponseMovieDetails
import com.example.saveo.utils.DataState
import com.example.saveo.repository.MoviesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MoviesDetailUsecase
@Inject
constructor(val moviesRepository: MoviesRepository) {
    suspend fun executeMovieDetails(imdb_id: String?): Flow<DataState<ResponseMovieDetails?>> = flow {
        emit(DataState.Loading)
        val response= moviesRepository.getMovieDetails(
            imdb_id,
            Constants.apiKey
        )
        when (response.statusCode) {
            200 -> emit(DataState.Success(response.data, response.statusCode))
            else -> emit(DataState.Error(response.statusCode, null))
        }
    }
}
