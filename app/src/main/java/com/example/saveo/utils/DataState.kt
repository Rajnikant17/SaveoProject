package com.example.saveo.utils

sealed class DataState <out T> (val data: T?=null,val statusCode:Int=0){
    class Success<T>(data: T, statusCode:Int=0) : DataState<T>(data,statusCode)
    class Error<T>(statusCode:Int=0, data: T?=null) : DataState<T>(data,statusCode)
    object Loading : DataState<Nothing>()
}