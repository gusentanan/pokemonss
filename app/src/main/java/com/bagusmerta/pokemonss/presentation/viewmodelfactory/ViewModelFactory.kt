package com.bagusmerta.pokemonss.presentation.viewmodelfactory

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.bagusmerta.pokemonss.data.PokemonssRepositoryImpl
import com.bagusmerta.pokemonss.di.Injection
import com.bagusmerta.pokemonss.presentation.home.HomeViewModel

class ViewModelFactory private constructor(private val repositoryImpl: PokemonssRepositoryImpl) :
    ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(HomeViewModel::class.java) -> { HomeViewModel(repositoryImpl) as T }
            else -> throw Throwable("Unknown ViewModel Class" + modelClass.name)
        }
    }

    companion object{
        @Volatile
        private var instance: ViewModelFactory? = null
        fun getInstance(): ViewModelFactory =
            instance ?: synchronized(this){
                ViewModelFactory(Injection.provideRepository()).apply { instance = this }
            }
    }
}