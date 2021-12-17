package com.example.saveo.utils

sealed class MainStateEvent {
    object GetMovieListResponse : MainStateEvent()
    object GetMovieDetailsResponse : MainStateEvent()
}