package com.bagusmerta.pokemonss.data

import com.bagusmerta.pokemonss.data.remote.response.PokemonDetailItem
import com.bagusmerta.pokemonss.data.remote.response.PokemonItem
import com.bagusmerta.pokemonss.data.remote.services.PokemonServiceConfig
import com.bagusmerta.pokemonss.utils.DataMapper
import com.bagusmerta.pokemonss.utils.ResultState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import java.lang.Exception


interface PokemonssRepository{
    fun getAllPokemon(): Flow<ResultState<List<PokemonItem>>>
    fun getSinglePokemon(pokemonId: Int): Flow<ResultState<PokemonDetailItem>>
}

class PokemonssRepositoryImpl(private val service: PokemonServiceConfig): PokemonssRepository {

    override fun getAllPokemon(): Flow<ResultState<List<PokemonItem>>> {
        return flow {
            try {
                val res = service.getServices().getAllPokemon()
                val dataMap = res.let {
                    DataMapper.mapPokemonResponsesToItem(it)
                }
                emit(ResultState.Success(dataMap))
            }catch (e: Exception){
                emit(ResultState.Error(e.message))
            }
        }.flowOn(Dispatchers.IO)
    }

    override fun getSinglePokemon(pokemonId: Int): Flow<ResultState<PokemonDetailItem>> {
        return flow {
            try {
                val res = service.getServices().getSinglePokemon(pokemonId)
                emit(ResultState.Success(res))

            }catch (e: Exception){
                emit(ResultState.Error(e.message))
            }
        }.flowOn(Dispatchers.IO)
    }

    companion object{
        @Volatile
        private var INSTANCE: PokemonssRepositoryImpl ?= null
        fun getInstance(service: PokemonServiceConfig): PokemonssRepositoryImpl =
            INSTANCE ?: synchronized(this){
                PokemonssRepositoryImpl(service).apply { INSTANCE = this }
            }
    }

}