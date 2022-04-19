package com.bagusmerta.pokemonss.data.remote.services

import androidx.viewbinding.BuildConfig
import com.bagusmerta.pokemonss.utils.Constant.BASE_URL
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class PokemonServiceConfig {
    private fun getInterceptor(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor{ chain ->
                var request = chain.request()
                    .newBuilder()
                    .addHeader("Accept", "application/json")
                    .build()
                chain.proceed(request)
            }
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .connectTimeout(120, TimeUnit.SECONDS)
            .readTimeout(120, TimeUnit.SECONDS)
            .build()
    }

    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(getInterceptor())
            .build()
    }

    fun getServices(): PokemonService = getRetrofit().create(PokemonService::class.java)
}
