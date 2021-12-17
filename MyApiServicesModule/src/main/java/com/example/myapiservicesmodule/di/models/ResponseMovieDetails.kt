package com.example.myapiservicesmodule.di.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
data class ResponseMovieDetails(
    @SerializedName("Title")
    @Expose
    var title: String? = null ,

    @SerializedName("Year")
    @Expose
    var year: String? = null,

    @SerializedName("Released")
    @Expose
     var released: String? = null,

    @SerializedName("Runtime")
    @Expose
    var runtime: String? = null,

    @SerializedName("Genre")
    @Expose
    var genre: String? = null,

    @SerializedName("Director")
    @Expose
    var director: String? = null,

    @SerializedName("Actors")
    @Expose
    var actors: String? = null,

    @SerializedName("Language")
    @Expose
    var language: String? = null,

    @SerializedName("Poster")
    @Expose
    var poster: String? = null,

    @SerializedName("imdbRating")
    @Expose
    var imdbRating: String? = null,

    @SerializedName("imdbID")
    @Expose
    var imdbID: String? = null,

    @SerializedName("Type")
    @Expose
    var type: String? = null,

    @SerializedName("BoxOffice")
    @Expose
    var boxOffice: String? = null,

    @SerializedName("Production")
    @Expose
    var production: String? = null,

    @SerializedName("Writer")
    @Expose
    var Writer: String? = null,

    @SerializedName("Response")
    @Expose
    var response: String? = null,

    @SerializedName("Plot")
    @Expose
    var plot: String? = null



)
