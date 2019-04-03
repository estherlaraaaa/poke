package com.example.poke

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.example.poke.Models.Pokemon
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.list_item.*
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

        recyclerview.layoutManager = LinearLayoutManager(this)

        recyclerview.adapter = PokemonAdapter(mPokemonList, this)

        getPokemonList()

    }


    private fun getPokemonList() {
        doAsync {
            var result = URL("https://pokeapi.co/api/v2/pokemon").readText()
            var json = JSONObject(result)
            var pokemons = json.getJSONArray("pokemon")
            var first = pokemons.getJSONObject(0).getJSONObject("pokemon")

            mPokemonList.clear()
            for (i in 0..(pokemons.length() - 1)) {
                var item = pokemons.getJSONObject(i).getJSONObject("pokemon")

                mPokemonList.add(Pokemon(item["name"].toString(), item["url"].toString()))
            }
            uiThread {
                longToast("Request performed")
                recyclerview.adapter?.notifyDataSetChanged()
            }
        }
    }
}
