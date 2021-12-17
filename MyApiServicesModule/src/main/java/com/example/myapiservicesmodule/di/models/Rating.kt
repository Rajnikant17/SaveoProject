package com.example.myapiservicesmodule.di.models

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName

data class Rating(
    @SerializedName("Source")
    @Expose
    private var source: String? = null ,
    @SerializedName("Value")
    @Expose
    private var value: String? = null
)
