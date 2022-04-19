package com.bagusmerta.pokemonss.presentation.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bagusmerta.pokemonss.data.PokemonssRepositoryImpl
import com.bagusmerta.pokemonss.data.remote.response.PokemonItem
import com.bagusmerta.pokemonss.utils.ResultState
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class HomeViewModel(private val pokemonssRepositoryImpl: PokemonssRepositoryImpl): ViewModel() {

    private val _result = MutableLiveData<List<PokemonItem>?>()
    private val _errMessage = MutableLiveData<String?>()

    val result: LiveData<List<PokemonItem>?>
    get() = _result

    val errMessage: LiveData<String?>
    get() = _errMessage

    fun getAllPokemon(){
        viewModelScope.launch {
            pokemonssRepositoryImpl.getAllPokemon().collect {
                when(it){
                    is ResultState.Success -> _result.postValue(it.data)
                    is ResultState.Error -> _errMessage.postValue(it.errMessage)
                }
            }
        }
    }
}