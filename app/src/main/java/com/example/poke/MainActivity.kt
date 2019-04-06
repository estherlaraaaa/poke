package com.example.poke

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import com.example.poke.Models.Pokemon
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONObject
import java.net.URL
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.longToast
import org.jetbrains.anko.uiThread


class MainActivity : AppCompatActivity() {

    private val mPokemonList = mutableListOf<Pokemon>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupRecyclerView()
        getPokemonList()
    }

    private fun getPokemonList() {
        doAsync {
            var result = URL("https://pokeapi.co/api/v2/pokemon/").readText()
            var resultJson = JSONObject(result)
            var pokemons = resultJson.getJSONArray("results")
            mPokemonList.clear()
            for (i in 0..(pokemons.length() - 1)){
                var item = pokemons.getJSONObject(i)
                var url:String = item["url"].toString()
                var splits = url.split("/")
                var id_number = splits[splits.size-2]
                var image_url = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/$id_number.png"
                mPokemonList.add(Pokemon(item["name"].toString(), item["url"].toString(), id_number , image_url))
            }

            uiThread{
                recyclerView.adapter?.notifyDataSetChanged()
            }
        }

    }

    private fun setupRecyclerView() {
        recyclerView.layoutManager = GridLayoutManager(this, 2)
        recyclerView.adapter = PokemonAdapter(this, mPokemonList)
    }




}

