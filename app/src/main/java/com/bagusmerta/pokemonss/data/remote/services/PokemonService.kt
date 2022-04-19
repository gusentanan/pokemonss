package com.bagusmerta.pokemonss.data.remote.services

import com.bagusmerta.pokemonss.data.remote.response.PokemonDetailItem
import com.bagusmerta.pokemonss.data.remote.response.PokemonItem
import com.bagusmerta.pokemonss.data.remote.response.PokemonResponses
import retrofit2.http.GET
import retrofit2.http.Path

interface PokemonService {

    @GET("pokemon")
    suspend fun getAllPokemon(): PokemonResponses

    @GET("pokemon/{id}")
    suspend fun getSinglePokemon(@Path("path") path: Int): PokemonDetailItem
}