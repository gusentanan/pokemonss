package com.bagusmerta.pokemonss.utils

import com.bagusmerta.pokemonss.data.remote.response.PokemonItem
import com.bagusmerta.pokemonss.data.remote.response.PokemonResponses
import com.bagusmerta.pokemonss.data.remote.response.PokemonTypes
import com.bagusmerta.pokemonss.data.remote.response.Type

object DataMapper {
    fun mapPokemonResponsesToItem(data: PokemonResponses): List<PokemonItem>? =
        data.result?.map {
            PokemonItem(
                name = it.name,
                url = it.url
            )
        }
}