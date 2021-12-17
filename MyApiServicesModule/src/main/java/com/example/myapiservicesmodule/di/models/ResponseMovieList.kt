package com.example.myapiservicesmodule.di.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class ResponseMovieList (@SerializedName("Search") @Expose var movieList:List<Movie>?=null)