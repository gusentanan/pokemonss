package com.bagusmerta.pokemonss.utils

import android.app.Activity
import android.widget.ImageView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy


fun ImageView.loadImage(id: String?){
    Glide.with(context)
        .load("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/${id}.png")
        .diskCacheStrategy(DiskCacheStrategy.ALL)
        .into(this)
}

fun Fragment.makeToast(message: String){
    Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
}