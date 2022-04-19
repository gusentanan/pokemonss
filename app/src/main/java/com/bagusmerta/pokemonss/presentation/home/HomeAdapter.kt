package com.bagusmerta.pokemonss.presentation.home

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bagusmerta.pokemonss.data.remote.response.PokemonItem
import com.bagusmerta.pokemonss.databinding.ItemPokemonHomeBinding
import com.bagusmerta.pokemonss.utils.loadImage

class HomeAdapter(): RecyclerView.Adapter<HomeAdapter.ViewHolder>() {

    private var listPokemon = mutableListOf<PokemonItem>()

    inner class ViewHolder(private val binding: ItemPokemonHomeBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(item: PokemonItem){
            binding.apply {
                tvPokemon?.text = item.name
                val pokemonId = item.url?.split("/".toRegex())?.dropLast(1)?.last()
                ivPokemon?.loadImage(pokemonId)

            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemPokemonHomeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = listPokemon[position]
        holder.bind(item)
    }

    override fun getItemCount() = listPokemon.size

    @SuppressLint("NotifyDataSetChanged")
    fun setItems(data: MutableList<PokemonItem>){
        this.listPokemon = data
        notifyDataSetChanged()
    }
}