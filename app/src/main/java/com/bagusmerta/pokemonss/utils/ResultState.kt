package com.bagusmerta.pokemonss.utils

sealed class ResultState<out T: Any>{
    data class Success<out T: Any>(val data: T?): ResultState<T>()
    data class Error(val errMessage: String?): ResultState<Nothing>()
}
