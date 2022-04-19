package com.bagusmerta.pokemonss.presentation.home

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.bagusmerta.pokemonss.data.remote.response.PokemonItem
import com.bagusmerta.pokemonss.databinding.FragmentHomeBinding
import com.bagusmerta.pokemonss.presentation.viewmodelfactory.ViewModelFactory
import com.bagusmerta.pokemonss.utils.makeToast

class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel
    private var _binding: FragmentHomeBinding ?= null
    private val homeAdapter = HomeAdapter()
    private val items = mutableListOf<PokemonItem>()

    private val binding
        get() = _binding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val viewModelFactory = ViewModelFactory.getInstance()
        homeViewModel = ViewModelProvider(this, viewModelFactory)[HomeViewModel::class.java]

        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        homeViewModel.getAllPokemon()
        initObserver()
        initRecycleView()
    }

    private fun initObserver() {
        homeViewModel.apply {
            result.observe(this@HomeFragment){
                handleResult(it)
            }
            errMessage.observe(this@HomeFragment){
                handleError(it)
            }
        }
    }

    private fun handleError(error: String?) {
        Log.e("ERROR", error.toString())
        this.makeToast(error.toString())
    }

    private fun handleResult(data: List<PokemonItem>?) {
        items.clear()
        data?.let { items.addAll(it) }
        homeAdapter.setItems(items)
    }

    private fun initRecycleView() {
        binding?.apply {
            rvPokemon.layoutManager = GridLayoutManager(context, 2)
            rvPokemon.adapter = homeAdapter
        }
    }
}