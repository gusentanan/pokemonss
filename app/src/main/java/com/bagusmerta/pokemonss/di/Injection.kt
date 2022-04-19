package com.bagusmerta.pokemonss.di

import com.bagusmerta.pokemonss.data.PokemonssRepositoryImpl
import com.bagusmerta.pokemonss.data.remote.services.PokemonServiceConfig


object Injection {
    fun provideRepository(): PokemonssRepositoryImpl{
        return PokemonssRepositoryImpl.getInstance(PokemonServiceConfig())
    }
}