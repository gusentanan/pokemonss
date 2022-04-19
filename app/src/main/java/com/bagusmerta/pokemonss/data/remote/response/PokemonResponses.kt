package com.bagusmerta.pokemonss.data.remote.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize


@Parcelize
data class PokemonResponses(
    @SerializedName("results")
    val result: List<PokemonItem>?
): Parcelable

@Parcelize
data class PokemonItem(
    @SerializedName("name")
    val name: String?,
    @SerializedName("url")
    val url: String?
): Parcelable

@Parcelize
data class PokemonDetailItem(
    @SerializedName("id")
    val id: Int?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("height")
    val height: Int?,
    @SerializedName("weight")
    val weight: Int?,
    @SerializedName("types")
    val types: List<PokemonTypes>?
): Parcelable

@Parcelize
data class PokemonTypes(
    @SerializedName("slot")
    val slot: Int?,
    @SerializedName("type")
    val type: Type?
): Parcelable

@Parcelize
data class Type(
    @SerializedName("name")
    val name: String?
): Parcelable

