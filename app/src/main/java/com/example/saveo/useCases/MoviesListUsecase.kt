package com.example.saveo.useCases

import com.example.myapiservicesmodule.di.Constants
import com.example.myapiservicesmodule.di.models.ResponseMovieList
import com.example.saveo.utils.DataState
import com.example.saveo.repository.MoviesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MoviesListUsecase
@Inject
constructor(val moviesRepository: MoviesRepository) {
    suspend fun executeMovieList(page_no: Int): Flow<DataState<ResponseMovieList?>> = flow {
        emit(DataState.Loading)
        val response= moviesRepository.getMovieList(
            Constants.key,
            Constants.movie_type,
            page_no,
            Constants.apiKey
        )
        when (response.statusCode) {
            200 -> emit(DataState.Success(response.data, response.statusCode))
            else -> emit(DataState.Error(response.statusCode, null))
        }
    }
}


















//@Singleton
//class BusinessLogic
//@Inject
//constructor(val apiServices: ApiServices) {
//    suspend fun executeMovieList(page_no: Int): Flow<DataState<ResponseMovieList>> = flow {
//
//        emit(DataState.Loading)
//        try {
//            val modelMovieListResponse =
//                apiServices.getMovieList(
//                    Constants.key,
//                    Constants.movie_type,
//                    page_no,
//                    Constants.apiKey
//                )
//            emit(DataState.Success(modelMovieListResponse))
//        } catch (e: Exception) {
//            emit(DataState.Error(e))
//        }
//    }
//
//    suspend fun executeMovieDetails(imdb_id: String?): Flow<DataState<ResponseMovieDetails>> =
//        flow {
//            emit(DataState.Loading)
//            try {
//                val modelMovieDetailsResponse =
//                    apiServices.getMovieDetails(imdb_id, Constants.apiKey)
//                emit(DataState.Success(modelMovieDetailsResponse))
//            } catch (e: Exception) {
//                emit(DataState.Error(e))
//            }
//        }
//}