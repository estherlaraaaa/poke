package com.example.poke

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
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

    lateinit var viewManager: LinearLayoutManager
    lateinit var viewAdpater: PokemonAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initRecyclerView()

        getPokemonList()

    }

    fun initRecyclerView() {

        viewManager = LinearLayoutManager(this)

        viewAdpater = PokemonAdapter(mPokemonList, "QUE DIABLOS VA ACA AUXILIO")

        recyclerview.apply {
            layoutManager = viewManager
            adapter = viewAdpater
        }
    }


    private fun getPokemonList() {
        doAsync {
            var result = URL("https://pokeapi.co/api/v2/pokemon/").readText()
            var json = JSONObject(result)
            var pokemon = json.getJSONArray("results")

            mPokemonList.clear()
            for (i in 0..(pokemon.length() - 1)) {
                var item = pokemon.getJSONObject(i)

                mPokemonList.add(Pokemon(item["name"].toString(), item["url"].toString()))
            }
            uiThread {
                longToast("Request performed")
                recyclerview.adapter?.notifyDataSetChanged()
            }
        }

    }


}
