package com.example.myapiservicesmodule.di.models
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

 class Movie{
    @SerializedName("Title")
    @Expose
    var Title :String?=null
    @SerializedName("imdbID")
    @Expose
    var imdbID :String?=null
    @SerializedName("Year")
    @Expose
    var Year :String?=null
    @SerializedName("Poster")
    @Expose
    var Poster :String?=null
}
